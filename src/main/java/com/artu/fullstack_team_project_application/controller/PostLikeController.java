package com.artu.fullstack_team_project_application.controller;

import com.artu.fullstack_team_project_application.repository.postings.PostingLikeRepository;
import com.artu.fullstack_team_project_application.repository.postings.PostingRepository;
import com.artu.fullstack_team_project_application.repository.users.UserRepository;
import jdk.jfr.Category;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/posting")
@AllArgsConstructor
public class PostLikeController {
    private PostingRepository postingRepository;
    private PostingLikeRepository postingLikeRepository;
    private UserRepository userRepository;

    @GetMapping()
    public String getPostLikes() {
        return "postLike";
    }

    @GetMapping("/likes")
    public String getPostLikesByUserId() {
        return "postLikeByUserId";
    }

}
