package com.alvinskylers.taskmanager.entity;

import lombok.*;

import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Task {
    private Long id;
    private String title;
    private String description;
    private Boolean completed;
    private LocalDateTime createdAt;
}
