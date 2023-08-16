package com.example.farmerstroy.common.exception.handler;

import java.util.HashMap;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.farmerstroy.common.dto.ResponseDTO;
import com.example.farmerstroy.common.exception.BadRequestException;

@RestControllerAdvice
// Rest방식으로 에러 처리
public class RestExceptionHandler {

    @ExceptionHandler(BindException.class)
    public ResponseEntity<?> handleBindException(BindException bindException){

        HashMap<String, String> errorMap = new HashMap<>();

        for (FieldError fieldError : bindException.getBindingResult().getFieldErrors()) {
            errorMap.put(fieldError.getField(), fieldError.getDefaultMessage());
        }

        return new ResponseEntity<>(
            ResponseDTO.builder()
            .code(1)
            .message("요청 데이터를 확인해주세요")
            .data(errorMap)
            .build(),
            HttpStatus.BAD_REQUEST
        );
    }

    // 처리할 에러를 특정함
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<?> handleBadRequestException(Exception exception) {
        exception.printStackTrace();
        return new ResponseEntity<>(ResponseDTO.builder()
            .code(1)
            .message(exception.getMessage())
            .build(), HttpStatus.BAD_REQUEST);
    }

    // 모든 에러를 처리할 때
    // 이렇게 하면 찾아내기 어려움
    // class별로 에러를 처리하는게 나음
    // @ExceptionHandler(Exception.class)
    // public ResponseEntity<?> handleException(Exception exception){
    //     exception.printStackTrace();// 콘솔창에 에러를 뿌려주고 화면에 뿌려줌(하고 싶으면 하고)
    //     return new ResponseEntity<>(ResponseDTO.builder()
    //         .code(1)
    //         .message(exception.getMessage())
    //         .build(), HttpStatus.INTERNAL_SERVER_ERROR);
    // }
}
