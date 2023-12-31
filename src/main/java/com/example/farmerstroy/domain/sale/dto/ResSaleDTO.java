package com.example.farmerstroy.domain.sale.dto;

import java.text.DecimalFormat;
import java.util.List;

import com.example.farmerstroy.model.category.entity.CategoryEntity;
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
// 판매 DTO
public class ResSaleDTO {

    private List<Sale> saleList;
    private List<Category> categoryList;

    public static ResSaleDTO of(List<SaleEntity> saleEntityList, List<CategoryEntity> categoryEntityList) {
        return ResSaleDTO.builder()
                .saleList(Sale.fromEntityList(saleEntityList))
                .categoryList(Category.fromEntityList(categoryEntityList))
                .build();
    }
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

        public static List<Sale> fromEntityList(List<SaleEntity> saleEntityList) {
            return saleEntityList
                    .stream()
                    .map((saleEntity) -> Sale.fromEntity(saleEntity))
                    .toList();
        }
    }
    
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @Getter
    public static class Category {
        private Long idx;
        private String name;

        public static Category fromEntity(CategoryEntity categoryEntity) {
            return Category.builder()
            .idx(categoryEntity.getIdx())
            .name(categoryEntity.getName())
            .build();
        }

        public static List<Category> fromEntityList(List<CategoryEntity> categoryEntityList) {
            return categoryEntityList
            .stream()
            .map((categoryEntity) -> Category.fromEntity(categoryEntity))
            .toList();
        }
    }
}
