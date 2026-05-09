package com.alvinskylers.taskmanager.dto;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CategoryRequest(

        @NotNull
        @Size(min=3, max=100)
        String name,

        @Size(min=3, max=100)
        String description
) {
}
