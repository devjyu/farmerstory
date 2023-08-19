package com.example.farmerstroy.model.sale.entity;

import java.util.List;

import com.example.farmerstroy.model.category.entity.CategoryEntity;
import com.example.farmerstroy.model.review.entity.ReviewEntity;
import com.example.farmerstroy.model.user.entity.UserEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "sale")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class SaleEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idx", nullable = false, unique = true)
    private Long idx;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "introduction", nullable = false)
    private String introduction;

    @Column(name = "price", nullable = false)
    private Integer price;

    @Column(name = "sale_img", length = 10000000)
    private String saleImg;

    @Column(name = "amount")
    private Integer amount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_idx", referencedColumnName = "idx", updatable = false, nullable = false)
    private UserEntity userEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_idx", referencedColumnName = "idx", updatable = false, nullable = false)
    private CategoryEntity categoryEntity;

    @OneToMany(mappedBy = "saleEntity", fetch = FetchType.LAZY)
    private List<ReviewEntity> reviewEntityList;
}
