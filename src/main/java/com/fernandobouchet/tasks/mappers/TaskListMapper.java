package com.fernandobouchet.tasks.mappers;

import com.fernandobouchet.tasks.domain.dto.TaskListDto;
import com.fernandobouchet.tasks.domain.entities.TasksList;

public interface TaskListMapper {

     TasksList fromDto(TaskListDto taskListDto);

     TaskListDto toDto(TasksList tasksList);
}
