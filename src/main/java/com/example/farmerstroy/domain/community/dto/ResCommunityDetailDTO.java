package com.example.farmerstroy.domain.community.dto;

import java.time.LocalDate;

import com.example.farmerstroy.model.community.entity.CommunityEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@ToString
public class ResCommunityDetailDTO {

    private Community community;

    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @Getter
    public static class Community{
        private Long idx;
        private String title;
        private String content;
        private String communityImg;
        private LocalDate createDate;
        private String userId;

        public static Community fromEntity(CommunityEntity communityEntity) {
            return Community.builder()
            .idx(communityEntity.getIdx())
            .title(communityEntity.getTitle())
            .content(communityEntity.getContent())
            .communityImg(communityEntity.getCommunityImg())
            .createDate(communityEntity.getCreateDate())
            .userId(communityEntity.getUserEntity().getId())
            .build();
        }

    }
}
