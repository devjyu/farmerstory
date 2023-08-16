package com.example.farmerstroy.common.dto;

import com.example.farmerstroy.model.user.entity.UserEntity;

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
public class LoginUserDTO {
    
    private User user;

    public static LoginUserDTO of(UserEntity userEntity){
        return LoginUserDTO.builder()
        .user(User.fromEntity(userEntity))
        .build();
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @Getter
    @ToString
    public static class User{
        private Long idx;
        private String nickname;
        private String id;
        private String password;
        // private String email;
        private String profileImg;
        // private Integer authority;
        // private List<>

        public static User fromEntity(UserEntity userEntity){
            return User.builder()
            .idx(userEntity.getIdx())
            .nickname(userEntity.getNickname())
            .id(userEntity.getId())
            .password(userEntity.getPassword())
            .profileImg(userEntity.getProfileImg())
            .build();
        }
    }
}
