package com.artu.fullstack_team_project_application.service.users;

import com.artu.fullstack_team_project_application.entity.postings.UserFollow;
import com.artu.fullstack_team_project_application.entity.users.user.User;
import com.artu.fullstack_team_project_application.entity.users.user.UserInterest;
import com.artu.fullstack_team_project_application.repository.postings.UserFollowRepository;
import com.artu.fullstack_team_project_application.repository.users.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@AllArgsConstructor
public class UserServiceImp implements UserService {

    private final UserRepository userRepository;
    private final UserFollowRepository userFollowRepository;

    @Override
    public Page<User> readAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public Optional<User> readOne(String userId) {
        return userRepository.findById(userId);
    }

    @Override
    public User save(User user) {
        return user;
    }

    @Override
    public void delete(String userId) {
    }
//
//    @Override
//    public List<UserInterest> readInterests(String userId) {
//        return List.of();
//    }
//
//    @Override
//    public List<UserInterest> saveInterests(UserInterest userInterest) {
//        return List.of();
//    }

//    @Override
//    public List<UserFollow> findByFolloweeId(String userId) {
//        return userFollowRepository.findByFolloweeId(userId);
//    }
//
//    @Override
//    public List<UserFollow> findByFollowerId(String userId) {
//        return userFollowRepository.findByFollowerId(userId);
//    }

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
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findByUserId(String userId) {
        return userRepository.findById(userId);
    }

    // user 검색
    @Override
    public List<User> searchUsers(String word) {
        String searchTerm = "%" + word + "%"; // LIKE 검색을 위한 % 추가
        return userRepository.searchUsers(searchTerm);
    }


    @Override
    @Transactional(readOnly = true)
    public Set<UserFollow> findByFollowerId(String followerId) {
        return userFollowRepository.findByFollowerId(followerId);
    }

    @Override
    @Transactional(readOnly = true)
    public Set<UserFollow> findByFolloweeId(String followeeId) {
        return userFollowRepository.findByFolloweeId(followeeId);
    }

    //
//    public UserService(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }
//
//    public List<User> getAllUsers() {
//        return userRepository.findAll();
//    }
//
//    public Optional<User> getUserById(String userId) {
//        return userRepository.findById(userId);
//    }
//
//    public User saveUser(User user) {
//        return userRepository.save(user);
//    }
//
//    public void deleteUser(String userId) {
//        userRepository.deleteById(userId);
//    }
}
