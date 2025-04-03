package com.artu.fullstack_team_project_application.controller;

import com.artu.fullstack_team_project_application.entity.users.user.User;
import com.artu.fullstack_team_project_application.service.users.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/search")
@AllArgsConstructor
public class SearchUserController {
    private final UserService userService;

    // 이름,메일,아이디로 일치한 사람 찾기
    @GetMapping("/users.do")
    public ResponseEntity<List<User>> searchUsers(@RequestParam(required = false) String usersearch) {
        if (usersearch == null || usersearch.isEmpty()) {
            return ResponseEntity.ok(Collections.emptyList()); // 빈 리스트 반환
        }
        List<User> userList = userService.searchUsers(usersearch);
        return ResponseEntity.ok(userList); // JSON 형태로 반환
    }

//    @PostMapping("/usersearch.do")
//    public String postForm(
//            @RequestParam(required = false) String usersearch,  // 파라미터가 없을 경우 null 처리
//            Model model
//    ){
//
//    }

}
