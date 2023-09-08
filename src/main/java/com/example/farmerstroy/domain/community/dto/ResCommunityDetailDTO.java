package com.example.farmerstroy.domain.community.dto;

import java.time.LocalDate;
import java.util.List;

import com.example.farmerstroy.model.comment.entity.CommentEntity;
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
    private List<Comment> commentList;

    // public static ResCommunityDetailDTO of(CommunityEntity communityEntity, List<CommentEntity> commentEntityList) {
    //     return ResCommunityDetailDTO.builder()
    //         .community(Community.fromEntity(communityEntity))
    //         .commentList(Comment.fromEntityList(commentEntityList))
    //         .build(); // build() 메서드 호출을 추가하세요.
    // }

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

    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @Getter
    public static class Comment {
        private Long idx;
        private String content;
        private LocalDate createDate;
        private String userId;
        private Long communityIdx;

        public static Comment fromEntity(CommentEntity commentEntity) {
            return Comment.builder()
            .idx(commentEntity.getIdx())
            .content(commentEntity.getContent())
            .createDate(commentEntity.getCreateDate())
            .userId(commentEntity.getUserEntity().getId())
            .communityIdx(commentEntity.getCommunityEntity().getIdx())
            .build();
        }

        public static List<Comment> fromEntityList(List<CommentEntity> commentEntityList) {
            return commentEntityList.stream()
            .map((commentEntity) -> Comment.fromEntity(commentEntity))
            .toList();
        }
    }
}
