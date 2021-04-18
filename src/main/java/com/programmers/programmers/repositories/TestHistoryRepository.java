package com.programmers.programmers.repositories;

import com.programmers.programmers.models.TestHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TestHistoryRepository extends JpaRepository<TestHistory,Long> {
    Optional<List<TestHistory>> findTestHistoriesByTestIdAndUserLoginId(long testId, String loginId);
}
