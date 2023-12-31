package com.example.farmerstroy.domain.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.farmerstroy.common.dto.LoginUserDTO;
import com.example.farmerstroy.domain.community.dto.ResCommunityDTO;
import com.example.farmerstroy.domain.community.dto.ResCommunityDetailDTO;
import com.example.farmerstroy.domain.community.service.CommunityService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class CommunityController {

    private final CommunityService communityService;
    
    // 커뮤니티 페이지
    @GetMapping("/community")
    public String communityPage(Model model){
        ResCommunityDTO resCommunityDTOList = communityService.getCommunityData();
        // System.out.println(resCommunityDTOList + "뭐라고");
        model.addAttribute("resCommunityDTOList", resCommunityDTOList);
        return "community";
    }

    // 커뮤니티 작성하기 페이지
    @GetMapping("/communityinsert")
    public String communityInsertPage(HttpSession session){
        LoginUserDTO loginUser = (LoginUserDTO)session.getAttribute("dto");
        if (loginUser == null) {
            return "redirect:/community";
        }
        return "communityinsert";
    }

    // 커뮤니티 게시글 상세페이지
    @GetMapping("/community/{communityIdx}")
    public String communityDetailPage(@PathVariable Long communityIdx, Model model) {
        ResCommunityDetailDTO resCommunityDetail = communityService.getCommunityDetailData(communityIdx);
        model.addAttribute("resCommunityDetail", resCommunityDetail);
        return "communitydetail";
    }

    // 커뮤니티 게시글 수정하기
    @GetMapping("/community/communitydetail/{communityIdx}")
    public String communityUpdatePage(@PathVariable Long communityIdx, Model model, HttpSession session) {
        ResCommunityDetailDTO resCommunityDetail = communityService.getCommunityDetailData(communityIdx);
        model.addAttribute("resCommunityDetail", resCommunityDetail);
        LoginUserDTO loginUser = (LoginUserDTO)session.getAttribute("dto");
        if (loginUser == null) {
            return "redirect:/community";
        }
        return "communityupdate";
    }
}
