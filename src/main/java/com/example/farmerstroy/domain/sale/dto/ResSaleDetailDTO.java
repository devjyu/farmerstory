package com.example.farmerstroy.domain.sale.dto;

import java.text.DecimalFormat;

import com.example.farmerstroy.model.sale.entity.SaleEntity;

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
// 판매 상세페이지 DTO
public class ResSaleDetailDTO {

    private Sale sale;


    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @Getter
    public static class Sale {
        private Long idx;
        private String name;
        private String title;
        private String introduction;
        private Integer price;
        private String priceStr;
        private String saleImg;
        private Integer amount;
        private Long categoryIdx;
        private Long userIdx;

        public static Sale fromEntity(SaleEntity saleEntity) {
            return Sale.builder()
                    .idx(saleEntity.getIdx())
                    .name(saleEntity.getName())
                    .title(saleEntity.getTitle())
                    .introduction(saleEntity.getIntroduction())
                    .price(saleEntity.getPrice())
                    .priceStr(new DecimalFormat("###,###").format(saleEntity.getPrice()))
                    .saleImg(saleEntity.getSaleImg())
                    .amount(saleEntity.getAmount())
                    .categoryIdx(saleEntity.getCategoryEntity().getIdx())
                    .userIdx(saleEntity.getUserEntity().getIdx())
                    .build();
        }
    }    
}
