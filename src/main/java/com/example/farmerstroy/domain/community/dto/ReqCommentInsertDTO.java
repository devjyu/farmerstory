package com.example.farmerstroy.domain.community.dto;

import jakarta.validation.constraints.NotBlank;
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
public class ReqCommentInsertDTO {

    
        @NotBlank(message = "내용을 입력해주세요")
        private String content;

        private Long communityIdx;


}
