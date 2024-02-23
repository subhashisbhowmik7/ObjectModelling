package com.crio.jukebox.repositories;

import java.util.*;
import com.crio.jukebox.entities.*;

public interface ISongRepository {
    public Song save(Song entity);

    public List<Song> findAll();

    public Optional<Song> findById(String id);

    boolean existsById(String id);

    public void delete(Song entity);

    public void deleteById(String id);
    
    public long count();
        
}
