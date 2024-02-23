package com.crio.jukebox.services;

import java.util.*;
import java.util.stream.Collectors;
import com.crio.jukebox.dtos.*;
import com.crio.jukebox.entities.*;
import com.crio.jukebox.exceptions.*;
import com.crio.jukebox.repositories.*;

public class PlaylistService implements IPlaylistService{

    private final IPlaylistRepository playlistRepository;
    private final IUserRepository userRepository;
    private final ISongRepository songRepository;

    public PlaylistService(IPlaylistRepository playlistRepository, IUserRepository userRepository, ISongRepository songRepository){
        this.playlistRepository = playlistRepository;
        this.userRepository = userRepository;
        this.songRepository = songRepository;
    }

    @Override
    public PlaylistSummaryDto create(String userId, String name, List<String> songIds)
    {
        final User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User for given id: " + userId + " not found!"));
        
        final List<Song> songs = songIds.stream().map(s -> songRepository.findById(s).orElseThrow(() -> new SongNotFoundException("Some Requested Songs Not Available. Please try again."))).collect(Collectors.toList());
        
        Playlist p = playlistRepository.save(new Playlist(name, songs));
        PlaylistSummaryDto pDto = new PlaylistSummaryDto(p.getId(), p.getName(), p.getSongList().stream().map(s-> s.getTitle()).collect(Collectors.toList()));
        user.addPlaylist(p);
        userRepository.save(user);
        return pDto;
    }

    public void deletePlaylist(String userId, String playlistId) throws PlaylistNotFoundException
    {
        final User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User for given id: " + userId + " not found!"));
        final Playlist p = playlistRepository.findById(playlistId).orElseThrow(() -> new PlaylistNotFoundException("Playlist IDs do not exist"));
        if(! user.checkIfPlaylistExist(p)){
            throw new PlaylistNotFoundException("Playlist Not Found");
        }

        user.deletePlaylist(p);
        playlistRepository.deleteById(playlistId);
        userRepository.save(user);

    }
    
    public PlaylistSummaryDto addSongPlaylist(String userId, String playlistId, List<String> songIds) throws SongNotFoundException{
        final User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User for given id: " + userId + " not found!"));
        final Playlist p = playlistRepository.findById(playlistId).orElseThrow(() -> new PlaylistNotFoundException("Playlist IDs do not exist"));
        if(! user.checkIfPlaylistExist(p)){
            throw new PlaylistNotFoundException("Playlist Not Found");
        }
        
        final List<Song> songs = songIds.stream().map(s-> songRepository.findById(s).orElseThrow(() -> new SongNotFoundException("Some Requested Songs Not Available. Please try again."))).collect(Collectors.toList()); 
        for(Song s : songs){ 
            if(! p.checkIfSongExist(s)){
                p.addSong(s);
            }
        }
        playlistRepository.save(p);
        PlaylistSummaryDto pDto = new PlaylistSummaryDto(p.getId(), p.getName(), p.getSongList().stream().map(s-> s.getId()).collect(Collectors.toList()));
        userRepository.save(user);
        return pDto;
    }

    public PlaylistSummaryDto deleteSongPlaylist(String userId, String playlistId, List<String> songIds) throws SongNotFoundException{
        final User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User for given id: " + userId + " not found!"));
        final Playlist p = playlistRepository.findById(playlistId).orElseThrow(() -> new PlaylistNotFoundException("Playlist IDs do not exist"));
        if(! user.checkIfPlaylistExist(p)){
            throw new PlaylistNotFoundException("Playlist Not Found");
        }

        final List<Song> songs = songIds.stream().map(s-> songRepository.findById(s).orElseThrow(() -> new SongNotFoundException("Some Requested Songs Not Available. Please try again."))).collect(Collectors.toList());

        for(Song s : songs){
            if(p.checkIfSongExist(s)){
                p.deleteSong(s);
            } else {
                throw new SongNotFoundException("Some Requested Songs for Deletion are not present in the playlist. Please try again.");
            }
        }
        playlistRepository.save(p);
        PlaylistSummaryDto pDto = new PlaylistSummaryDto(p.getId(), p.getName(), p.getSongList().stream().map(s-> s.getId()).collect(Collectors.toList()));
        userRepository.save(user);
        return pDto;
    }

    public SongSummaryDto playPlaylist(String userId, String playlistId) throws PlaylistNotFoundException{
          
        final User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User for given id: " + userId + " not found!"));
        final Playlist p = playlistRepository.findById(playlistId).orElseThrow(() -> new PlaylistNotFoundException("Playlist IDs do not exist"));
        if(! user.checkIfPlaylistExist(p)){
            throw new PlaylistNotFoundException("Playlist Not Found");
        }

        List<Song> songs = p.getSongList();
        if(songs.isEmpty()){
            throw new EmptyPlaylistException("Playlist is empty.");
        }

        Song currentSong = songs.get(0);//fetch first song of the playlist
        SongSummaryDto sDto = new SongSummaryDto(currentSong.getTitle(), currentSong.getAlbum().getName(), currentSong.getFeaturArtists().stream().map(x-> x.getName()).collect(Collectors.toList()));
        //create UserPlaylistCurrentSong obj san set it to User
        UserPlaylistCurrentSong userPlaylistCurrentSong = new UserPlaylistCurrentSong(p, currentSong);
        user.setUserPlaylistCurrentSong(userPlaylistCurrentSong);

        userRepository.save(user);
        return sDto;
        
    }
    
    
}
