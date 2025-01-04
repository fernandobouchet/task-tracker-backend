package com.fernandobouchet.tasks.services;

import com.fernandobouchet.tasks.domain.entities.TaskList;

import java.util.List;

public interface TaskListService {
    List<TaskList> listTaskLists();
}
