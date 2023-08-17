package com.example.farmerstroy.domain.sale.dto;

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
