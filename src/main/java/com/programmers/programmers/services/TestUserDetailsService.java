package com.programmers.programmers.services;

import com.programmers.programmers.models.User;
import com.programmers.programmers.repositories.TestUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class TestUserDetailsService implements UserDetailsService {
    @Autowired
    private TestUserRepository testUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return testUserRepository.findUserByLoginId(username).orElseThrow(()->new UsernameNotFoundException(""+username+" invalid"));
    }
}
