package com.artu.fullstack_team_project_application.service.postings;

import com.artu.fullstack_team_project_application.dto.PostingCommentsDto;
import com.artu.fullstack_team_project_application.entity.postings.Posting;
import com.artu.fullstack_team_project_application.entity.postings.PostingComment;
import com.artu.fullstack_team_project_application.entity.postings.PostingImage;
import com.artu.fullstack_team_project_application.entity.postings.PostingLike;
import com.artu.fullstack_team_project_application.repository.postings.PostingCommentRepository;
import com.artu.fullstack_team_project_application.repository.postings.PostingImageRepository;
import com.artu.fullstack_team_project_application.repository.postings.PostingRepository;
import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.file.Files;
import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PostingServiceImp implements PostingService {
    private PostingRepository postingRepository;
    private final PostingImageRepository postingImageRepository;
    private final EntityManager entityManager;
    private PostingCommentRepository postingCommentRepository;

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
