package com.artu.fullstack_team_project_application.controller;

import com.artu.fullstack_team_project_application.entity.postings.UserFollow;
import com.artu.fullstack_team_project_application.entity.postings.UserFollowId;
import com.artu.fullstack_team_project_application.entity.users.user.User;
import com.artu.fullstack_team_project_application.entity.users.user.UserSetting;
import com.artu.fullstack_team_project_application.service.postings.PostingService;
import com.artu.fullstack_team_project_application.service.users.UserService;
import com.artu.fullstack_team_project_application.service.users.UserServiceImp;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.Instant;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@Controller //@RestController
@RequestMapping("posting/{userId}")
@AllArgsConstructor
public class UserFollowController {
    private final UserServiceImp userService;
    private final PostingService postingService;

    @GetMapping("/follower.do")
    public String follower(
            @PathVariable String userId,
            @RequestParam(required = false) String followerId,
            @RequestParam(required = false) String followeeId,
            Model model
    ) {

        Set<UserFollow> findByFollowerId = userService.findByFollowerId(followerId);
        Set<UserFollow> findByFolloweeId = userService.findByFolloweeId(followeeId);
        Optional<User> userOptional = userService.findByUserId(userId);

        User user = userOptional.get();
        model.addAttribute("user", user);
        model.addAttribute("findByFollowerId", findByFollowerId);
        model.addAttribute("findByFolloweeId", findByFolloweeId);

        return "posting/follower"; // follower.html로 이동
    }




    @GetMapping("/followee.do")
    public String followee(
            @PathVariable String userId,
            @RequestParam(required = false) String followeeId,
            @RequestParam(required = false) String followerId,
            Model model
    ) {
        Set<UserFollow> findByFollowerId = userService.findByFollowerId(followerId);
        Set<UserFollow> findByFolloweeId = userService.findByFolloweeId(followeeId);
        Optional<User> userOptional = userService.findByUserId(userId);


        User user = userOptional.get();
        model.addAttribute("user", user);
        model.addAttribute("findByFollowerId", findByFollowerId);
        model.addAttribute("findByFolloweeId", findByFolloweeId);
        return "posting/followee"; // followee.html로 이동
    }

//    @DeleteMapping("/followee.do")
//    public ResponseEntity<void> userUnfollow(
//            @PathVariable String userId,
//            @RequestParam(required = false) String followeeId,
//            @RequestParam(required = false) String followerId,
//            Model model
//    ) {
//        return ResponseEntity.ok().build();
//    }

    @PostMapping("/followee.do")
    public ResponseEntity<String> userFollowRegister(@RequestBody UserFollowId followId) {
        userService.registerFollow(followId);
        return ResponseEntity.ok("Success");
    }

    @DeleteMapping("/followee.do")
    public ResponseEntity<String> deleteFollower(
            @RequestBody UserFollowId followId
            ) {
        System.out.println("deleteFollower: "+followId);

        userService.removeFollow(followId);
        return ResponseEntity.ok("Success");
    }
}
//@PostMapping("setting.do")
//public String saveUserSetting(
//        @PathVariable String userId,
//        @ModelAttribute UserSetting userSetting,
//        RedirectAttributes redirectAttributes
//) {
//    User user = userService.findByUserId(userId).orElseThrow(() -> new RuntimeException("찾을 수 없습니다."));
//    UserSetting setting = userSettingService.findOne(userId).orElse(new UserSetting());
//
//    setting.setUser(user);
//    setting.setSettingId(setting.getSettingId());
//    setting.setDisplayColor(userSetting.getDisplayColor());
//    setting.setLanguage(userSetting.getLanguage());
//    setting.setSetAt(Instant.now());
//    userSettingService.save(setting);
//
////        return ResponseEntity.ok("Setting saved");
//    redirectAttributes.addFlashAttribute("message", "설정 완료");
//    return "redirect:/posting/" + userId + "/setting.do";
//}