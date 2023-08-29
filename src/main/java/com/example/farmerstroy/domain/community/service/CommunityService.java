package com.example.farmerstroy.domain.community.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.farmerstroy.model.community.repository.CommunityRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommunityService {
    
    private final CommunityRepository communityRepository;

}
