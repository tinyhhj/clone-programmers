package com.programmers.programmers.repositories;

import com.programmers.programmers.models.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface TestRepository extends JpaRepository<Test,Long> {
}
