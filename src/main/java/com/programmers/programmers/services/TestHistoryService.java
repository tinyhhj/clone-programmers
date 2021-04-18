package com.programmers.programmers.services;

import com.programmers.programmers.models.TestHistory;
import com.programmers.programmers.repositories.TestHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TestHistoryService {
    @Autowired
    private TestHistoryRepository testHistoryRepository;

    public List<TestHistory> findByTestIdAndUserLoginId(long testId, String loginId) {
        return this.testHistoryRepository.findTestHistoriesByTestIdAndUserLoginId(testId,loginId)
                .orElse(new ArrayList<>());
    }

    public TestHistory createHistory(TestHistory history) {
        return this.testHistoryRepository.save(history);
    }
}
