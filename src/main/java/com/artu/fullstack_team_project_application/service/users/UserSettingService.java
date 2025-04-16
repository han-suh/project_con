package com.artu.fullstack_team_project_application.service.users;

import com.artu.fullstack_team_project_application.entity.users.user.UserSetting;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface UserSettingService {
    @Transactional
    public void save(UserSetting userSetting);
    public void delete(UserSetting userSetting);
    // 설정 확인
    public Optional<UserSetting> findOne(String userId);


}
