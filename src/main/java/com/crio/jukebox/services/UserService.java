package com.crio.jukebox.services;
//import java.util.*;
import com.crio.jukebox.dtos.*;
import com.crio.jukebox.entities.*;
//import com.crio.jukebox.exceptions.*;
import com.crio.jukebox.repositories.*;

public class UserService implements IUserService {
    private final IUserRepository userRepository;

    public UserService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Create a new user in the system
    @Override
    public UserInfoDto create(String name) {
        User u = userRepository.save(new User(name));
        UserInfoDto uDto = new UserInfoDto(u.getId(), u.getName());
        return uDto;
    }
    
}
