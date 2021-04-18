package com.programmers.programmers.apis.dto;

import com.programmers.programmers.models.Test;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TestDto {
    private Long id;
    private String title;
    private String description;
    private LocalDateTime createAt;
    private List<TestCaseDto> testcases = new ArrayList<>();
    private TestDto() {
    }

    public TestDto(Test source) {
        BeanUtils.copyProperties(source,this);
        this.testcases = source.getTestCases().stream().map(TestCaseDto::new).collect(Collectors.toList());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }

    public List<TestCaseDto> getTestcases() {
        return testcases;
    }

    public void setTestcases(List<TestCaseDto> testcases) {
        this.testcases = testcases;
    }
}
