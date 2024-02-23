package com.crio.jukebox.repositories;

import java.util.*;
import com.crio.jukebox.entities.*;

public interface IUserRepository {
    public User save(User entity);

    public List<User> findAll();

    public Optional<User> findById(String id);

    boolean existsById(String id);

    public void delete(User entity);

    public void deleteById(String id);
    
    public long count();
        
}
