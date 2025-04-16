package com.artu.fullstack_team_project_application.service.postings;

import com.artu.fullstack_team_project_application.entity.postings.Posting;
import com.artu.fullstack_team_project_application.entity.postings.PostingLike;
import com.artu.fullstack_team_project_application.repository.postings.PostingLikeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;


@Service
@AllArgsConstructor
public class PostingLikeServiceImp implements PostingLikeService {
    private PostingLikeRepository postingLikeRepository;

    @Override
    public void save(Integer postId, String userId) {
        if (postId != null && userId != null) {
            PostingLike postingLike = new PostingLike();
            postingLike.setIsUsed(true);
        }else {
            PostingLike postingLike = new PostingLike();
            postingLike.setPostId(postId);
            postingLike.setUserId(userId);
            postingLike.setIsUsed(true);
            postingLike.setLikedAt(Instant.now());
            postingLikeRepository.save(postingLike);
        }
    }

    @Override
    public void delete(Integer postId, String userId) {
        // PostingLike postingLike = postingLikeRepository.findByPostIdAndUserId(postId, userId)
        PostingLike postingLike = new PostingLike();
        postingLike.setPostId(postId);
        postingLike.setUserId(userId);
        postingLikeRepository.delete(postingLike);
    }

    @Override
    public boolean existsByUserIdAndPostId(String userId, Integer postId) {
        return postingLikeRepository.existsByUserIdAndPostId(userId, postId);
    }

    @Override
    public Set<PostingLike> findAllByUserId(String userId) {
        return postingLikeRepository.findAllByUserId(userId);
    }

    @Override
    public Map<String, Long> countPostingLikeByUserId(String userId){
        Long countPostingLikes = postingLikeRepository.countPostingLikes(userId);
        Map<String, Long> countPostingLikeMap = new HashMap<>();
        countPostingLikeMap.put("countPostingLikes", countPostingLikes);
        return countPostingLikeMap;
    }

}
