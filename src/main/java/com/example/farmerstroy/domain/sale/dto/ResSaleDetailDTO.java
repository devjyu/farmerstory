package com.example.farmerstroy.domain.sale.dto;

import java.util.List;

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

    private List<Sale> saleDetailList;

    public static ResSaleDetailDTO of(List<SaleEntity> saleEntityList) {
        return ResSaleDetailDTO.builder()
                .saleDetailList(Sale.fromEntityList(saleEntityList))
                .build();
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @Getter
    public static class Sale {
        private Long idx;
        private String title;
        private String introduction;
        private String price;
        private String saleImg;
        private Integer amount;
        private Long categoryIdx;

        public static Sale fromEntity(SaleEntity saleEntity) {
            return Sale.builder()
                    .idx(saleEntity.getIdx())
                    .title(saleEntity.getTitle())
                    .introduction(saleEntity.getIntroduction())
                    .price(saleEntity.getPrice())
                    .saleImg(saleEntity.getSaleImg())
                    .amount(saleEntity.getAmount())
                    .categoryIdx(saleEntity.getCategoryEntity().getIdx())
                    .build();
        }

        public static List<Sale> fromEntityList(List<SaleEntity> saleEntityList) {
            return saleEntityList
                    .stream()
                    .map((saleEntity) -> Sale.fromEntity(saleEntity))
                    .toList();
        }
    }    
}
