package com.crio.jukebox.repositories;

import java.util.*;
import com.crio.jukebox.entities.*;


public interface IPlaylistRepository  {
    public Playlist save(Playlist entity);

    public List<Playlist> findAll();

    public Optional<Playlist> findById(String id);

    boolean existsById(String id);

    public void delete(Playlist entity);

    public void deleteById(String id);

    public long count();
        
}
