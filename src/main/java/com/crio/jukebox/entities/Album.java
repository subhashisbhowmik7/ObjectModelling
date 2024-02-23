package com.crio.jukebox.entities;

import java.util.List;

public class Album {//extends BaseEntity but we need to steup repository

    private String name;
    private List<Song> songs;
    private User owner;
    private List<Artist> listOfArtists;

    public Album(String name)
    {
        this.name=name;
    }

    public String getName(){
        return name;
    }
    public List<Song> getSongs(){
        return songs;
    }
    public User getOwner()
    {
        return owner;
    }
    public List<Artist> getListOfArtists()
    {
        return listOfArtists;
    }
    public String toString() {
        return "Album [name=" + name + "]" + "Owner: ["+ owner ; 
    }     
    
}
