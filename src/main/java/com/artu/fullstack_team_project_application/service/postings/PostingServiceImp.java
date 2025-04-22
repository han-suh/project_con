package com.artu.fullstack_team_project_application.service.postings;

import com.artu.fullstack_team_project_application.dto.PostingCommentsDto;
import com.artu.fullstack_team_project_application.entity.postings.Posting;
import com.artu.fullstack_team_project_application.entity.postings.PostingComment;
import com.artu.fullstack_team_project_application.entity.postings.PostingImage;
import com.artu.fullstack_team_project_application.entity.postings.PostingLike;
import com.artu.fullstack_team_project_application.entity.users.user.User;
import com.artu.fullstack_team_project_application.repository.postings.PostingCommentRepository;
import com.artu.fullstack_team_project_application.repository.postings.PostingImageRepository;
import com.artu.fullstack_team_project_application.repository.postings.PostingRepository;
import com.artu.fullstack_team_project_application.service.users.UserService;
import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.file.Files;
import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public
class PostingServiceImp implements PostingService {
    private PostingRepository postingRepository;
    private final PostingImageRepository postingImageRepository;
    private final EntityManager entityManager;
    private PostingCommentRepository postingCommentRepository;
    private UserService userService;

    @Override
    public Posting save(
//            Authentication authentication,
            Posting posting
    ) {
        // 1. 인증된 사용자 ID 추출
//        String userId = authentication.getUserId();

        // 2. userId로 User 조회
//        User user = userService.readOne(userId)
//                .orElseThrow(() -> new NoSuchElementException("해당 유저 없음"));

        // 3. Posting 객체에 User 세팅
//        posting.setUser(user);

        // 새로운 게시글 생성
        Posting postingSave = new Posting();

        // postingSave.setUserId(userId); // 보안 강화를 위해 userId는 서버에서 직접 설정
        // 공통 데이터 설정
        postingSave.setContents(posting.getContents());
//        postingSave.setUser(user);
        postingSave.setPostingImages(posting.getPostingImages());
        postingSave.setLocationTag(posting.getLocationTag());
        postingSave.setPersonTagId(posting.getPersonTagId());
        postingSave.setVisibilityType(posting.getVisibilityType());
        postingSave.setCreatedAt(posting.getCreatedAt());

        return postingRepository.save(postingSave);
    }

    @Override
    public Posting delete(Posting posting){
        Posting postingDelete = postingRepository.findById(posting.getPostId())
                .orElseThrow(() -> new NoSuchElementException("게시글 없음"));

        postingRepository.delete(postingDelete);
        return postingDelete;
    }

//    @Override
//    public Posting delete(Posting posting) {
//        Posting postingDelete = entityManager.find(Posting.class, posting.getPostId())
//        if (postingDelete != null) {
//            entityManager.remove(postingDelete);
//        }
//    }

    @Override
    public Set<Posting> findByUserId(String userId) {
        return postingRepository.findByUser_UserId(userId);
    }

    @Override
    public Posting findByPostId(Integer postId) {
        Posting posting = postingRepository.findByPostId(postId);

//        Set<PostingComment> comments = postingCommentRepository.findByPost_PostId(postId);
//        Set<PostingCommentsDto> postingCommentsDto = comments.stream()
//                .map(PostingCommentsDto::new)
//                .collect(Collectors.toSet());
//        // posting.setComments(new ArrayList<>(postingCommentsDto));
//        posting.setComments(postingCommentsDto);
//
//        Set<PostingComment> postingComments = comments.stream()
//                .collect(Collectors.toSet());

//        posting.setComments(postingComments);

        Set<PostingCommentsDto> postingCommentsDto = new HashSet<>();

        return posting;
    }


}
