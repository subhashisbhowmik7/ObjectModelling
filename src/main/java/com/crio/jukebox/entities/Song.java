package com.crio.jukebox.entities;

import java.util.List;

public class Song extends BaseEntity{
    private final String title;
    private final String genre;
    private final Album album;
    private final Artist owner;
    private final List<Artist> featureArtists;

    public Song(String title, String genre, Album album, Artist owner, List<Artist> featureArtists){
        this.title = title;
        this.genre = genre;
        this.album = album;
        this.owner = owner;
        this.featureArtists = featureArtists;
    }

    public Song(String id ,String title, String genre, Album album, Artist owner, List<Artist> featureArtists){
        this(title, genre, album, owner, featureArtists);
        this.id = id;
    }

    //Copy Constructors
    public Song(Song entity)
    {
        this(entity.getTitle(), entity.getGenre(), entity.getAlbum(), entity.getOwner(), entity.getFeaturArtists());
    }

    //Getters
    public String getTitle(){
        return title;
    }
    public String getGenre(){
        return genre;
    }
    public Album getAlbum(){
        return album;
    }
    public Artist getOwner(){
        return owner;
    }
    public List<Artist> getFeaturArtists(){
        return featureArtists;
    }

    @Override
    public String toString() {
        return "Song [id=" + id + ", title=" + title + ", genre=" + genre + ", album=" + album.getName() + ", owner="+ owner.getName() +", featureArtists"+ featureArtists + "]";
    }

}
