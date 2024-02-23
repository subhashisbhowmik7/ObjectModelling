package com.crio.jukebox.entities;

import java.util.List;
import java.util.stream.Collectors;


public class User extends BaseEntity{
    private String name;
    private List<Playlist> playlists;
    private UserPlaylistCurrentSong userPlaylistCurrentSong;


    public User(String name, List<Playlist>playlists)
    {
        this.name=name;
        this.playlists=playlists;
    }

    public User(String name)
    {
        this.name=name;
        
    }

    public User(String id, String name)
    {
        this.id=id;
        this.name=name;
        
    }


    public User(String id,String name, List<Playlist>playlists)
    {
        this(name, playlists);
        this.id=id;
    }



    //Copy Constructor
    public User(User entity){
        this(entity.getId(), entity.getName(), entity.getPlaylists());
    }


    

    //Getters
    public String getName() {
        return name;
    }
    public List<Playlist> getPlaylists() {
        return playlists.stream().collect(Collectors.toList());
    }
    public UserPlaylistCurrentSong getUserPlaylistCurrentSong() {
        return userPlaylistCurrentSong;
    }

    //Setters
    public void setUserPlaylistCurrentSong(UserPlaylistCurrentSong userPlaylistCurrentSong) {
        this.userPlaylistCurrentSong = userPlaylistCurrentSong;
    }

    

    //Behaviours
    public void addPlaylist(Playlist playlist){
        playlists.add(playlist);
    }
    public void deletePlaylist(Playlist playlist){
        playlists.removeIf(p-> p.getId() == playlist.getId());
    }
    public boolean checkIfPlaylistExist(Playlist playlist){
        if(this.getPlaylists().isEmpty()){
            return false;
        }
        return this.getPlaylists().stream().anyMatch(p-> p.equals(playlist));
    }


    @Override
    public String toString() {
        return "User [name=" + name + ", playlists=" + playlists + "]";
    }

    
}
