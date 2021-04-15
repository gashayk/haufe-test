package com.haufe.beer.app.demo.service.impl;

import com.haufe.beer.app.demo.exception.NotFoundException;
import com.haufe.beer.app.demo.model.entity.User;
import com.haufe.beer.app.demo.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        Optional<User> user = userRepository.findUserByUsername(username);

        if (!user.isPresent()) {
            log.error("loadUserByUsername() -> User not found");
            throw new NotFoundException("Could not find user", HttpStatus.NOT_FOUND);
        }

        return new com.haufe.beer.app.demo.service.UserDetailsService(user.get());
    }
}
