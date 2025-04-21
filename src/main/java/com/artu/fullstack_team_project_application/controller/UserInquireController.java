package com.artu.fullstack_team_project_application.controller;

import com.artu.fullstack_team_project_application.entity.users.base.UserInquire;
import com.artu.fullstack_team_project_application.entity.users.user.User;
import com.artu.fullstack_team_project_application.service.users.UserInquireService;
import com.artu.fullstack_team_project_application.service.users.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

//@Controller
@RestController
@RequestMapping("/inquire")
@AllArgsConstructor
public class UserInquireController {
    private final UserInquireService userInquireService;
    private final UserService userService;

//    @PostMapping("/{userId}/inquire.do")
//    public String saveInquire(
//            @PathVariable String userId,
//            @ModelAttribute UserInquire userInquire
//    ) {
//        User user = userService.readOne(userId).get();
//        userInquire.setUser(user);
//        userInquireService.save(userInquire);
//        return "redirect:/inquire/" + userId + "/myinquire.do";
//    }

    @PostMapping("/{userId}/inquire.do")
    public ResponseEntity<UserInquire> saveInquire(
            @PathVariable String userId,
            @ModelAttribute UserInquire userInquire
    ){
        User user = userService.readOne(userId).get();
        userInquire.setUser(user);
        userInquireService.save(userInquire);
        return ResponseEntity.ok(userInquire);
    }


//    @DeleteMapping("/{userId}/inquire.do")
//    public String deleteInquire(
//            @PathVariable String userId,
//            @RequestParam(required = false) String inquireId
//    ){
//        userInquireService.delete(inquireId);
//        return "redirect:/inquire/" + userId + "/myinquire.do";
//    }


    @DeleteMapping("/{userId}/inquire.do")
    public ResponseEntity<Void> deleteInquire(
            @PathVariable String userId,
            @RequestParam(required = false) String inquireId
    ){
        userInquireService.delete(inquireId);
        return ResponseEntity.noContent().build();
    }

//    @GetMapping("/{userId}/myinquire.do")
//    public String myInquire(
//            @PathVariable String userId,
//            Model model
//    ) {
//        Optional<User> userOptional = userService.readOne(userId);
//        User user = userOptional.get();
//        model.addAttribute("user", user);
//        Set<UserInquire> userInquires = userInquireService.findInquireByUserId(userId);
//        model.addAttribute("userInquires", userInquires);
//        return "inquire/myinquire";
//    }

    @GetMapping("/{userId}/myinquire.do")
    public ResponseEntity<Set<UserInquire>> myInquire(
            @PathVariable String userId
    ){
        Optional<User> userOptional = userService.readOne(userId);
        User user = userOptional.get();
        Set<UserInquire> userInquires = userInquireService.findInquireByUserId(userId);
        return ResponseEntity.ok(userInquires);
    }


//    @GetMapping("/{userId}/inquirecate.do")
//    public String InqCate(
//            @PathVariable UserInquire.InquireCategory inquireCatagrory,
//            @PathVariable String userId,
//            Model model
//    ) {
//        Optional<User> userOptional = userService.readOne(userId);
//        User user = userOptional.get();
//        model.addAttribute("user", user);
//        Set<UserInquire> inquiresCate = userInquireService.findInquireByInquireCategory(inquireCatagrory);
//        return "inquire/inquire";
//    }

    @GetMapping("/{userId}/inquirecate.do")
    public ResponseEntity<Map<String, Object>> InqCate(
            @RequestParam  UserInquire.InquireCategory inquireCatagrory,
            @PathVariable String userId
    ){
        Set<UserInquire> inquiresCate = userInquireService.findInquireByInquireCategory(inquireCatagrory);
        Map<String, Object> result = new HashMap<>();
        result.put("inquiresCate", inquiresCate);
        Optional<User> userOptional = userService.readOne(userId);
        User user = userOptional.get();
        result.put("user", user);
        return ResponseEntity.ok(result);
    }

//    @GetMapping("/findAll.do")
//    public String findAll(
//            @RequestParam String userId,
//            Model model
//    ) {
//        Optional<User> userOptional = userService.readOne(userId);
//        User user = userOptional.get();
//        model.addAttribute("user", user);
//        List<UserInquire> userInquires = userInquireService.findALL();
//        model.addAttribute("userInquires", userInquires);
//        return "inquire/findAll";
//    }

//    @GetMapping("/{userId}/findAll.do")
//    public ResponseEntity<List<UserInquire>> findAll(
//            @PathVariable String userId
//    ){
//        List<UserInquire> userInquires = userInquireService.findALL();
//        return ResponseEntity.ok(userInquires);
//    }

    @GetMapping("/{userId}/findAll.do")
    public ResponseEntity<Map<String, Object>> getInquires(@PathVariable String userId) {
        List<UserInquire> inquires = userInquireService.findALL();
        Optional<User> userOptional = userService.readOne(userId);
        User user = userOptional.get();

        Map<String, Object> result = new HashMap<>();
        result.put("inquires", inquires);  // 직접 엔티티 리스트 담기
        result.put("user", user);

        return ResponseEntity.ok(result);
    }
}
