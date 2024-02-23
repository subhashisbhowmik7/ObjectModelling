package com.crio.jukebox.entities;

import java.util.List;
import java.util.stream.Collectors;

public class Playlist extends BaseEntity {
    private final String name;
    private List<Song> songs;

    public Playlist(String name, List<Song> songs){
        this.name = name;
        this.songs = songs;
    }

    public Playlist(String id, String name, List<Song> songs){
        this(name, songs);
        this.id = id;
    }

    //Copy Constructor
    public Playlist(Playlist entity){
        this(entity.getId(), entity.getName(), entity.getSongList());
    }

    //Getters
    public String getName(){
        return name;
    }
    public List<Song> getSongList(){
        return songs.stream().collect(Collectors.toList());
    }

    //Behaviours
    public void addSong(Song song){
        songs.add(song);
    }
    public void deleteSong(Song song){
        songs.removeIf(s-> s.getId() == song.getId());
    }
    public boolean checkIfSongExist(Song song){
        if(this.getSongList().isEmpty()){
            return false;
        }
        return this.getSongList().stream().anyMatch(s-> s.equals(song));
    }
    

    public String toString()
    {
        return "Playlist [id=" + id + ", name=" + name + ", songIds=" + songs + "]";
    }
    
}
