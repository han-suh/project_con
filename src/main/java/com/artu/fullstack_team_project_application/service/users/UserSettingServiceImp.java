package com.artu.fullstack_team_project_application.service.users;

import com.artu.fullstack_team_project_application.entity.users.user.UserSetting;
import com.artu.fullstack_team_project_application.repository.users.UserSettingRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@AllArgsConstructor
public class UserSettingServiceImp implements UserSettingService {
    private final UserSettingRepository userSettingRepository;

    @Override
    public void save(UserSetting userSetting){
        userSettingRepository.save(userSetting);
    }

    @Override
    public void delete(UserSetting userSetting) {
        userSettingRepository.delete(userSetting);
    }

    @Override
    public Optional<UserSetting> findOne(String userId) {
        return userSettingRepository.findOneByUser_UserId(userId);
    }

}
