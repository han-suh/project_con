package com.artu.fullstack_team_project_application.service.postings;

import com.artu.fullstack_team_project_application.entity.postings.PostingLike;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface PostingLikeService {
    @Transactional
    public PostingLike save(Integer postId, String userId);
    public PostingLike delete(Integer postId, String userId);

    // 유저의 좋아요 목록
    Set<PostingLike> findAllByUserId(String userId);

    // 게시물 좋아요 카운트
    Map<String, Long> countPostingLikeByUserId(String userId);

    // 좋아요 중복 방지
    boolean existsByUserIdAndPostId(String userId, Integer postId);

    // 찾기
    // Optional<PostingLike> findByPostIdAndUserId(String userId, Integer postId);



}
