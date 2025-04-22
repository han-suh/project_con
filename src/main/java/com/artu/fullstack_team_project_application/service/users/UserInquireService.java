package com.artu.fullstack_team_project_application.service.users;


import com.artu.fullstack_team_project_application.entity.users.base.UserInquire;
import com.artu.fullstack_team_project_application.entity.users.user.User;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface UserInquireService {
    void save(UserInquire userInquire);
    void delete(String userId, Integer inquireId);
    List<UserInquire> findAll();
    Set<UserInquire> findInquireByUserId(String userId);
    Set<UserInquire> findInquireByInquireCategory(UserInquire.InquireCategory inquireCategory);
    Optional<UserInquire> findOneByInquireIdAndUser_UserId(Integer inquireId, String userId);

}
