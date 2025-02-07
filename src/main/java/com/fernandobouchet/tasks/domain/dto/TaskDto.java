package com.fernandobouchet.tasks.domain.dto;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.fernandobouchet.tasks.domain.entities.TaskPriority;
import com.fernandobouchet.tasks.domain.entities.TaskStatus;

import java.time.LocalDateTime;
import java.util.UUID;

public record TaskDto(
        UUID id,
        String title,
        String description,
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
        LocalDateTime dueDate,
        TaskPriority priority,
        TaskStatus status
) {
}
