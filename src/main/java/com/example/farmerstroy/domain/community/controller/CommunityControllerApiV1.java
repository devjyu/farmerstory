package com.example.farmerstroy.domain.community.controller;

import java.io.IOException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.farmerstroy.common.dto.LoginUserDTO;
import com.example.farmerstroy.domain.community.dto.ReqCommunityInsertDTO;
import com.example.farmerstroy.domain.community.dto.ReqCommunityUpdateDTO;
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

    // 게시글 수정하기기
    @PutMapping
    public ResponseEntity<?> updateCommunityTable(Long idx, ReqCommunityUpdateDTO dto, HttpSession session) throws IOException {
        LoginUserDTO userDTO = (LoginUserDTO) session.getAttribute("dto");
        return communityServiceApiV1.updateCommunityTable(idx, dto, userDTO);
    }
    
}
