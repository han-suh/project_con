package com.artu.fullstack_team_project_application.service.users;

import com.artu.fullstack_team_project_application.entity.postings.UserFollow;
import com.artu.fullstack_team_project_application.entity.users.user.User;
import com.artu.fullstack_team_project_application.entity.users.user.UserInterest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public interface UserService {


    // 관리자 페이지, 회원 검색
    public Page<User> readAll(Pageable pageable);

    //
    public Optional<User> readOne(String userId);

    public User save(User user);

    public void delete(String userId);

//    public List<UserInterest> readInterests(String userId);
//    public List<UserInterest> saveInterests(UserInterest userInterest);

//    // follow 리스트
//    List<UserFollow> findByFolloweeId(String userId);
//    List<UserFollow> findByFollowerId(String userId);

    // follow 수
    Map<String, Long> getCountFollower(String followeeId);
    Map<String, Long> getCountFollowee(String followerId);

    List<User> findAll();
    Set<UserFollow> findByFollowerId(String followerId);
    Set<UserFollow> findByFolloweeId(String followeeId);

    Optional<User> findByUserId(String userId);

    // User 검색
    List<User> searchUsers(@Param("word") String word);

}
