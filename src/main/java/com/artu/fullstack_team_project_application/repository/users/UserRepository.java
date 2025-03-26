package com.artu.fullstack_team_project_application.repository.users;

import com.artu.fullstack_team_project_application.entity.users.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    // 이름,메일,아이디로 일치한 사람 찾기
    @Query("SELECT u " +
            "FROM User u " +
            "WHERE (:word IS NULL OR u.userName LIKE %:word%) " +
            "OR (:word IS NULL OR u.userEmail LIKE %:word%) " +
            "OR (:word IS NULL OR u.userId LIKE %:word%)")
      // word가 null인 경우 LIKE 검색이 발생하지 않도록
    List<User> searchUsers(@Param("searchuser") String searchuser);
}
//            "WHERE u.userName LIKE %:word% " +
//            "OR u.userEmail LIKE %:word% " +
//            "OR u.userId LIKE %:word%")
