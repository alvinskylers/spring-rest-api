package com.alvinskylers.taskmanager.dto;

import lombok.Builder;

@Builder
public record TaskResponse(
        Long id,
        String title,
        String description,
        Boolean completed
) {
}
