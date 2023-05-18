package com.juhai.api.controller;

import com.juhai.commons.service.ParamterService;
import com.juhai.commons.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Api(value = "获取系统配置相关", tags = "获取系统配置相关")
@RequestMapping("/system")
@RestController
public class SiteConfigController {

    @Autowired
    private ParamterService paramterService;

    @ApiOperation(value = "获取系统配置")
    @GetMapping("/config")
    public R config(HttpServletRequest httpServletRequest) {
        Map<String, String> paramsMap = paramterService.getAllParamByMap();

        Map<String, String> data = new HashMap<>();
        data.put("bian_download_url", paramsMap.get("bian_download_url"));
        data.put("huobi_download_url", paramsMap.get("huobi_download_url"));
        data.put("online_service", paramsMap.get("online_service"));
        data.put("home_notice", paramsMap.get("home_notice"));
        return R.ok().put("data", data);
    }
}
