package com.artu.fullstack_team_project_application.controller;

import com.artu.fullstack_team_project_application.entity.users.user.User;
import com.artu.fullstack_team_project_application.service.users.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/serch")
@AllArgsConstructor
public class SearchUserController {
    private final UserService userService;

    // 이름,메일,아이디로 일치한 사람 찾기
    @GetMapping("/userserch.do")
    public String postForm(
            @RequestParam(required = false) String usersearch,  // 파라미터가 없을 경우 null 처리
            Model model
    ){
        if(usersearch == null || usersearch.isEmpty()){
            // 검색어가 없을 경우, 빈 문자열을 전달하거나 처리하지 않음
            usersearch = "";
        }
        List<User> userList = userService.searchUsers(usersearch); // 검색어를 전달하여 검색
        model.addAttribute("users", userList);
        return "/serch/userserch";
    }

}
