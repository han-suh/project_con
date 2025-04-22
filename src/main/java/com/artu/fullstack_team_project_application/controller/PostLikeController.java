package com.artu.fullstack_team_project_application.controller;

import com.artu.fullstack_team_project_application.entity.postings.Posting;
import com.artu.fullstack_team_project_application.entity.postings.PostingLike;
import com.artu.fullstack_team_project_application.repository.postings.PostingLikeRepository;
import com.artu.fullstack_team_project_application.repository.postings.PostingRepository;
import com.artu.fullstack_team_project_application.repository.users.UserRepository;
import com.artu.fullstack_team_project_application.service.postings.PostingLikeService;
import jdk.jfr.Category;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Set;

//@Controller
@RestController
@RequestMapping("/posting")
@AllArgsConstructor
public class PostLikeController {
    private final PostingLikeService postingLikeService;
    private PostingRepository postingRepository;
    private PostingLikeRepository postingLikeRepository;
    private UserRepository userRepository;

//   @PostMapping("/postlikepage.do")
//    @PostMapping("/{userId}/postlikepage.do")
//    public String savePostLike (@RequestParam Integer postId, @PathVariable String userId) {
//        postingLikeService.save(postId, userId);
//        return "redirect:/posts/" + postId;
//    }
//
//    @GetMapping("/likes")
//    public String getPostLikesByUserId() {
//        return "postLikeByUserId";
//    }

    @PostMapping("/postlikepage.do")
    public ResponseEntity<PostingLike> savePostLike (
            @RequestParam Integer postId,
            @RequestParam String userId
    ) {
        PostingLike postLike = postingLikeService.save(postId, userId);
        return ResponseEntity.ok(postLike);
    }

    @DeleteMapping("/postlikepage.do")
    public ResponseEntity<PostingLike> deletePostLike (
            @RequestParam Integer postId,
            @RequestParam String userId
    ) {
        postingLikeService.delete(postId, userId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/like/post.do")
    public ResponseEntity<Set<PostingLike>> findAllByUserId (@RequestParam String userId) {
        Set<PostingLike> likes = postingLikeService.findAllByUserId(userId);
        for (PostingLike like : likes) {
            like.getPost().getContents();
        }
        return ResponseEntity.ok(likes);
    }

//    @GetMapping("/like/post.do")
//    public ResponseEntity<Set<PostingLike>> findAllByUserId(Authentication authentication) {
//        String userId = authentication.getName();
//        Set<PostingLike> likes = postingLikeService.findAllByUserId(userId);
//        return ResponseEntity.ok(likes);
//    }

    @GetMapping("/like/count.do")
    public ResponseEntity<Map<String, Long>> countPostingLikeByUserId (@RequestParam String userId) {
        Map<String, Long> countPostingLikeMap = postingLikeService.countPostingLikeByUserId(userId);
        return ResponseEntity.ok(countPostingLikeMap);
    }

    @GetMapping("/like/check.do")
    public ResponseEntity<Boolean> existsByUserIdAndPostId (@RequestParam String userId, @RequestParam Integer postId) {
        boolean exists = postingLikeService.existsByUserIdAndPostId(userId, postId);
        return ResponseEntity.ok(exists);
    }

    @GetMapping("/like/exists.do")
    public ResponseEntity<Map<String, Object>> existsByUserIdAndPostIdResponse (@RequestParam String userId, @RequestParam Integer postId) {
        boolean exists = postingLikeService.existsByUserIdAndPostId(userId, postId);
        Map<String, Object> result = Map.of("exists", exists, "postId", postId, "userId", userId);
        return ResponseEntity.ok(result);
    }



}
