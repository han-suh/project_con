package com.artu.fullstack_team_project_application.repository.postings;

import com.artu.fullstack_team_project_application.entity.postings.Posting;
import com.artu.fullstack_team_project_application.entity.postings.PostingLike;
import com.artu.fullstack_team_project_application.entity.postings.PostingLikeId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface PostingLikeRepository extends JpaRepository<PostingLike, PostingLikeId> {
    // 유저의 좋아요 목록
//    @Query("SELECT * FROM PostingLike pl WHERE Posting p")
    Set<PostingLike> findAllByUserId(String userId);

    // 게시물 좋아요 카운트
    @Query("SELECT COUNT(pl.userId) FROM PostingLike pl GROUP BY pl.postId")
    Long countPostingLikes(@Param("userId") String userId);

    // 좋아요 중복 방지
    boolean existsByUserIdAndPostId(String userId, Integer postId);

    // 좋아요 찾기
    Optional<PostingLike> findByPostIdAndUserId(Integer postId, String userId);



}
