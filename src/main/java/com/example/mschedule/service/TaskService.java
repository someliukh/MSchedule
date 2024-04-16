package com.example.mschedule.service;

import com.example.mschedule.dto.TaskRequest;
import com.example.mschedule.entity.Task;
import com.example.mschedule.entity.User;

import java.util.List;

public interface TaskService {

    Task createTaskForUser(User user, TaskRequest request);

    long taskNumber(Integer id);

}
