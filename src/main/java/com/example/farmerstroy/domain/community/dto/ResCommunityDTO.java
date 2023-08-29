package com.example.farmerstroy.domain.community.dto;

import java.time.LocalDate;
import java.util.List;

import com.example.farmerstroy.model.community.entity.CommunityEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class ResCommunityDTO {
    
    private List<Community> communityList;

    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @Getter
    public static class Community{
        private Long idx;
        private String title;
        private String content;
        private LocalDate createDate;
        private Long userIdx;

        public static Community fromEntity(CommunityEntity communityEntity) {
            return Community.builder()
                .idx(communityEntity.getIdx())
                .title(communityEntity.getTitle())
                .content(communityEntity.getContent())
                .createDate(communityEntity.getCreateDate())
                .userIdx(communityEntity.getUserEntity().getIdx())
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
