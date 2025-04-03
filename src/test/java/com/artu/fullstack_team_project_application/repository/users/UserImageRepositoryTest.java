package com.artu.fullstack_team_project_application.repository.users;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserImageRepositoryTest {
    @Autowired
    UserImageRepository userImageRepository;

    @Test
    void findUserImgByPrfImgId() {
        System.out.println(userImageRepository.findUserImgByPrfImgId(1));
    }
}