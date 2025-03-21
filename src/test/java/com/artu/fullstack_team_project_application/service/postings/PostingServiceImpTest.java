package com.artu.fullstack_team_project_application.service.postings;

import com.artu.fullstack_team_project_application.entity.postings.Posting;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PostingServiceImpTest {
    @Autowired
    private PostingService postingService;
    private PostingServiceImp postingServiceImp;

    @Test
    void findByUserId() {
        System.out.println(postingService.findByUserId("user1"));
    }
}