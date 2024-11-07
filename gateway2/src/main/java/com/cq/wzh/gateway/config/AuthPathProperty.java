package com.cq.wzh.gateway.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;


/**
 * @Author: 王振华
 * @Date: 2024/9/19 0019 9:19
 * @Description:
 */
@ConfigurationProperties(prefix = "auth")
@Component
@Data
public class AuthPathProperty {
    private List<String> includePath;
    private List<String> excludePath;
}
