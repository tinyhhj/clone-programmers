package com.programmers.programmers.apis.dto;

import com.programmers.programmers.models.TestCase;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;

public class TestCaseDto {
    private String input;
    private String output;
    private LocalDateTime createAt;

    public TestCaseDto(TestCase source) {
        BeanUtils.copyProperties(source, this);
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }
}
