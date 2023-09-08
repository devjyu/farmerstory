package com.example.farmerstroy.domain.community.controller;

import java.io.IOException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.farmerstroy.common.dto.LoginUserDTO;
import com.example.farmerstroy.domain.community.dto.ReqCommentInsertDTO;
import com.example.farmerstroy.domain.community.dto.ReqCommunityInsertDTO;
import com.example.farmerstroy.domain.community.dto.ReqCommunityUpdateDTO;
import com.example.farmerstroy.domain.community.dto.ResCommunityDetailDTO;
import com.example.farmerstroy.domain.community.service.CommunityServiceApiV1;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/community")
public class CommunityControllerApiV1 {

    private final CommunityServiceApiV1 communityServiceApiV1;

    // 게시글 등록하기
    @PostMapping
    public ResponseEntity<?> insertCommunityTable(ReqCommunityInsertDTO dto, HttpSession session) throws IOException {
        LoginUserDTO userDTO = (LoginUserDTO) session.getAttribute("dto");
        return communityServiceApiV1.insertCommunityTable(dto, userDTO);
    }

    // 게시글 수정하기
    @PutMapping
    public ResponseEntity<?> updateCommunityTable(Long idx, ReqCommunityUpdateDTO dto, HttpSession session) throws IOException {
        LoginUserDTO userDTO = (LoginUserDTO) session.getAttribute("dto");
        return communityServiceApiV1.updateCommunityTable(idx, dto, userDTO);
    }
    
    // 게시글 삭제하기
    @DeleteMapping("/{idx}")
    public ResponseEntity<?> deleteCommunityTable(@PathVariable Long idx, ResCommunityDetailDTO dto, HttpSession session) {
        LoginUserDTO userDTO = (LoginUserDTO) session.getAttribute("dto");
        return communityServiceApiV1.deleteCommunityTable(idx, userDTO);
    }

    // 게시글 댓글 등록하기
    @PostMapping("/{idx}")
    public ResponseEntity<?> insertCommentTable(@PathVariable Long idx ,ReqCommentInsertDTO dto, HttpSession session) {

        LoginUserDTO userDTO = (LoginUserDTO) session.getAttribute("dto");
        return communityServiceApiV1.insertCommentTable(idx, dto, userDTO);
    }
}
