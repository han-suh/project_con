package com.artu.fullstack_team_project_application.repository.users;

import com.artu.fullstack_team_project_application.entity.users.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    @Query("SELECT u " +
            "FROM User u " +
            "WHERE u.userName LIKE %:word% " +
            "OR u.userEmail LIKE %:word% " +
            "OR u.userId LIKE %:word%")
    List<User> searchUsers(@Param("word") String word);

}
