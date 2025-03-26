package com.artu.fullstack_team_project_application.controller;

import com.artu.fullstack_team_project_application.entity.users.user.User;
import com.artu.fullstack_team_project_application.service.users.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/signIn.do")
    public String signInForm() {
        return "user/signIn";
    }

    @PostMapping("/signIn.do")
    public boolean authenticate(
            @RequestParam("userId") String username,
            @RequestParam("password") String password) {
        Optional<User> userOptional = userService.readOne(username);
        if (userOptional.isPresent() && userOptional.get().getPassword().equals(password)) {
            return true;
        }
        return false;

        // 비번 암호화
        // 쿠키나 세션 만들기
        // 해더 fragments 만들기
    }

    @GetMapping("/signUp.do")
    public String signUpForm() {
        return "user/signUp";
    }

    @PostMapping("/signUp.do")
    public String signUpAction( // Action페이지, 리다이렉트 할거임.
            @ModelAttribute User user, // 폼에서 전송된 데이터를 자동으로 객체로 변환해주는 역할
            RedirectAttributes redirectAttributes
    ) throws Exception {
        boolean result = false;
        result = userService.save(user).getIsUsed(); // 왜 result에 안들어가지지?  getIsUsed를 이렇게 쓸 줄이야.

        if(result){ // 성공하면, 리다이렉트 하고 user 아이디 같이 보내기
            redirectAttributes.addFlashAttribute("userId", user.getUserId());
            return "redirect:/index.html";
        }else{ // 실패시 등록페이지 머뭄. 적은 데이터 다 사라지니까 js로 회원가입에 필요한 여러 조건을 필터링 걸어야 할 것.
            return "redirect:/user/singUp.do";
        }
    }

}
