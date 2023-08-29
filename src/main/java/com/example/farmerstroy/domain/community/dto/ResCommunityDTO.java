package com.example.farmerstroy.domain.community.dto;

import java.time.LocalDate;
import java.util.List;

import com.example.farmerstroy.domain.sale.dto.ResSaleDTO;
import com.example.farmerstroy.model.community.entity.CommunityEntity;
import com.example.farmerstroy.model.sale.entity.SaleEntity;

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
public class ResCommunityDTO {
    
    private List<Community> communityList;

    public static ResCommunityDTO of(List<CommunityEntity> communityEntityList) {
    return ResCommunityDTO.builder()
            .communityList(Community.fromEntityList(communityEntityList))
            .build();
    }

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

        public static List<Community> fromEntityList(List<CommunityEntity> communityEntityList) {
            return communityEntityList
            .stream()
            .map((communityEntity) -> Community.fromEntity(communityEntity))
            .toList();
        }
    }
}
