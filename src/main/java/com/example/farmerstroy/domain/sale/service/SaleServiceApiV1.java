package com.example.farmerstroy.domain.sale.service;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.farmerstroy.common.dto.LoginUserDTO;
import com.example.farmerstroy.common.dto.ResponseDTO;
import com.example.farmerstroy.common.exception.BadRequestException;
import com.example.farmerstroy.domain.sale.dto.ReqSaleInsertDTO;
import com.example.farmerstroy.domain.sale.dto.ReqSaleUpdateDTO;
import com.example.farmerstroy.domain.sale.dto.ResSaleDTO;
import com.example.farmerstroy.model.category.entity.CategoryEntity;
import com.example.farmerstroy.model.category.repository.CategoryRepository;
import com.example.farmerstroy.model.sale.entity.SaleEntity;
import com.example.farmerstroy.model.sale.repositroy.SaleRepository;
import com.example.farmerstroy.model.user.entity.UserEntity;
import com.example.farmerstroy.model.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SaleServiceApiV1 {

    private final SaleRepository saleRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    // 판매페이지 게시글 카테고리 idx로 게시글 조회
    public ResponseEntity<?> getSaleListByCategoryIdx(Long categoryIdx) {
        List<SaleEntity> saleEntityList = saleRepository.findByCategoryEntity_Idx(categoryIdx);
        ResSaleDTO dto = ResSaleDTO.of(saleEntityList);
        return new ResponseEntity<>(
                ResponseDTO.builder()
                        .code(0)
                        .message("상품 조회에 성공했습니다.")
                        .data(dto)
                        .build(),
                HttpStatus.OK);
    }

    // 상품 등록
    @Transactional
    public ResponseEntity<?> insertSaleTable(ReqSaleInsertDTO dto, LoginUserDTO loginUserDTO) throws IOException{
        if(
            dto.getName() == null || dto.getName().equals("") ||
            dto.getTitle() == null || dto.getTitle().equals("") ||
            dto.getIntroduction() == null || dto.getIntroduction().equals("") ||
            dto.getPrice() < 0 || dto.getAmount() < 0
        ) {
            throw new BadRequestException("정보를 입력해주세요");
        }
        
        if(loginUserDTO == null){
            throw new BadRequestException("로그인 해주세요."); 
        }


        Optional<UserEntity> userEntityOptional = userRepository.findById(loginUserDTO.getUser().getId());

        if (userEntityOptional.isEmpty()) {
            throw new BadRequestException("삭제된 유저입니다.");    
        }

        String saleImg = null;

        if (dto.getSaleImg() != null) {
            String imgBase64 = Base64.getEncoder().encodeToString(dto.getSaleImg().getBytes());
            saleImg = "data:" + dto.getSaleImg().getContentType() + ";base64," + imgBase64;
            System.out.println("사진" + saleImg);
        } else {
            saleImg = "http://via.placeholder.com/320x240";
        }

        UserEntity userEntity = userEntityOptional.get();

        Optional<CategoryEntity> categoryEntityOptional = categoryRepository.findByIdx(dto.getCategoryIdx());

        if (categoryEntityOptional.isEmpty()) {
            throw new BadRequestException("카테고리 번호를 잘못 입력하셨습니다.");
        }

        SaleEntity saleEntity = SaleEntity.builder()
        .name(dto.getName())
        .title(dto.getTitle())
        .introduction(dto.getIntroduction())
        .price(dto.getPrice())
        .saleImg(saleImg)
        .amount(dto.getAmount())
        .userEntity(userEntity)
        .categoryEntity(categoryEntityOptional.get())
        .build();

        saleRepository.save(saleEntity);

        return new ResponseEntity<>(
            ResponseDTO.builder()
                .code(0)
                .message("상품 등록에 성공했습니다.")
                .build()
            ,HttpStatus.OK
        );
    }

    @Transactional
    public ResponseEntity<?> updateSaleUpdate(ReqSaleUpdateDTO dto) {
        List<SaleEntity> findByIdx = saleRepository.findByIdx(dto.getSale().getIdx());
    }
}
