package com.artu.fullstack_team_project_application.repository.postings;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
    void findByUserId() {
        //System.out.println(postingRepository.findByUserId("user1"));
    }

    @Test
    void findByPostId() {
        // System.out.println(postingRepository.findByPostId(1));
    }
}