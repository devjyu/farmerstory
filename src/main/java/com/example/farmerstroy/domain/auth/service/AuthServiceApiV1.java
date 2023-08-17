package com.example.farmerstroy.domain.auth.service;

import java.io.IOException;
import java.util.Base64;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.farmerstroy.common.dto.LoginUserDTO;
import com.example.farmerstroy.common.dto.ResponseDTO;
import com.example.farmerstroy.common.exception.BadRequestException;
import com.example.farmerstroy.domain.auth.dto.ReqJoinDTO;
import com.example.farmerstroy.domain.auth.dto.ReqLoginDTO;
import com.example.farmerstroy.model.user.entity.UserEntity;
import com.example.farmerstroy.model.user.repository.UserRepository;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AuthServiceApiV1 {

        private final UserRepository userRepository;

        // 로그인
        public ResponseEntity<?> login(ReqLoginDTO dto, HttpSession session) {
                // 리파지토리에서 아이디가 삭제되지 않은 유저 찾기
                Optional<UserEntity> userEntityOptional = userRepository.findById(dto.getUser().getId());

                // 없으면 존재하지 않음 메시지 리턴
                if (userEntityOptional.isEmpty()) {
                        throw new BadRequestException("존재하지 않는 아이디입니다.");
                }

                // 있으면 반환
                UserEntity userEntity = userEntityOptional.get();

                // 비밀번호가 일치하지 않으면 메시지 리턴
                if (!userEntity.getPassword().equals(dto.getUser().getPassword())) {
                        throw new BadRequestException("비밀번호가 알치하지 않습니다");
                }

                // 세션에 로그인 유저 정보 저장
                session.setAttribute("dto", LoginUserDTO.of(userEntity));

                // 응답 데이터로 리턴하기
                return new ResponseEntity<>(
                                ResponseDTO.builder()
                                                .code(0)
                                                .message("로그인에 성공하였습니다.")
                                                .build(),
                                HttpStatus.OK);
        }

        // 회원가입
        @Transactional
        public ResponseEntity<?> join(ReqJoinDTO dto) throws IOException {
                // 회원가입 정보 입력했는지 확인
                if (dto.getId() == null ||
                                dto.getId().equals("") ||
                                dto.getPassword() == null ||
                                dto.getPassword().equals("") ||
                                dto.getNickname() == null ||
                                dto.getNickname().equals("") ||
                                dto.getEmail() == null ||
                                dto.getEmail().equals("") ||
                                dto.getAuthority() == null) {
                        // return new ResponseEntity<>(
                        // ResponseDTO.builder()
                        // .code(1)
                        // .message("아이디나 비밀번호, 이름, 이메일을 입력해주세요")
                        // .build()
                        // ,HttpStatus.BAD_REQUEST
                        // );

                        throw new BadRequestException("정보를 제대로 입력해주세요");
                }

                // 리파지토리에서 아이디로 유저 찾기
                Optional<UserEntity> userEntityOptional = userRepository.findById(dto.getId());

                // 있으면 있다고 메시지 리턴
                if (userEntityOptional.isPresent()) {
                        // return new ResponseEntity<>(
                        // ResponseDTO.builder()
                        // .code(1)
                        // .message("이미 존재하는 아이디입니다.")
                        // .build()
                        // ,HttpStatus.BAD_REQUEST
                        // );

                        throw new BadRequestException("이미 존재합니다.");
                }

                // String profileImg = "default_profile.png";
                String profileImg = null;

                if (dto.getProfileImg() != null) {
                        String imgBase64 = Base64.getEncoder().encodeToString(dto.getProfileImg().getBytes());

                        profileImg = "data:" + dto.getProfileImg().getContentType() + ";base64," + imgBase64;
                } else {
                        profileImg = "default.png";

                       //  ClassPathResource defaultImageResource = new ClassPathResource("/img/default_profile.png");
                        
                        // byte[] defaultImageBytes = FileUtils.readFileToByteArray(defaultImageResource.getFile());

                      //  byte[] readbyte = FileUtil.getBytes(request.getServletContext().getRealPath("/image_bundle/default_img.png"));
                        //File defaultImageFile = new File(getClass().getResource("img/default_profile.png").getFile());
                        // byte[] defaultImageBytes = new byte[(int) defaultImageResource];
                        // String imgBase64 = Base64.getEncoder().encodeToString(defaultImageBytes);
                        // profileImg = "data:" + defaultImageFile + ";base64," + imgBase64;




                        // FileInputStream fis = new FileInputStream(defaultImageFile);
                        // fis.read(defaultImageBytes);
                        // fis.close();
                        // String defaultImgBase64 = Base64.getEncoder().encodeToString(defaultImageBytes);
                        
                        // profileImg = "data:image/png;base64," + defaultImgBase64;
                }
                
                // 없으면 회원가입 처리
                UserEntity userEntity = UserEntity.builder()
                                .id(dto.getId())
                                .password(dto.getPassword())
                                .nickname(dto.getNickname())
                                .email(dto.getEmail())
                                .authority(dto.getAuthority())
                                .profileImg(profileImg)
                                .build();

                userRepository.save(userEntity);

                // 응답데이터로 리턴하기
                return new ResponseEntity<>(
                                ResponseDTO.builder()
                                                .code(0)
                                                .message("회원가입에 성공하였습니다.")
                                                .build(),
                                HttpStatus.OK);
        }

        // id중복체크
        public ResponseEntity<?> checkId(String id) {
                if (id == null || id.equals("")) {
                        return new ResponseEntity<>(
                                        ResponseDTO.builder()
                                                        .code(1)
                                                        .message("아이디를 입력해주세요")
                                                        .build(),
                                        HttpStatus.BAD_REQUEST);
                } else if (userRepository.existsById(id)) {
                        return new ResponseEntity<>(
                                        ResponseDTO.builder()
                                                        .code(1)
                                                        .message("이미 존재하는 아이디입니다.")
                                                        .build(),
                                        HttpStatus.BAD_REQUEST);
                } else {
                        return new ResponseEntity<>(
                                        ResponseDTO.builder()
                                                        .code(0)
                                                        .message("사용가능한 아이디입니다.")
                                                        .build(),
                                        HttpStatus.OK);
                }
        }

        // 이름(닉네임) 중복체크
        public ResponseEntity<?> checkNickname(String nickname) {
                if (nickname == null || nickname.equals("")) {
                        return new ResponseEntity<>(
                                        ResponseDTO.builder()
                                                        .code(1)
                                                        .message("닉네임을 입력해주세요")
                                                        .build(),
                                        HttpStatus.BAD_REQUEST);
                } else if (userRepository.existsByNickname(nickname)) {
                        return new ResponseEntity<>(
                                        ResponseDTO.builder()
                                                        .code(1)
                                                        .message("이미 존재하는 닉네임입니다.")
                                                        .build(),
                                        HttpStatus.BAD_REQUEST);
                } else {
                        return new ResponseEntity<>(
                                        ResponseDTO.builder()
                                                        .code(0)
                                                        .message("사용 가능한 닉네임입니다.")
                                                        .build(),
                                        HttpStatus.OK);
                }
        }

        // 유저가 농부 또는 일반인을 구별할때
        public ResponseEntity<?> farmerOrPublic(Integer authority) {
                if (authority == null) {
                        return new ResponseEntity<>(
                                        ResponseDTO.builder()
                                                        .code(1)
                                                        .message("농부 또는 일반을 선택해주세요")
                                                        .build(),
                                        HttpStatus.BAD_REQUEST);
                } else if (authority == 1) {
                        return new ResponseEntity<>(
                                        ResponseDTO.builder()
                                                        .code(0)
                                                        .message("농부를 선택하셨습니다")
                                                        .build(),
                                        HttpStatus.OK);
                } else {
                        return new ResponseEntity<>(
                                        ResponseDTO.builder()
                                                        .code(0)
                                                        .message("일반을 선택하셨습니다")
                                                        .build(),
                                        HttpStatus.OK);
                }

        }

        // // 사진 저장
        // @Transactional
        // public ResponseEntity<?> saveGallery(MultipartFile multipartFile) throws IOException {
        //         String imgBase64 = Base64.getEncoder().encodeToString(multipartFile.getBytes());

        //         String imgUrl = "data:" + multipartFile.getContentType() + ";base64," + imgBase64;

        //         UserEntity userEntity = UserEntity.builder()
        //                         .profileImg(imgUrl)
        //                         .build();

        //         userRepository.save(userEntity);

        //         return new ResponseEntity<>(
        //                         ResponseDTO.builder()
        //                                         .code(0)
        //                                         .message("성공")
        //                                         .build(),
        //                         HttpStatus.OK);
        // }
}
