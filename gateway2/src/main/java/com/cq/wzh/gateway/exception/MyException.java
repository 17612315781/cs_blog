package com.cq.wzh.gateway.exception;

import lombok.Data;

/**
 * @Author: 王振华
 * @Date: 2024/9/19 0019 17:32
 * @Description:
 */
@Data
public class MyException extends RuntimeException {
    private String msg;
    private String code;
}
