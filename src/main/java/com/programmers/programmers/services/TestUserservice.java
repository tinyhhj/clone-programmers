package com.programmers.programmers.services;

import com.programmers.programmers.exceptions.NotFoundException;
import com.programmers.programmers.models.User;
import com.programmers.programmers.repositories.TestUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.stereotype.Service;

@Service
public class TestUserservice {
    @Autowired
    TestUserRepository repository;

    public User findByLoginId(String loginId) {
        return repository.findUserByLoginId(loginId)
                .orElseThrow(()->new NotFoundException("404","loginId is invalid " + loginId))
                ;
    }
}
