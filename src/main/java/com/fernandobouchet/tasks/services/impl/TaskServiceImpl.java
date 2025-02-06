package com.fernandobouchet.tasks.services.impl;

import com.fernandobouchet.tasks.domain.entities.Task;
import com.fernandobouchet.tasks.domain.entities.TaskList;
import com.fernandobouchet.tasks.domain.entities.TaskPriority;
import com.fernandobouchet.tasks.domain.entities.TaskStatus;
import com.fernandobouchet.tasks.repositories.TaskListRepository;
import com.fernandobouchet.tasks.repositories.TaskRepository;
import com.fernandobouchet.tasks.services.TaskService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
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

    @Transactional
    @Override
    public Task createTask(UUID taskListId, Task task) {
        if (null != task.getId()) {
            throw new IllegalArgumentException("Task already has an ID");
        }

        if (null == task.getTitle() || task.getTitle().isBlank()) {
            throw new IllegalArgumentException("Task must hava a title");
        }

        TaskPriority priority = Optional.ofNullable(task.getPriority())
                .orElse(TaskPriority.MEDIUM);

        TaskList taskList = taskListRepository
                .findById(taskListId)
                .orElseThrow(() ->
                        new IllegalArgumentException("Invalid Task List ID provided"));

        LocalDateTime now = LocalDateTime.now();

        return taskRepository.save(
                new Task(
                        null,
                        task.getTitle(),
                        task.getDescription(),
                        task.getDueDate(),
                        TaskStatus.OPEN,
                        priority,
                        taskList,
                        now,
                        now
                ));

    }

    @Override
    public Optional<Task> getTask(UUID taskListId, UUID taskId) {
        return taskRepository.findByTaskListIdAndId(taskListId, taskId);
    }

    @Transactional
    @Override
    public Task updateTask(UUID taskListId, UUID taskId, Task task) {
        if(null == task.getId()) {
            throw new IllegalArgumentException("Task must hava ID!");
        }

        if(!Objects.equals(taskId,task.getId())) {
            throw new IllegalArgumentException("Task IDs do not match!");
        }

        if(null == task.getPriority()) {
            throw new IllegalArgumentException("Task must have a valid priority!");
        }

        if(null == task.getStatus()) {
            throw new IllegalArgumentException("Task must hava a valid status!");
        }

        Task existingTask = taskRepository.findByTaskListIdAndId(taskListId, task.getId())
                .orElseThrow(()-> new  IllegalStateException("Task not found!"));

        existingTask.setTitle(task.getTitle());
        existingTask.setDescription(task.getDescription());
        existingTask.setDueDate(task.getDueDate());
        existingTask.setPriority(task.getPriority());
        existingTask.setStatus(task.getStatus());
        existingTask.setUpdated(LocalDateTime.now());

        return taskRepository.save(existingTask);
    }

    @Transactional
    @Override
    public void deleteTask(UUID taskListId, UUID id) {
      taskRepository.deleteByTaskListIdAndId(taskListId, id);
    }

}
