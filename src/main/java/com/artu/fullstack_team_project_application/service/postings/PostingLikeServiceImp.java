package com.artu.fullstack_team_project_application.service.postings;

import com.artu.fullstack_team_project_application.entity.postings.Posting;
import com.artu.fullstack_team_project_application.entity.postings.PostingLike;
import com.artu.fullstack_team_project_application.repository.postings.PostingLikeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.*;


@Service
@AllArgsConstructor
@Transactional
public class PostingLikeServiceImp implements PostingLikeService {
    private PostingLikeRepository postingLikeRepository;

    @Override
    public PostingLike save(Integer postId, String userId) {
        if (postId != null && userId != null) {
            PostingLike postingLike = new PostingLike();
            postingLike.setPostId(postId);
            postingLike.setUserId(userId);
            postingLike.setIsUsed(true);
            postingLike.setLikedAt(Instant.now());
            return postingLikeRepository.save(postingLike);
        }else {
            throw new IllegalArgumentException("저장 불가");
        }
    }

    @Override
    public PostingLike delete(Integer postId, String userId) {
         PostingLike postingLike = postingLikeRepository.findByPostIdAndUserId(postId, userId).orElseThrow(() -> new NoSuchElementException("좋아요 없음"));
//        PostingLike postingLike = new PostingLike();
//        postingLike.setPostId(postId);
//        postingLike.setUserId(userId);
        postingLikeRepository.delete(postingLike);
        return postingLike;
    }

    // 유저의 좋아요 목록
    @Override
    public Set<PostingLike> findAllByUserId(String userId) {
        return postingLikeRepository.findAllByUserId(userId);
    }

    // 게시물 좋아요 카운트
    @Override
    public Map<String, Long> countPostingLikeByUserId(String userId){
        Long countPostingLikes = postingLikeRepository.countPostingLikes(userId);
        Map<String, Long> countPostingLikeMap = new HashMap<>();
        countPostingLikeMap.put("countPostingLikes", countPostingLikes);
        return countPostingLikeMap;
    }

    // 좋아요 중복 방지
    @Override
    public boolean existsByUserIdAndPostId(String userId, Integer postId) {
        return postingLikeRepository.existsByUserIdAndPostId(userId, postId);
    }



//    @Override
//    public List<PostingLike> findOneByPostIdAndUserId(String userId, Integer postId){
//        return postingLikeRepository.findByPostIdAndUserId(postId, userId);
//    }

}
