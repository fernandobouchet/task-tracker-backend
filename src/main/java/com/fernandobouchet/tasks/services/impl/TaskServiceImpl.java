package com.fernandobouchet.tasks.services.impl;

import com.fernandobouchet.tasks.domain.entities.Task;
import com.fernandobouchet.tasks.repositories.TaskListRepository;
import com.fernandobouchet.tasks.repositories.TaskRepository;
import com.fernandobouchet.tasks.services.TaskService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskListRepository taskListRepository;

    private final TaskRepository taskRepository;

    public TaskServiceImpl(TaskListRepository taskListRepository, TaskRepository taskRepository) {
        this.taskListRepository = taskListRepository;
        this.taskRepository = taskRepository;
    }


    @Override
    public List<Task> listTasks(UUID taskListId) {
        return taskRepository.findByTaskListId(taskListId);
    }
}
