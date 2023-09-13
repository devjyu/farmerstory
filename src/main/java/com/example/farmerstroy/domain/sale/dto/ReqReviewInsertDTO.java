package com.example.farmerstroy.domain.sale.dto;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
public class ReqReviewInsertDTO {

    // private Long idx;

    @NotBlank(message = "내용을 입력해주세요")
    @Size(min = 1, max = 1000, message = "1000자 이하로 입력해주세요")
    private String content;

    @NotNull
    private Integer grade;

    private MultipartFile reviewImg;

    private Long saleIdx;
}
