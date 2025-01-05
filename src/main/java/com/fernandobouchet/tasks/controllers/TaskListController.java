package com.fernandobouchet.tasks.controllers;

import com.fernandobouchet.tasks.domain.dto.TaskListDto;
import com.fernandobouchet.tasks.domain.entities.TaskList;
import com.fernandobouchet.tasks.mappers.TaskListMapper;
import com.fernandobouchet.tasks.services.TaskListService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/task-lists")
public class TaskListController {

    private final TaskListService taskListService;
    private final TaskListMapper taskListMapper;

    public TaskListController(TaskListService taskListService, TaskListMapper taskListMapper) {
        this.taskListService = taskListService;
        this.taskListMapper = taskListMapper;
    }


    @GetMapping
    public List<TaskListDto> listTaskList() {
        return taskListService.listTaskLists()
                .stream()
                .map(taskListMapper::toDto)
                .toList();
    }

    @PostMapping
    public TaskListDto createTaskList (@RequestBody TaskListDto taskListDto) {
       TaskList createdTaskList =  taskListService.createTaskList(taskListMapper.fromDto(taskListDto));
       return taskListMapper.toDto(createdTaskList);
    }
}
