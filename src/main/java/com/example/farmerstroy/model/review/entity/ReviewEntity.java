package com.example.farmerstroy.model.review.entity;

import java.time.LocalDate;

import com.example.farmerstroy.model.sale.entity.SaleEntity;
import com.example.farmerstroy.model.user.entity.UserEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "review")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class ReviewEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idx", nullable = false, unique = true)
    private Long idx;

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "review_img")
    private String reviewImg;

    @Column(name = "grade", nullable = false)
    private Integer grade;

    @Column(name = "create_date", nullable = false)
    private LocalDate createDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_idx", referencedColumnName = "idx", updatable = false, nullable = false)
    private UserEntity userEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sale_idx", referencedColumnName = "idx", updatable = false, nullable = false)
    private SaleEntity saleEntity;
}
