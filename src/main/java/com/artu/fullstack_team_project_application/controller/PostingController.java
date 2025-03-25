package com.artu.fullstack_team_project_application.controller;

import com.artu.fullstack_team_project_application.entity.postings.Posting;
import com.artu.fullstack_team_project_application.entity.postings.UserFollow;
import com.artu.fullstack_team_project_application.entity.users.user.User;
import com.artu.fullstack_team_project_application.service.postings.PostingService;
import com.artu.fullstack_team_project_application.service.users.UserService;
import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@Controller
@RequestMapping("/posting")
@AllArgsConstructor
public class PostingController {
    private final PostingService postingService;
    private final UserService userService;
    //logger 출력할 때 컨트롤러로 출력.
    private static final Logger logger = LoggerFactory.getLogger(PostingController.class);

    @GetMapping("/findAll.do")
    public String findAll(Model model) {
        List<User> userList = userService.findAll();
        model.addAttribute("users", userList);
        return "posting/findAll";
    }

    @GetMapping("/{userId}/userpage.do")
    public String userpage(
            @PathVariable String userId,
            Model model) {

        Optional<User> userOptional = userService.readOne(userId);
        System.out.println("userOptional:" + userOptional);
        if (!userOptional.isPresent()) {
            return "redirect:/artu.do";
        }

        User user = userOptional.get();
        model.addAttribute("user", user);  // user 객체를 모델에 담기
        model.addAttribute("userId", userId);  // userId도 모델에 담기

        // followee 수
        Map<String, Long> countFolloweeMap = userService.getCountFollowee(userId);
        Long countFolloweeCount = countFolloweeMap.get("countFollowee");
        model.addAttribute("countFolloweeMap", countFolloweeMap);
        model.addAttribute("countFolloweeCount", countFolloweeCount);

        // follower 수
        Map<String, Long> countFollowerMap = userService.getCountFollower(userId);
        Long countFollowerCount = countFollowerMap.get("countFollower");
        model.addAttribute("countFollowerMap", countFollowerMap);
        model.addAttribute("countFollowerCount", countFollowerCount);

        // 템플릿에 user, followerCounts, followeeCounts를 전달
        return "posting/userpage";
    }

//    public List<Posting> findByUser_UserId(@PathVariable String userId, Model model) {
//        return postingService.findByUserId(userId);
//    }


//    @GetMapping("/{userId}/postpage.do")
//    @ResponseBody
//    public List<Posting> postpage(
//            @PathVariable String userId,
//            Model model) {
//         List<Posting> postings = postingService.findByUserId(userId);
//         Optional<User> userOptional = userService.readOne(userId);
//         User user = userOptional.get();
//         model.addAttribute("userId", userId);
//         model.addAttribute("postings", postings);
//        //return "posting/postpage";
//    }

    @GetMapping("/{userId}/postpage.do")
    @ResponseBody
    public ResponseEntity<Set<Posting>> postpage(
            @PathVariable String userId) {
        Set<Posting> postings = postingService.findByUserId(userId);
        // return ResponseEntity.ok(postings);
        return ResponseEntity.status(201).body(postings);
    }

    @GetMapping("/{userId}/postAdd.do")
    public String postForm(
            @ModelAttribute Posting posting,
            @RequestParam("search") String word,
            Model model
    ){
        // Posting posting = new Posting();
        // model.addAttribute("posting", posting);
        // return "posting/postAdd";
        // postingService.save(posting);
        List<User> userList = userService.searchUsers(word);
        model.addAttribute("users", userList);
        return "/posting/postAdd";
    }



//    public ResponseEntity<Posting> postAdd(Model model){
//        Posting emptyPosting = new Posting();
//        model.addAttribute("posting", emptyPosting);
//        return "posting/postAdd";
//    }

//    @PostMapping
//    public ResponseEntity<Set<Posting>> savePost(
//            // @AuthenticationPrincipal CustomUserDetails userDetails, // 로그인한 사용자 정보 가져오기
//            @RequestBody Posting posting
//    ){
//        // String userId = userDetails.getUserId(); // 보안 강화: 클라이언트가 직접 ID 전달 X
//        Posting savedPosting = postingService.save(posting);
//        return ResponseEntity.ok(savedPosting);
//    }





}
