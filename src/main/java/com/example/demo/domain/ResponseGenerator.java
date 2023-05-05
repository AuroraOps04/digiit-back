package com.example.demo.domain;

/**
 * @author AuroraOps04
 * @date 2022/3/9 21:42:27
 * @description 响应生成器
 */
public class ResponseGenerator {
    public static Response success() {
        return Response.builder()
                .success(true)
                .build();
    }

    public static Response noLogin(){
       return Response.builder()
               .success(false)
               .errorCode(ErrorCode.NOT_LOGIN.getCode())
               .errorMessage(ErrorCode.NOT_LOGIN.getMessage())
               .build();
    }

    public static Response success(Object data) {
        return Response.builder()
                .success(true)
                .data(data)
                .build();
    }

    public static Response success(Object data, Long count) {
        return Response.builder()
                .success(true)
                .data(data)
                .count(count)
                .build();
    }

    public static Response businessError() {
        return Response.builder()
                .success(false)
                .errorCode(ErrorCode.BUSINESS_ERROR.getCode())
                .errorMessage(ErrorCode.BUSINESS_ERROR.getMessage())
                .build();
    }

    public static Response businessError(String message) {
        return Response.builder()
                .success(false)
                .showType(ErrorCode.BUSINESS_ERROR.getShowType())
                .errorCode(ErrorCode.BUSINESS_ERROR.getCode())
                .errorMessage(message)
                .build();
    }

    public static Response businessError(ErrorCode errorCode) {
        return Response.builder()
                .success(false)
                .errorCode(errorCode.getCode())
                .showType(errorCode.getShowType())
                .errorMessage(errorCode.getMessage())
                .build();
    }

    public static Response silent(String message) {
        return Response.builder()
                .success(false)
                .errorCode("4000")
                .showType(0)
                .errorMessage(message)
                .build();
    }

}
