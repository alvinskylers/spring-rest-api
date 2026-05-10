package com.alvinskylers.taskmanager.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/info")
public class InfoController {

    @Value("${app.name}")
    private String name;

    @Value("${app.version}")
    private String version;

    @Value("${app.max-task-per-page}")
    private String maxPerPage;

    @GetMapping
    public Map<String, Object> appInfo(){
        Map<String, Object> info = new HashMap<>();
        info.put("app name: " , name);
        info.put("app version: " , version);
        info.put("max info per-page: ", maxPerPage);
        return info;
    }

}
