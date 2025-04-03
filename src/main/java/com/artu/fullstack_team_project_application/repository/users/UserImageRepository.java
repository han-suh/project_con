package com.artu.fullstack_team_project_application.repository.users;

import com.artu.fullstack_team_project_application.entity.users.user.User;
import com.artu.fullstack_team_project_application.entity.users.user.UserImg;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface UserImageRepository extends JpaRepository<UserImg, Integer> {
    List<UserImg> findUserImgByPrfImgId(Integer prfImgId);

    Set<UserImg> findUserImgByUser_UserId(String userId);

}
