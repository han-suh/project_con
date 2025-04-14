package com.artu.fullstack_team_project_application.repository.postings;

import com.artu.fullstack_team_project_application.entity.postings.PostingComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface PostingCommentRepository extends JpaRepository<PostingComment, Integer> {
    Set<PostingComment> findByPost_PostId(Integer postPostId);

}
