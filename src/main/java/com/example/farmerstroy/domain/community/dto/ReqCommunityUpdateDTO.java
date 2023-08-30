package com.example.farmerstroy.domain.community.dto;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class ReqCommunityUpdateDTO {

    private Long idx;
    
    @NotBlank(message = "게시글 제목을 입력해주세요")
    @Size(min = 1, max = 100, message = "100자 이하로 입력해주세요")
    private String title;

    @NotBlank(message = "게시글 내용을 입력해주세요")
    @Size(min = 1, max = 1000, message = "1000자 이하로 입력해주세요")
    private String content;

    private MultipartFile communityImg;
}
