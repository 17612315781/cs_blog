package com.cq.wzh.gateway.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @Author: 王振华
 * @Date: 2024/9/19 0019 18:07
 * @Description:
 */
@Data
@AllArgsConstructor
public class ResponseException {
    private String msg;
    private String code;
}
