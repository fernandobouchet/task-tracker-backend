package com.fernandobouchet.tasks.mappers;

import com.fernandobouchet.tasks.domain.dto.TaskDto;
import com.fernandobouchet.tasks.domain.entities.Task;

public interface TaskMapper {
    Task fromDto(TaskDto taskDto);

    TaskDto toDto(Task task);
}
