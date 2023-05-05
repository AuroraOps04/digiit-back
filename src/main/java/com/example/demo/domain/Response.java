package com.example.demo.domain;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

/**
 * @author AuroraOps04
 * @date 2022/06/15
 * 统一响应体封装
 */
@Data
@ToString
@Builder
public class Response {
    // 请求是否成功
    private boolean success;
    // 错误码
    private String errorCode;
    // 错误信息
    private String errorMessage;
    // 错误展示级别
    private Integer showType;
    // 数据
    private Object data;
    // 数据条数
    private Long count;
}
