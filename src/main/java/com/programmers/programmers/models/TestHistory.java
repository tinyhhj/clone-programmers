package com.programmers.programmers.models;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="test_histories")
public class TestHistory {
    @Column
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name="test_id")
    private Test test;
    @Column
    private String errorMessage;

    @Column
    private boolean pass;
    @Column
    private long elapsedTime;
    @Column
    private LocalDateTime createAt;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Test getTest() {
        return test;
    }

    public void setTest(Test test) {
        this.test = test;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public boolean isPass() {
        return pass;
    }

    public void setPass(boolean pass) {
        this.pass = pass;
    }

    public long getElapsedTime() {
        return elapsedTime;
    }

    public void setElapsedTime(long elapsedTime) {
        this.elapsedTime = elapsedTime;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    private TestHistory() {

    }

    public TestHistory(Test test,User user, String errorMessage, boolean pass, long elapsedTime) {
        setTest(test);
        setUser(user);
        setErrorMessage(errorMessage);
        setPass(pass);
        setElapsedTime(elapsedTime);
        setCreateAt(LocalDateTime.now());
    }
}
