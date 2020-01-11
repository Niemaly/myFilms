package com.Szumski.myFilms.service;

import com.Szumski.myFilms.model.databaseModels.User;
import com.Szumski.myFilms.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class UserDetailsServiceImplementation implements UserDetailsService {


    UserRepository userRepository;

    @Autowired
    public UserDetailsServiceImplementation(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(userName);
        if(user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return user;
    }

}

