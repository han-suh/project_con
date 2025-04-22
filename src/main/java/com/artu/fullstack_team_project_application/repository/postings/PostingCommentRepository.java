package com.artu.fullstack_team_project_application.repository.postings;

import com.artu.fullstack_team_project_application.entity.postings.PostingComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface PostingCommentRepository extends JpaRepository<PostingComment, Integer> {
    // 게시물 해당 댓글
//    Set<PostingComment> findByPost_PostId(Integer postPostId);

    // 해당 댓글 찾기
    Optional<PostingComment> findByCommentId(Integer commentId);

    // 유저의 댓글
    Set<PostingComment> findByUser_UserId(String userId);


    @Query("SELECT c  " +
            "FROM PostingComment c " +
            "LEFT JOIN c.post p " +
            "WHERE p.postId= :postId AND c.isUsed = true")
    Set<PostingComment> findCommentsByPost_PostId(@Param("postId") Integer postId);


}
