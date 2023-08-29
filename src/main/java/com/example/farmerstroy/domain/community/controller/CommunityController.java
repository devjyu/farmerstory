package com.example.farmerstroy.domain.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.farmerstroy.domain.community.dto.ResCommunityDTO;
import com.example.farmerstroy.domain.community.service.CommunityService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class CommunityController {

    private final CommunityService communityService;
    
    // 커뮤니티 페이지
    @GetMapping("/community")
    public String communityPage(Model model){
        ResCommunityDTO resCommunityDTOList = communityService.getCommunityData();
        model.addAttribute("resCommunityDTOList", resCommunityDTOList);
        return "community";
    }

    // 커뮤니티 작성하기 페이지
    @GetMapping("/communityinsert")
    public String communityInsertPage(){
        return "communityinsert";
    }
}
