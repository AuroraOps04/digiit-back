package com.example.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * <p>标题: </p>
 * <p>描述: </p>
 * <p>版权: Copyright (c) 2022</p>
 * <p>公司: 政安科技</p>
 *
 * @author yanguoxin
 * @date 2022-08-05 11:30
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ResponseVo {
    Integer code;
    String message;
    Object data;
    boolean state;
}
