package com.example.farmerstroy.domain.community.dto;

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
public class ReqCommentUpdateDTO {

    @NotBlank(message = "내용을 입력해주세요")
    @Size(min = 1, max = 300, message = "300자 이하로 입력해주세요")
    private String content;

}
