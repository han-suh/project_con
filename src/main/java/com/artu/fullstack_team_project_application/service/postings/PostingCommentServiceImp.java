package com.artu.fullstack_team_project_application.service.postings;

import com.artu.fullstack_team_project_application.entity.postings.Posting;
import com.artu.fullstack_team_project_application.entity.postings.PostingComment;
import com.artu.fullstack_team_project_application.entity.users.user.User;
import com.artu.fullstack_team_project_application.repository.postings.PostingCommentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
@Transactional
public class PostingCommentServiceImp implements PostingCommentService{
    private final PostingCommentRepository postingCommentRepository;

    @Override
    public PostingComment save(Posting post, String content, User user) {
        if (post != null && content != null && user != null) {
        PostingComment postingComment = new PostingComment();
        postingComment.setPost(post);
        postingComment.setUser(user);
        postingComment.setContents(content);
        postingComment.setCreatedAt(Instant.now());
        postingComment.setIsUsed(true);
        return postingCommentRepository.save(postingComment);
        }else {
            throw new IllegalArgumentException("댓글 불가");
        }
    }


//    @Override
//    public void saveComment(PostingComment postingComment){
//        postingComment.setCreatedAt(Instant.now());
//        postingComment.setIsUsed(true);
//        postingCommentRepository.save(postingComment);
//    }

//    @Override
//    public void deleteComment(Integer commentId, String content) {
//
//    }

//    @Override
//    public void deleteComment(Integer commentId){
//        PostingComment postingComment = new PostingComment();
//        postingComment.setCommentId(commentId);
//        postingCommentRepository.delete(postingComment);
//    }

    @Override
    public PostingComment delete(Integer commentId) {
        PostingComment postingComment = postingCommentRepository.findById(commentId).orElseThrow(()->new NoSuchElementException("없음"));
        postingCommentRepository.delete(postingComment);
        return postingComment;
    }

//    @Override
//    public Set<PostingComment> findByPost_PostId(Integer postId) {
//        return postingCommentRepository.findByPost_PostId(postId);
//    }

    @Override
    public Optional<PostingComment> findByCommentId(Integer commentId) {
        return postingCommentRepository.findById(commentId);
    }

    @Override
    public Set<PostingComment> findByUser_UserId(String userId) {
        return postingCommentRepository.findByUser_UserId(userId);
    }

    @Override
    public Set<PostingComment> findCommentsByPost_PostId(Integer postId) {
        return postingCommentRepository.findCommentsByPost_PostId(postId);
    }

}
