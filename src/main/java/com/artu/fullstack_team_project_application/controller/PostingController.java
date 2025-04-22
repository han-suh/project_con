package com.artu.fullstack_team_project_application.controller;

import com.artu.fullstack_team_project_application.dto.PostingCommentsDto;
import com.artu.fullstack_team_project_application.entity.postings.Posting;
import com.artu.fullstack_team_project_application.entity.postings.PostingComment;
import com.artu.fullstack_team_project_application.entity.postings.UserFollow;
import com.artu.fullstack_team_project_application.entity.users.user.User;
import com.artu.fullstack_team_project_application.entity.users.user.UserImg;
import com.artu.fullstack_team_project_application.service.postings.PostingLikeService;
import com.artu.fullstack_team_project_application.service.postings.PostingService;
import com.artu.fullstack_team_project_application.service.users.UserService;
import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.*;

//@Controller
@RestController
@RequestMapping("/posting")
@AllArgsConstructor
public class PostingController {
    private final PostingService postingService;
    private final UserService userService;
    private final PostingLikeService postingLikeService;
    //logger 출력할 때 컨트롤러로 출력.
    private static final Logger logger = LoggerFactory.getLogger(PostingController.class);

//    @PostMapping("postAdd.do")
    @PostMapping("/{userId}/postAdd.do")
    public ResponseEntity<Posting> savePost(
//            Authentication authentication,
            @PathVariable String userId,
            @RequestBody Posting posting
    ){
//        if (authentication == null) {
//            throw new AccessDeniedException("로그인이 필요합니다.");
//        }

//        logger.info("savePost: {}",posting);
//        Posting savedPosting = new Posting();

//        String userId = authentication.getuserId(); // 또는 커스텀 UserDetails에서 추출
//
//        User user = userService.readOne(userId)
//                .orElseThrow(() -> new NoSuchElementException("User not found"));
//        posting.setUser(user);

        Posting savedPosting = postingService.save(posting);
//        Posting savedPosting = postingService.save(authentication, posting);
        return ResponseEntity.ok(savedPosting);
    }


//    @DeleteMapping("/postdetail.do")
    @DeleteMapping("/{userId}/postdetail.do")
    public ResponseEntity<Posting> deletePosting(@RequestParam(required = false) Integer postId){
//        if (postId != null) {
            Posting postings = postingService.findByPostId(postId);
            postingService.delete(postings);
            return ResponseEntity.ok(postings);
//        }else {
//            return ResponseEntity.badRequest().build();
//        }
//        직관적인 방법 - 레파지토리에 구현 필요
//        Posting postingDelete = postingService.deleteByPostId(postId);
//        return ResponseEntity.ok(postingDelete);
    }


    @GetMapping("/findAll.do")
    public String findAll(Model model) {
        List<User> userList = userService.findAll();
        model.addAttribute("users", userList);
        return "posting/findAll";
    }

//    @GetMapping("/{userId}/userpage.do")
//    public String userpage(
//            @PathVariable String userId,
//            Model model) {
//
//        Optional<User> userOptional = userService.readOne(userId);
//        System.out.println("userOptional:" + userOptional);
//        if (!userOptional.isPresent()) {
//            return "redirect:/artu.do";
//        }
//
//        User user = userOptional.get();
//        model.addAttribute("user", user);  // user 객체를 모델에 담기
//        model.addAttribute("userId", userId);  // userId도 모델에 담기
//
//        // followee 수
//        Map<String, Long> countFolloweeMap = userService.getCountFollowee(userId);
//        Long countFolloweeCount = countFolloweeMap.get("countFollowee");
//        model.addAttribute("countFolloweeMap", countFolloweeMap);
//        model.addAttribute("countFolloweeCount", countFolloweeCount);
//
//        // follower 수
//        Map<String, Long> countFollowerMap = userService.getCountFollower(userId);
//        Long countFollowerCount = countFollowerMap.get("countFollower");
//        model.addAttribute("countFollowerMap", countFollowerMap);
//        model.addAttribute("countFollowerCount", countFollowerCount);
//
//        // posting 수
//        Map<String, Long> countPostingMap = userService.getCountPosting(userId);
//        Long countPostingCount = countPostingMap.get("countPosting");
//        model.addAttribute("countPostingMap", countPostingMap);
//        model.addAttribute("countPostingCount", countPostingCount);
//
//        // user prfImg
//        Set<UserImg> userImg = userService.findUserImgByUserId(userId);
//        model.addAttribute("userImg", userImg);
//
//        // 게시물 좋아요 수
//        Map<String, Long> countPostingLike = postingLikeService.countPostingLikeByUserId(userId);
//        Long countPostingLikeCount = countPostingLike.get("countPostingLike");
//        model.addAttribute("countPostingLike", countPostingLike);
//        model.addAttribute("countPostingLikeCount", countPostingLikeCount);
//
//        // 템플릿에 user, followerCounts, followeeCounts를 전달
//        return "posting/userpage";
//    }

    @GetMapping("/{userId}/userpage.do")
    public ResponseEntity<Map<String, Object>> userpage(
            @RequestParam String userId,
            Model model
    ) {
        Optional<User> userOptional = userService.readOne(userId);
        if (!userOptional.isPresent()) {
            return ResponseEntity.badRequest().build();
        }
        User user = userOptional.get();
        Map<String, Object> result = new HashMap<>();
        Map<String, Long> countFolloweeMap = userService.getCountFollowee(userId);
        Map<String, Long> countFollowerMap = userService.getCountFollower(userId);
        Map<String, Long> countPostingMap = userService.getCountPosting(userId);
        Set<UserImg> userImg = userService.findUserImgByUserId(userId);
        result.put("user", user);
        result.put("countFolloweeMap", countFolloweeMap);
        result.put("countFollowerMap", countFollowerMap);
        result.put("countPostingMap", countPostingMap);
        result.put("userImg", userImg);

        return ResponseEntity.ok(result);
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

    // user의 전체 게시물
    @GetMapping("/{userId}/postpage.do")
    @ResponseBody
    public ResponseEntity<Set<Posting>> postpage(
            @PathVariable String userId) {
        Set<Posting> postings = postingService.findByUserId(userId);
        // return ResponseEntity.ok(postings);
        return ResponseEntity.status(201).body(postings);
    }

    // 한 게시물에 대한
    @GetMapping("/{userId}/postdetail.do")
    @ResponseBody
    public ResponseEntity<Posting> post(
            @ModelAttribute Posting posting
            ) {
        Posting postings = postingService.findByPostId(posting.getPostId());

        return ResponseEntity.status(201).body(postings);
    }


    @GetMapping("/{userId}/postAdd.do")
    public String postForm(
            @ModelAttribute Posting posting
            // Model model
    ){
         // Posting posting = new Posting();
         // model.addAttribute("posting", posting);
        // return "posting/postAdd";
        //  postingService.save(posting);
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
