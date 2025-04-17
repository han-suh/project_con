package com.artu.fullstack_team_project_application.service.users;

import com.artu.fullstack_team_project_application.entity.postings.UserFollow;
import com.artu.fullstack_team_project_application.entity.postings.UserFollowId;
import com.artu.fullstack_team_project_application.entity.users.user.User;
import com.artu.fullstack_team_project_application.entity.users.user.UserImg;
import com.artu.fullstack_team_project_application.entity.users.user.UserInterest;
import com.artu.fullstack_team_project_application.repository.postings.PostingRepository;
import com.artu.fullstack_team_project_application.repository.postings.UserFollowRepository;
import com.artu.fullstack_team_project_application.repository.users.UserImageRepository;
import com.artu.fullstack_team_project_application.repository.users.UserRepository;
import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Slf4j
@Service
@AllArgsConstructor
@Transactional
public class UserServiceImp implements UserService {

    private final UserRepository userRepository;
    private final UserFollowRepository userFollowRepository;
    private final PostingRepository postingRepository;
    private final UserImageRepository userImageRepository;
    private final EntityManager entityManager;

    @Override
    public Page<User> readAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public Optional<User> readOne(String userId) {
        return userRepository.findById(userId);
    }

//    @Override
//    public void registerFollow(String followeeId, String followerId) {
//        //
////        User followee = userRepository.findById(followeeId).orElseThrow();
////        User follower = userRepository.findById(followerId).orElseThrow();
////        User followedAt = userRepository.
//
//        UserFollow userFollow = new UserFollow();
////        userFollow.setFollowers(follower);
////        userFollow.setFollowees(followee);
//        userFollow.setFollowerId(followerId);
//        userFollow.setFolloweeId(followeeId);
////        userFollow.setFollowedAt();
//        UserFollowId userFollowId = new UserFollowId();
//        userFollowId.setFollowerId(followerId);
//        userFollowId.setFolloweeId(followeeId);
//        userFollowRepository.save(userFollow);
//    }

    @Override
    public void registerFollow(UserFollowId userFollowId) {
        UserFollow userFollow = entityManager.find(UserFollow.class, userFollowId);
        System.out.println("userFollowId = " + userFollowId);
        if(userFollowId != null) {
                userFollow.setIsUsed(true);
                System.out.println("true로 변경");
            userFollowRepository.save(userFollow);
        }
        else {
            userFollow = new UserFollow();
            userFollow.setFolloweeId(userFollowId.getFolloweeId());
            userFollow.setFollowerId(userFollowId.getFollowerId());
            userFollow.setIsUsed(true);
            System.out.println("신규");
            userFollowRepository.save(userFollow);
        }
    }


    @Override
    @Transactional
    public void removeFollow(UserFollowId userFollowId) {
        UserFollow userFollow = entityManager.find(UserFollow.class, userFollowId);
        System.out.println(userFollow.getFolloweeId());
        if(userFollow==null)throw new IllegalArgumentException("없습니다.");
//        entityManager.remove(userFollow);
        entityManager.remove(userFollow);
//        userFollow.setIsUsed(Boolean.FALSE);
    }


    @Override
    public User save(User user) {
        return user;
    }

    @Override
    public void delete(String userId) {
    }

    //
    @Override
    public Map<String, Long> getCountFollower(String followeeId) {
        Long countFollower = userFollowRepository.countFolloweeByUserId(followeeId);
        Map<String, Long> countFollowerMap = new HashMap<>();
        countFollowerMap.put("countFollower", countFollower);
        return countFollowerMap;
    }

    @Override
    public Map<String, Long> getCountFollowee(String followerId) {
        Long countFollowee = userFollowRepository.countFollowerByUserId(followerId);
        Map<String, Long> countFolloweeMap = new HashMap<>();
        countFolloweeMap.put("countFollowee", countFollowee);
        return countFolloweeMap;
    }

    @Override
    public Map<String, Long> getCountPosting(String userId) {
        Long countPosting = postingRepository.countpostingByUserId(userId);
        Map<String, Long> countPostingMap = new HashMap<>();
        countPostingMap.put("countPosting", countPosting);
        return countPostingMap;
    }

    @Override
    public Set<UserImg> findUserImgByUserId(String userId) {
        return userImageRepository.findUserImgByUser_UserId(userId);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findByUserId(String userId) {
        return userRepository.findById(userId);
    }

    // 이름,메일,아이디로 일치한 사람 찾기
    @Override
    public List<User> searchUsers(String searchuser) {
        if (searchuser == null || searchuser.isEmpty()) {
            return new ArrayList<>();  // 검색어가 없을 때 빈 리스트 반환
        }
//        String searchTerm = "%" + searchuser + "%"; // LIKE 검색을 위한 % 추가
        return userRepository.searchUsers(searchuser);
    }


    @Override
    @Transactional(readOnly = true)
    public Set<UserFollow> findByFollowerId(String followerId) {
        return userFollowRepository.findByFollowerIdAndIsUsedTrue(followerId);
    }

    @Override
    @Transactional(readOnly = true)
    public Set<UserFollow> findByFolloweeId(String followeeId) {
        return userFollowRepository.findByFolloweeIdAndIsUsedTrue(followeeId);
    }

}
