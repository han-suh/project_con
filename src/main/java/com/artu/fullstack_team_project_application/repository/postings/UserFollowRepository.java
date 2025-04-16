package com.artu.fullstack_team_project_application.repository.postings;

import com.artu.fullstack_team_project_application.entity.postings.UserFollow;
import com.artu.fullstack_team_project_application.entity.postings.UserFollowId;
import com.artu.fullstack_team_project_application.entity.users.user.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface UserFollowRepository extends JpaRepository<UserFollow, UserFollowId> {
    @EntityGraph(attributePaths = {"followers", "followees"})

//    @Query("SELECT uf FROM UserFollow uf JOIN FETCH uf.followerId = : followerId AND uf.isUsed = true")
    Set<UserFollow> findByFollowerIdAndIsUsedTrue(String followerId);

//    @Query("SELECT uf FROM UserFollow uf JOIN FETCH uf.followeeId = : followeeId AND uf.isUsed = true")
    Set<UserFollow> findByFolloweeIdAndIsUsedTrue(String followeeId);

//    boolean existsFollowerIdByFolloweeId(String followerId, String followeeId);

    // follow 수
    @Query("SELECT COUNT(uf.followeeId) " +
            "FROM User u LEFT JOIN UserFollow uf " +
            "ON u.userId = uf.followerId " +
            "WHERE u.userId = :followerId " +
            "GROUP BY u.userId")
    Long countFolloweeByUserId(@Param("followerId") String followerId);

    @Query("SELECT COUNT(uf.followerId) " +
            "FROM User u LEFT JOIN UserFollow uf " +
            "ON u.userId = uf.followeeId " +
            "WHERE u.userId = :followeeId " +
            "GROUP BY u.userId")
    Long countFollowerByUserId(@Param("followeeId") String followeeId);

    // void removeUserFollowByFolloweeId(String followeeId);


}
