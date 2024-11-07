package com.cq.wzh.gateway.config;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import io.jsonwebtoken.Claims;
import io.netty.util.internal.StringUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.RequestPath;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.List;

/**
 * @Author: 王振华
 * @Date: 2024/9/18 0018 10:43
 * @Description:
 */
@Slf4j
@Configuration
//@ConditionalOnClass(DisPatcherServlet.class)

//@Order(0)
@RequiredArgsConstructor
public class MyGlobalGatewayFilter implements GlobalFilter, Ordered {
    private final AuthPathProperty authPathProperty;
    @Autowired
    private final JwtUtil jwtUtil;
    private final AntPathMatcher antPathMatcher;
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        //获取请求
        ServerHttpRequest request = exchange.getRequest();
        //设置路径白名单
        RequestPath path = request.getPath();
        if (isExcludePath(path.toString())){
            return chain.filter(exchange);
        }
        List<String> headers = request.getHeaders().get("authorization");
        //需要传递的token
        String token;

        if (headers.isEmpty()){
            log.error("请登录后再操作！");
            ServerHttpResponse response = exchange.getResponse();
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            response.setComplete();

        }
        token = headers.get(0);
        Long userId = null;
        try {
            JSONObject entries = new JSONObject(jwtUtil.get(token).toString());
            userId = (Long) entries.get("userId");
        } catch (Exception e) {
            ServerHttpResponse response = exchange.getResponse();
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            response.setComplete();
        }
        //todo 传递用户信息
        String userInfo=userId.toString();
        exchange.mutate().request(builder -> {
            builder.header("user-info",userInfo).build();
        });

        return chain.filter(exchange);
    }

    private boolean isExcludePath(String path) {
        for (String pattenPath: authPathProperty.getExcludePath()){
            if (antPathMatcher.match(pattenPath,path)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
