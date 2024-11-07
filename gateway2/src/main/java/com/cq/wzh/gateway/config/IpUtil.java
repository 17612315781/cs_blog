package com.cq.wzh.gateway.config;

/**
 * @Author: 王振华
 * @Date: 2024/11/7 星期四 12:11
 * @Description:
 */
public class IpUtil {
    public static boolean isInternalIp(String ip) {
        String[] internalIps = {"127.0.0.1", "localhost", "0:0:0:0:0:0:0:1"};
        for (String internalIp : internalIps) {
            if (internalIp.equals(ip)) {
                return true;
            }
        }
        return false;
    }
}
