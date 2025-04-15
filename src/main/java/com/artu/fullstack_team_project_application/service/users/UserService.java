package com.artu.fullstack_team_project_application.service.users;

import com.artu.fullstack_team_project_application.entity.postings.UserFollow;
import com.artu.fullstack_team_project_application.entity.users.user.User;
import com.artu.fullstack_team_project_application.entity.users.user.UserImg;
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

    // follow 추가/삭제
    void registerFollow(String followeeId, String followerId);
    void removeFollow(String followeeId, String followerId);

    public User save(User user);

    public void delete(String userId);


    // follow 수
    Map<String, Long> getCountFollower(String followeeId);
    Map<String, Long> getCountFollowee(String followerId);

    // follow 리스트
    List<User> findAll();
    Set<UserFollow> findByFollowerId(String followerId);
    Set<UserFollow> findByFolloweeId(String followeeId);

    Optional<User> findByUserId(String userId);

    // 이름,메일,아이디로 일치한 사람 찾기
    List<User> searchUsers(@Param("searchuser") String searchuser);

    // posting 수
    Map<String, Long> getCountPosting(String userId);

    Set<UserImg> findUserImgByUserId(String userId);

}
