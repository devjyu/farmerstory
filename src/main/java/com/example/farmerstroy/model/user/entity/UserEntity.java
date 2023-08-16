package com.example.farmerstroy.model.user.entity;

import java.util.List;

import com.example.farmerstroy.model.comment.entity.CommentEntity;
import com.example.farmerstroy.model.community.entity.CommunityEntity;
import com.example.farmerstroy.model.diary.entity.DiaryEntity;
import com.example.farmerstroy.model.review.entity.ReviewEntity;
import com.example.farmerstroy.model.sale.entity.SaleEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "`USER`")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@EqualsAndHashCode(of = "idx", callSuper = false)
public class UserEntity {

    @Id
    @Column(name = "idx", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @Column(name = "nickname", nullable = false)
    private String nickname;

    @Column(name = "id", nullable = false)
    private String id;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "profile_img", length = 10000000)
    private String profileImg;

    @Column(name = "authority", nullable = false)
    private Integer authority;

    @OneToMany(mappedBy = "userEntity", fetch = FetchType.LAZY)
    private List<DiaryEntity> diaryEntityList;

    @OneToMany(mappedBy = "userEntity", fetch = FetchType.LAZY)
    private List<CommentEntity> commentEntityList;

    @OneToMany(mappedBy = "userEntity", fetch = FetchType.LAZY)
    private List<CommunityEntity> communityEntityList;

    @OneToMany(mappedBy = "userEntity", fetch = FetchType.LAZY)
    private List<ReviewEntity> reviewEntityList;

    @OneToMany(mappedBy = "userEntity", fetch = FetchType.LAZY)
    private List<SaleEntity> saleEntityList;
}
