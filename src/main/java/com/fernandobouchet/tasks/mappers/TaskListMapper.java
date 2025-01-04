package com.fernandobouchet.tasks.mappers;

import com.fernandobouchet.tasks.domain.dto.TaskListDto;
import com.fernandobouchet.tasks.domain.entities.TaskList;

public interface TaskListMapper {

     TaskList fromDto(TaskListDto taskListDto);

     TaskListDto toDto(TaskList taskList);
}
