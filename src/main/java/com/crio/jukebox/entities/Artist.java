package com.crio.jukebox.entities;

public class Artist {       //extends BaseEntity but we need to steup repository
    private final String name;

    public Artist(String name){
        this.name = name;
    }

    public String getName()
    {
        return name;
    }
    @Override
    public String toString() {
        return "Artist [name=" + name + "]"; 
    }
}
