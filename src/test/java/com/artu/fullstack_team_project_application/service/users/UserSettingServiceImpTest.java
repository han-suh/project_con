package com.artu.fullstack_team_project_application.service.users;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserSettingServiceImpTest {
    @Autowired
    private UserSettingService userSettingService;

    @Test
    void save() {
//        System.out.println();
    }

    @Test
    void delete() {
    }

    @Test
    void findOne() {
        System.out.println(userSettingService.findOne("user1001"));
    }
}