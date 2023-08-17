package com.example.farmerstroy.domain.auth.controller;

import java.io.IOException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.farmerstroy.domain.auth.dto.ReqJoinDTO;
import com.example.farmerstroy.domain.auth.dto.ReqLoginDTO;
import com.example.farmerstroy.domain.auth.service.AuthServiceApiV1;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthControllerApiV1 {
    
    private final AuthServiceApiV1 authServiceApiV1;

    // 회원가입(프로필까지)
    @PostMapping("/join")
    public ResponseEntity<?> join(@Valid ReqJoinDTO dto) throws IOException{
        return authServiceApiV1.join(dto);
    }

    // 아이디 중복 체크
    @PostMapping("/id/check")
    public ResponseEntity<?> checkId(@RequestParam(value = "id") String id){
        return authServiceApiV1.checkId(id);
    }

    // 닉네임 중복 체크
    @PostMapping("/nickname/check")
    public ResponseEntity<?> checkNickname(@RequestParam(value = "nickname") String nickname){
        return authServiceApiV1.checkNickname(nickname);
    }
    
    // @PostMapping("/profileImg")
    // public @ResponseBody ResponseEntity<?> saveGalleryForm(ReqJoinDTO dto) throws IOException{
    //     return authServiceApiV1.saveGallery(dto.getProfileImg());
    // }

    // 로그인
    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody ReqLoginDTO dto, HttpSession session){
        return authServiceApiV1.login(dto, session);
    }
}
