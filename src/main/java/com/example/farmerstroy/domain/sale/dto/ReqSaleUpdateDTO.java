package com.example.farmerstroy.domain.sale.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class ReqSaleUpdateDTO {
    
    private Sale sale;

    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @Getter
    public static class Sale{
        private Long idx;
        private String name;
        private String title;
        private String introduction;
        private Integer price;
        private Integer amount;
        private MultipartFile saleImg;
        private Long categoryIdx;
    }
}
