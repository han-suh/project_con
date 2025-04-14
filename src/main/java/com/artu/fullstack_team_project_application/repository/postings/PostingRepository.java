package com.artu.fullstack_team_project_application.repository.postings;

import com.artu.fullstack_team_project_application.entity.postings.Posting;
import com.artu.fullstack_team_project_application.entity.users.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface PostingRepository extends JpaRepository<Posting, Integer> {
    // 논리적 삭제 목록 확인
     List<Posting> findByIsUsedFalse();

    // user의 게시글 조회
    Set<Posting> findByUser_UserId(String userId);

    // post_Id
    Posting findByPostId(Integer postId);


    // 위치 태그
    // List<Posting> findByLocationTag(String locationTag);

    // 사람 태그
    // List<Posting> findByPersonTagId(String personTagId);

    // posting 수
    @Query("SELECT COUNT(p.postId) " +
            "FROM User u LEFT JOIN Posting p " +
            "ON u.userId = p.userId " +
            "WHERE u.userId = :userId")
    Long countpostingByUserId(@Param("userId") String userId);

    // postImg




}
