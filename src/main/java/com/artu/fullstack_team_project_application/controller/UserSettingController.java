package com.artu.fullstack_team_project_application.controller;

import com.artu.fullstack_team_project_application.entity.users.user.User;
import com.artu.fullstack_team_project_application.entity.users.user.UserSetting;
import com.artu.fullstack_team_project_application.service.users.UserService;
import com.artu.fullstack_team_project_application.service.users.UserSettingService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Optional;

@Controller //@RestController
@RequestMapping("posting/{userId}")
@AllArgsConstructor
public class UserSettingController {
    private final UserSettingService userSettingService;
    private final UserService userService;

    @GetMapping("setting.do")
    public String setting(
            Model model,
            @PathVariable String userId
    ) {
        Optional<UserSetting> findOne = userSettingService.findOne(userId);
        Optional<User> userOptional = userService.findByUserId(userId);
//        if (findOne.isPresent() && userOptional.isPresent()) {
        User user = userOptional.get();
        model.addAttribute("user", user);
        model.addAttribute("userId", userId);
        model.addAttribute("userSetting", findOne.orElse(null));
//        }
        return "posting/setting";
    }

    @PostMapping("setting.do")
//    public ResponseEntity<String> saveUserSetting(
    public String saveUserSetting(
            @PathVariable String userId,
            @ModelAttribute UserSetting userSetting,
            RedirectAttributes redirectAttributes
            ) {
        User user = userService.findByUserId(userId).orElseThrow(() -> new RuntimeException("찾을 수 없습니다."));
        UserSetting setting = userSettingService.findOne(userId).orElse(new UserSetting());

        setting.setUser(user);
        setting.setSettingId(setting.getSettingId());
        setting.setDisplayColor(userSetting.getDisplayColor());
        setting.setLanguage(userSetting.getLanguage());
        setting.setSetAt(Instant.now());
        userSettingService.save(setting);

//        return ResponseEntity.ok("Setting saved");
        redirectAttributes.addFlashAttribute("message", "설정 완료");
        return "redirect:/posting/" + userId + "/setting.do";
    }


}

//       if (userOptional.isPresent() && userOptional.get().getPassword().equals(password)) {
//        return true;
//        }
//        return false;
