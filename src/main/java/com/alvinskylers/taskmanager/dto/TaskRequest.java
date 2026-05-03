package com.alvinskylers.taskmanager.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record TaskRequest(

        @NotNull
        @Size(min=5, max=100, message = "Title must be between 5 to 100 characters!")
        String title,

        @Size(min=10, max=500, message = "Description must be between 5 to 500 characters")
        String description,

        Boolean completed
) {
}
