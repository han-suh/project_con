package com.artu.fullstack_team_project_application.repository.users;

import com.artu.fullstack_team_project_application.entity.users.base.UserInquire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface UserInquireRepository extends JpaRepository<UserInquire, Integer> {

    Set<UserInquire> findByUser_UserId(String userId);

    Optional<UserInquire> findOneByUser_UserId(String userId);

    Set<UserInquire> findByInquireCategory(UserInquire.InquireCategory inquireCategory);

}
