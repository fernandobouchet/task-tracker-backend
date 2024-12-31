package com.fernandobouchet.tasks.dto;

import com.fernandobouchet.tasks.entities.TaskPriority;
import com.fernandobouchet.tasks.entities.TaskStatus;

import java.time.LocalDateTime;
import java.util.UUID;

public record TaskDto(
        UUID id,
        String title,
        String description,
        LocalDateTime dueDate,
        TaskPriority priority,
        TaskStatus status
) {
}
