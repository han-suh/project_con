package com.artu.fullstack_team_project_application.service.postings;

import com.artu.fullstack_team_project_application.entity.postings.Posting;
import com.artu.fullstack_team_project_application.entity.postings.PostingComment;
import com.artu.fullstack_team_project_application.entity.postings.PostingImage;
import com.artu.fullstack_team_project_application.entity.postings.PostingLike;
import com.artu.fullstack_team_project_application.repository.postings.PostingImageRepository;
import com.artu.fullstack_team_project_application.repository.postings.PostingRepository;
import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.file.Files;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
@AllArgsConstructor
public class PostingServiceImp implements PostingService {
    private PostingRepository postingRepository;
    private final PostingImageRepository postingImageRepository;
    private final EntityManager entityManager;

    @Override
    public Posting save(Posting posting) {
        // 새로운 게시글 생성
        Posting postingSave = new Posting();

        // postingSave.setUserId(userId); // 보안 강화를 위해 userId는 서버에서 직접 설정
        // 공통 데이터 설정
        postingSave.setContents(posting.getContents());
        postingSave.setLocationTag(posting.getLocationTag());
        postingSave.setPersonTagId(posting.getPersonTagId());
        postingSave.setVisibilityType(posting.getVisibilityType());

        return postingRepository.save(postingSave);
    }
//
//    @Override
//    public void remove(Posting posting) {
//        postingRepository.delete(posting);
//    }
//
//    @Override
//    public Page<Posting> findAll(Pageable pageable) {
//        return null;
//    }
//
    @Override
    public Set<Posting> findByUserId(String userId) {
        return postingRepository.findByUser_UserId(userId);
    }

    // 게시물 등록
//    @Override
//    public void register(Posting posting) {
//        posting.setPostId(posting.getPostId());
//        posting.setUserId(posting.getUserId());
//        posting.setContents(posting.getContents());
//        posting.setLocationTag(posting.getLocationTag());
//        posting.setPersonTagId(posting.getPersonTagId());
//        posting.setVisibilityType(posting.getVisibilityType());
//        // postingRepository.save(posting);
//        return postingRepository.save(posting);
//    }






/*
        PostingImage postingImage = new PostingImage();
        PostingComment postingComment = new PostingComment();
        posting.setPostId(posting.getPostId());
        posting.setUserId(posting.getUserId());
        posting.setContents(posting.getContents());
        posting.setLocationTag(posting.getLocationTag());
        posting.setPersonTagId(posting.getPersonTagId());
        posting.setVisibilityType(posting.getVisibilityType());
        posting.setCreatedAt(posting.getCreatedAt());
        posting.setEditAt(posting.getEditAt());
        posting.setIsUsed(posting.getIsUsed());
        postingImage.setPost(postingImage.getPost());
        postingComment.setPost(postingComment.getPost());
        postingImage.setImgId(postingImage.getImgId());
        postingImage.setImgUrl(postingImage.getImgUrl());
        Files entityManager;
        Posting existPosting = entityManager.find(Posting.class,postId);
 */

//    @Override
//    public Set<Posting> findByPostId(Integer postId) {
//        return postingRepository.findByPostId(postId);
//    }
//
//    @Override
//    public List<PostingImage> findPostingImageByPost_PostId(Integer postId) {
//        return List.of();
//    }
//
//    @Override
//    public List<PostingImage> findPostingImageByUser_UserId(String userId) {
//        return List.of();
//    }




//    @Override
//    public Page<Posting> findAll(Pageable pageable) {
//        return null;
//    }

//    @Override
//    public List<Posting> findByUser_UserId(String userId) {
//        return postingRepository.findByUser_UserId(userId);
//    }

//    @Override
//    public List<PostingImage> findByPost_PostId(Posting postId) {
//        return postingImageRepository.findByPost_PostId(postId);
//    }

//    @Override
//    public Map<String, Long> getUserActivityCounts(Long userNum) {
//        return Map.of();
//    }

//    @Override
//    public List<PostingLike> readAllLikes() {
//        return List.of();
//    }


}
