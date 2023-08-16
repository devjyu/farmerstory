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
public class ResSaleDTO {

    private List<Sale> saleList;

    public static ResSaleDTO of(List<SaleEntity> saleEntityList) {
        return ResSaleDTO.builder()
                .saleList(Sale.fromEntityList(saleEntityList))
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
        private String categoryName;

        public static Sale fromEntity(SaleEntity saleEntity) {
            return Sale.builder()
                    .idx(saleEntity.getIdx())
                    .title(saleEntity.getTitle())
                    .introduction(saleEntity.getIntroduction())
                    .price(saleEntity.getPrice())
                    .saleImg(saleEntity.getSaleImg())
                    .categoryName(saleEntity.getCategoryEntity().getName())
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
