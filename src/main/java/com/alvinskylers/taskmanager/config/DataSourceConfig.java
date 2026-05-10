package com.alvinskylers.taskmanager.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

public class DataSourceConfig {

    @Bean
    @Profile("dev")
    public String devDataSource() {
        return "H2 In-Memory Database";
    }

    @Bean
    @Profile("prod")
    public String prodDataSource() {
        return "PostgreSQL";
    }
}
