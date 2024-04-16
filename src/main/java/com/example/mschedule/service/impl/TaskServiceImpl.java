package com.example.mschedule.service.impl;

import com.example.mschedule.dto.TaskRequest;
import com.example.mschedule.entity.Task;
import com.example.mschedule.entity.User;
import com.example.mschedule.repository.TaskRepository;
import com.example.mschedule.service.TaskService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    public Task createTaskForUser(User user, TaskRequest request) {

        Task task = Task.builder()
                .subject(request.getSubject())
                .description(request.getDescription())
                .startTime(request.getStartTime())
                .endTime(request.getEndTime())
                .user(user)
                .build();

        return taskRepository.save(task);
    }

    public long taskNumber(Integer id) {
        return taskRepository.countTaskByUser_Id(id);
    }
}
