package com.juhai.api.utils;

import cn.hutool.http.HttpUtil;
import org.lionsoul.ip2region.xdb.Searcher;

import java.util.HashMap;
import java.util.Map;

public class IpUtil {

    private static byte[] BUFF = null;

    public static Map<String, String> IP_DETAIL_CACHE = new HashMap<>();

    static {
//        BUFF = HttpUtil.downloadBytes("https://juhaijituan.oss-ap-southeast-1.aliyuncs.com/gs/ip2region.xdb");
//        IP_DETAIL_CACHE.put("0:0:0:0:0:0:0:1", "本地");
    }

    public static String getIpDetail(String ip) {
//        try {
//            boolean hasKey = IP_DETAIL_CACHE.containsKey(ip);
//            if (hasKey) {
//                return IP_DETAIL_CACHE.get(ip);
//            }
//
//            Searcher searcher = Searcher.newWithBuffer(BUFF);
//            String ipDetail = searcher.search(ip);
//            IP_DETAIL_CACHE.put(ip, ipDetail);
//            return ipDetail;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        System.out.printf("测试");
        IP_DETAIL_CACHE.put(ip, "未知");
        return "未知";
    }
}
