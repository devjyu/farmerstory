package com.example.farmerstroy.domain.sale.dto;

import org.springframework.web.multipart.MultipartFile;

import ch.qos.logback.core.net.LoginAuthenticator;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
public class ReqSaleInsertDTO {

    @NotBlank(message = "상품 이름을 입력해주세요")
    private String name;

    @NotBlank(message = "제목을 입력해주세요")
    private String title;

    @NotBlank(message = "상세 설명을 입력해주세요")
    @Size(min = 1, max = 1000, message = "1000자 이하로 적어주세요")
    private String introduction;

    @NotBlank(message = "금액을 입력해주세요")
    @Size(min = 1, max = 1000000)
    private String price;

    @NotBlank(message = "수량을 입력해주세요")
    @Size(min = 1, max = 300)
    private Integer amount;

    private MultipartFile saleImg;

    @NotNull
    private Long categoryIdx;
}

