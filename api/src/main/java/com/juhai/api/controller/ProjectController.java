package com.juhai.api.controller;

import cn.hutool.core.collection.CollUtil;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.juhai.commons.entity.Project;
import com.juhai.commons.service.ParamterService;
import com.juhai.commons.service.ProjectService;
import com.juhai.commons.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Slf4j
@Api(value = "项目相关", tags = "项目相关")
@RequestMapping("/project")
@RestController
public class ProjectController {
    @Autowired
    private ProjectService projectService;

    @Autowired
    private ParamterService paramterService;

    @ApiOperation(value = "获取项目列表")
    @GetMapping("/list")
    public R list(HttpServletRequest httpServletRequest) {
        List<Project> list = projectService.list(
                new LambdaQueryWrapper<Project>()
                        .eq(Project::getStatus, 0)
                        .orderByDesc(Project::getSort)
        );
        JSONArray array = new JSONArray();
        if (CollUtil.isNotEmpty(list)) {
            Map<String, String> map = paramterService.getAllParamByMap();
            for (Project project : list) {
                JSONObject temp = new JSONObject();
                temp.put("projectId", project.getId());
                temp.put("projectName", project.getProjectName());
                temp.put("projectAmount", project.getProjectAmount());
                temp.put("incomeRate", project.getIncomeRate());
                temp.put("limitTime", project.getLimitTime());
                temp.put("minAmount", project.getMinAmount());
                temp.put("startTime", project.getStartTime());
                temp.put("schedule", project.getSchedule());
                temp.put("guaranteeCompany", project.getGuaranteeCompany());
                temp.put("img", map.get("resource_domain") + project.getImg());
                array.add(temp);
            }
        }
        return R.ok().put("data", array);
    }
}
