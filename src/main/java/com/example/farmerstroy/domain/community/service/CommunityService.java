package com.example.farmerstroy.domain.community.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.farmerstroy.domain.community.dto.ResCommunityDTO;
import com.example.farmerstroy.model.community.entity.CommunityEntity;
import com.example.farmerstroy.model.community.repository.CommunityRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommunityService {
    
    private final CommunityRepository communityRepository;

    // 커뮤니티 전체 게시글 가져오기
    public ResCommunityDTO getCommunityData() {
        List<CommunityEntity> communityEntityList = communityRepository.findAll();
        ResCommunityDTO dto = ResCommunityDTO.builder()
        .communityList(ResCommunityDTO.Community.fromEntityList(communityEntityList))
        .build();
        return dto;
    }
}
