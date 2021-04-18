package com.programmers.programmers.services;

import com.programmers.programmers.models.Test;
import com.programmers.programmers.repositories.TestRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TestService {
    private final TestRepository testRepository;
    public TestService(TestRepository testRepository) {
        this.testRepository = testRepository;
    }
    @Transactional
    public List<Test> getTests() {
        return this.testRepository.findAll();
    }

    @Transactional
    public Test getTest(long id) {
        return this.testRepository.findById(id).orElseThrow(()->new RuntimeException(""+id+ "is invalid"));
    }
}
