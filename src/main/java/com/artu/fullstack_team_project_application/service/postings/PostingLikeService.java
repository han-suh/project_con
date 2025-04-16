package com.artu.fullstack_team_project_application.service.postings;

import com.artu.fullstack_team_project_application.entity.postings.PostingLike;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.Set;

public interface PostingLikeService {
    @Transactional
    public void save(Integer postId, String userId);
    public void delete(Integer postId, String userId);

    // 좋아요 중복 방지
    boolean existsByUserIdAndPostId(String userId, Integer postId);

    // 유저의 좋아요 목록
    Set<PostingLike> findAllByUserId(String userId);

    // 게시물 좋아요 카운트
    Map<String, Long> countPostingLikeByUserId(String userId);

}
