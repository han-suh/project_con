package com.artu.fullstack_team_project_application.repository.postings;

import com.artu.fullstack_team_project_application.entity.postings.Posting;
import com.artu.fullstack_team_project_application.entity.postings.PostingImage;
import com.artu.fullstack_team_project_application.entity.users.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostingImageRepository extends JpaRepository<PostingImage, Integer> {
    // 게시글 이미지 조회
    List<PostingImage> findPostingImageByPost_PostId(Integer postId);

    // 게시글 user 이미지 조회
    // List<PostingImage> findPostingImageByUser_UserId(String userId);

}
