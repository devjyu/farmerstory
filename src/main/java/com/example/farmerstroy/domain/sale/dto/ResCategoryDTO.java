package com.example.farmerstroy.domain.sale.dto;

import java.util.List;

import com.example.farmerstroy.model.category.entity.CategoryEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@ToString
public class ResCategoryDTO {
    
    private List<Category> categoryList;

    public static ResCategoryDTO of(List<CategoryEntity> categoryEntityList) {
        return ResCategoryDTO.builder()
        .categoryList(Category.fromEntityList(categoryEntityList))
        .build();
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
