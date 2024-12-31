package com.fernandobouchet.tasks.mappers.impl;

import com.fernandobouchet.tasks.domain.dto.TaskListDto;
import com.fernandobouchet.tasks.domain.entities.Task;
import com.fernandobouchet.tasks.domain.entities.TaskStatus;
import com.fernandobouchet.tasks.domain.entities.TasksList;
import com.fernandobouchet.tasks.mappers.TaskListMapper;
import com.fernandobouchet.tasks.mappers.TaskMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class TaskListMapperImpl implements TaskListMapper {

    private final TaskMapper taskMapper;

    public TaskListMapperImpl(TaskMapper taskMapper)  {
        this.taskMapper = taskMapper;
    }

    @Override
    public TasksList fromDto(TaskListDto taskListDto) {
        return new TasksList(
                taskListDto.id(),
                taskListDto.title(),
                taskListDto.description(),
                Optional.ofNullable(taskListDto.tasks()).map(
                        tasks -> tasks.stream().map(taskMapper::fromDto)
                                .toList()
                ).orElse(null),
                null,
                null
        );
    }

    @Override
    public TaskListDto toDto(TasksList tasksList) {
        return new TaskListDto(
                tasksList.getId(),
                tasksList.getTitle(),
                tasksList.getDescription(),
                Optional.ofNullable(tasksList.getTasks())
                                .map(List::size)
                        .orElse(0),
                calculateListProgress(tasksList.getTasks()),
                Optional.ofNullable(tasksList.getTasks())
                        .map(tasks -> tasks.stream().map(taskMapper::toDto).toList())
                        .orElse(null)

        );
    }

    private Double calculateListProgress(List<Task> tasks) {
        if(tasks.isEmpty()){
            return  null;
        }

       long closedTaskCount =  tasks.stream().filter(task -> TaskStatus.CLOSED == task.getStatus()).count();

        return (double) closedTaskCount / tasks.size();
    }
}
