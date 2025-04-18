package com.artu.fullstack_team_project_application.repository.postings;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PostingRepositoryTest {
    @Autowired
    private PostingRepository postingRepository;

    @Test
    void findByIsUsedFalse() {
        // System.out.println(postingRepository.);
    }

    @Test
    @Transactional
    void findByUser_UserId() {
        System.out.println(postingRepository.findByUser_UserId("user1"));
    }
}