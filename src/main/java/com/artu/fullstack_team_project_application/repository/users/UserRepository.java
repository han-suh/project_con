package com.artu.fullstack_team_project_application.repository.users;

import com.artu.fullstack_team_project_application.entity.users.user.User;
import com.artu.fullstack_team_project_application.entity.users.user.UserImg;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    // 이름,메일,아이디로 일치한 사람 찾기
    @Query("SELECT u FROM User u WHERE LOWER(u.userName) LIKE LOWER(CONCAT('%', :searchuser, '%')) " +
            "OR LOWER(u.userEmail) LIKE LOWER(CONCAT('%', :searchuser, '%')) " +
            "OR LOWER(u.userId) LIKE LOWER(CONCAT('%', :searchuser, '%'))")
    List<User> searchUsers(@Param("searchuser") String searchuser);

}
