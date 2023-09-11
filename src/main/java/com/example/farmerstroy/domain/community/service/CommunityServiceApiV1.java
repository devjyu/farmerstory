package com.example.farmerstroy.domain.community.service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Base64;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.farmerstroy.common.dto.LoginUserDTO;
import com.example.farmerstroy.common.dto.ResponseDTO;
import com.example.farmerstroy.common.exception.BadRequestException;
import com.example.farmerstroy.domain.community.dto.ReqCommentInsertDTO;
import com.example.farmerstroy.domain.community.dto.ReqCommentUpdateDTO;
import com.example.farmerstroy.domain.community.dto.ReqCommunityInsertDTO;
import com.example.farmerstroy.domain.community.dto.ReqCommunityUpdateDTO;
import com.example.farmerstroy.model.comment.entity.CommentEntity;
import com.example.farmerstroy.model.comment.repository.CommentRepository;
import com.example.farmerstroy.model.community.entity.CommunityEntity;
import com.example.farmerstroy.model.community.repository.CommunityRepository;
import com.example.farmerstroy.model.user.entity.UserEntity;
import com.example.farmerstroy.model.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommunityServiceApiV1 {
    
    private final CommunityRepository communityRepository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;

    // 게시글 등록하기
    @Transactional
    public ResponseEntity<?> insertCommunityTable(ReqCommunityInsertDTO dto, LoginUserDTO loginUserDTO) throws IOException {
        if (
            dto.getTitle() == null || dto.getTitle().equals("") ||
            dto.getContent() == null || dto.getContent().equals("")
        ) {
            throw new BadRequestException("정보를 입력해주세요");
        }

        if (loginUserDTO == null) {
            throw new BadRequestException("로그인 해주세요");
        }

        Optional<UserEntity> userEntityOptional = userRepository.findById(loginUserDTO.getUser().getId());

        if (userEntityOptional.isEmpty()) {
            throw new BadRequestException("삭제된 유저입니다.");
        }

        String communityImg = null;

        if (dto.getCommunityImg() != null) {
            String imgBase64 = Base64.getEncoder().encodeToString(dto.getCommunityImg().getBytes());
            communityImg = "data:" + dto.getCommunityImg().getContentType() + ";base64," + imgBase64;
            // System.out.println("사진" + communityImg);
        }

        UserEntity userEntity = userEntityOptional.get();

        CommunityEntity communityEntity = CommunityEntity.builder()
        .title(dto.getTitle())
        .content(dto.getContent())
        .communityImg(communityImg)
        .createDate(LocalDate.now())
        .userEntity(userEntity)
        .build();

        communityRepository.save(communityEntity);

        return new ResponseEntity<>(
            ResponseDTO.builder()
            .code(0)
            .message("게시글 작성에 성공했습니다.")
            .build()
            ,HttpStatus.OK  
        );
    }

    // 게시글 수정하기
    @Transactional
    public ResponseEntity<?> updateCommunityTable(Long idx, ReqCommunityUpdateDTO dto, LoginUserDTO loginUserDTO) throws IOException {
        Optional<CommunityEntity> communityEntityOptional = communityRepository.findByIdx(idx);

        if (communityEntityOptional.isEmpty()) {
            throw new BadRequestException("해당 하는 게시글이 없습니다.");
        }

        CommunityEntity communityEntity = communityEntityOptional.get();

        if (!communityEntity.getUserEntity().getIdx().equals(loginUserDTO.getUser().getIdx())) {
            throw new BadRequestException("권한이 없습니다.");
        }

        // 기존 이미지 값 유지
        String communityImg = communityEntity.getCommunityImg();

        // System.out.println("사진" + communityImg);

        communityEntity.setIdx(dto.getIdx());
        communityEntity.setTitle(dto.getTitle());
        communityEntity.setContent(dto.getContent());
        if (dto.getCommunityImg() != null) {
            // 새로운 이미지가 업로드되었을 때만 이미지 업데이트
            String imgBase64 = Base64.getEncoder().encodeToString(dto.getCommunityImg().getBytes());
            communityImg = "data:" + dto.getCommunityImg() + ";base64," + imgBase64;
            communityEntity.setCommunityImg(communityImg);
            // System.out.println("사진" + communityImg);
        }

        return new ResponseEntity<>(
            ResponseDTO.builder()
                .code(0)
                .message("게시글 수정에 성공했습니다.")
                .build()
            ,HttpStatus.OK
        );
    }

    // 게시글 삭제하기
    @Transactional
    public ResponseEntity<?> deleteCommunityTable(Long idx, LoginUserDTO loginUserDTO) {
        Optional<CommunityEntity> communityEntityOptional = communityRepository.findByIdx(idx);

        if (communityEntityOptional.isEmpty()) {
            throw new BadRequestException("해당 게시글이 없습니다.");
        }

        CommunityEntity communityEntity = communityEntityOptional.get();

        if (!communityEntity.getUserEntity().getIdx().equals(loginUserDTO.getUser().getIdx())) {
            throw new BadRequestException("권한이 없습니다.");
        }

        communityRepository.delete(communityEntity);

        return new ResponseEntity<>(
            ResponseDTO.builder()
                .code(0)
                .message("게시글 삭제에 성공했습니다.")
                .build()
            ,HttpStatus.OK
        );
    }

    // 게시글 댓글 등록하기
    @Transactional
    public ResponseEntity<?> insertCommentTable(Long idx, ReqCommentInsertDTO dto, LoginUserDTO loginUserDTO) {
        // System.out.println("dto는 무엇인가" + dto.getContent());
        if (dto == null ||  dto.getContent() == null || dto.getContent().equals("")) {
            throw new BadRequestException("정보를 입력해주세요");
        }

        if (loginUserDTO == null) {
            throw new BadRequestException("로그인 해주세요");
        }

        Optional<UserEntity> userEntityOptional = userRepository.findById(loginUserDTO.getUser().getId());

        if (userEntityOptional.isEmpty()) {
            throw new BadRequestException("삭제된 유저입니다.");
        }

        UserEntity userEntity = userEntityOptional.get();

        Optional<CommunityEntity> communityEntityOptional = communityRepository.findByIdx(idx);

        if (communityEntityOptional.isEmpty()) {
            throw new BadRequestException("삭제된 게시글입니다.");
        }

        CommunityEntity communityEntity = communityEntityOptional.get();

        CommentEntity commentEntity = CommentEntity.builder()
        .content(dto.getContent())
        .createDate(LocalDate.now())
        .userEntity(userEntity)
        .communityEntity(communityEntity)
        .build();

        System.out.println(commentEntity + "이 부분");

        commentRepository.save(commentEntity);

        return new ResponseEntity<>(
            ResponseDTO.builder()
            .code(0)
            .message("댓글 등록에 성공했습니다.")
            .build(),HttpStatus.OK
        );
    }
    
    // 게시글 댓글 수정하기
    @Transactional
    public ResponseEntity<?> updateCommentTable(Long idx, ReqCommentUpdateDTO dto, LoginUserDTO loginUserDTO) {
        Optional<CommentEntity> commentEntityOptional = commentRepository.findByIdx(idx);
        
        if (commentEntityOptional.isEmpty()) {
            throw new BadRequestException("해당 댓글이 없습니다.");
        }

        CommentEntity commentEntity = commentEntityOptional.get();

        if (!commentEntity.getUserEntity().getIdx().equals(loginUserDTO.getUser().getIdx())) {
            throw new BadRequestException("해당 권한이 없습니다.");
        }

        commentEntity.setContent(dto.getContent());

        return new ResponseEntity<>(
            ResponseDTO.builder()
                .code(0)
                .message("댓글 수정에 성공했습니다.")
                .build()
            ,HttpStatus.OK
        );
    }
    
    // 게시글 댓글 삭제하기
    @Transactional
    public ResponseEntity<?> deleteCommentTable(Long idx, LoginUserDTO loginUserDTO) {
        Optional<CommentEntity> commentEntityOptional = commentRepository.findByIdx(idx);

        if (commentEntityOptional.isEmpty()) {
            throw new BadRequestException("해당 댓글이 없습니다.");
        }

        CommentEntity commentEntity = commentEntityOptional.get();

        if (!commentEntity.getUserEntity().getIdx().equals(loginUserDTO.getUser().getIdx())) {
            throw new BadRequestException("권한이 없습니다.");
        }

        commentRepository.delete(commentEntity);

        return new ResponseEntity<>(
            ResponseDTO.builder()
                .code(0)
                .message("댓글 삭제에 성공했습니다.")
                .build()
            ,HttpStatus.OK
        );
    }

}
