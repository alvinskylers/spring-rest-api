package com.alvinskylers.taskmanager.controller;

import com.alvinskylers.taskmanager.config.AppProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/info/advanced")
public class AdvancedInfoController {

    private final AppProperties appProperties;


    public AdvancedInfoController(AppProperties appProperties) {
        this.appProperties = appProperties;
    }


    @GetMapping
    public Map<String, Object> getInfo() {
        Map<String, Object> map = new HashMap<>();
        map.put("name: ", appProperties.getName());
        map.put("version: ", appProperties.getVersion());
        map.put("max-tasks-per-page: ", appProperties.getMaxTasksPerPage());

        return map;
    }


}
