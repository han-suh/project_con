package com.artu.fullstack_team_project_application.service.postings;

import com.artu.fullstack_team_project_application.entity.postings.Posting;
import com.artu.fullstack_team_project_application.entity.postings.PostingImage;
import com.artu.fullstack_team_project_application.entity.postings.PostingLike;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface PostingService {

    @Transactional
    // posting 등록 및 수정
    // Posting save(Posting posting);

    // posting 삭제
    // void remove(Posting posting);

    // posting 리스트
    // Page<Posting> findAll(Pageable pageable);

    // user 게시글
    Set<Posting> findByUserId(String userId);

    // post_Id
    // Set<Posting> findByPostId(Integer postId);

    // 게시글 이미지
    // List<PostingImage> findPostingImageByPost_PostId(Integer postId);

    // 게시글 user 이미지
    // List<PostingImage> findPostingImageByUser_UserId(String userId);


}
