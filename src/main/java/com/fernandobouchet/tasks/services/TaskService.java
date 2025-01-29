package com.fernandobouchet.tasks.services;

import com.fernandobouchet.tasks.domain.entities.Task;

import java.util.List;
import java.util.UUID;

public interface TaskService {
    List<Task> listTasks(UUID taskListId);
}
