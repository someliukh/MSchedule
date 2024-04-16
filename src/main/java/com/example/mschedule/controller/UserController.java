package com.example.mschedule.controller;

import com.example.mschedule.dto.StaffResponse;
import com.example.mschedule.dto.TaskRequest;
import com.example.mschedule.entity.Task;
import com.example.mschedule.entity.User;
import com.example.mschedule.repository.TaskRepository;
import com.example.mschedule.service.TaskService;
import com.example.mschedule.service.UserService;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.CompositeType;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/staff")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173", maxAge = 3600)
public class UserController {

    private final UserService userService;
    private final TaskService taskService;
    private final TaskRepository taskRepository;

    @GetMapping()
    public ResponseEntity<List<StaffResponse>> allStaff() {
        List<User> users = userService.getAllUser();

        List<StaffResponse> staffList = users.stream().map(user -> StaffResponse.builder()
                .id(user.getId())
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .taskNums(taskService.taskNumber(user.getId()))
                .isFree(userService.getFreeStatus(user))
                .build()).toList();

        return ResponseEntity.ok(staffList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StaffResponse> staffById(@PathVariable Integer id) {
        User user = userService.getUserById(id);

        StaffResponse response = StaffResponse.builder()
                .id(user.getId())
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .taskNums(taskService.taskNumber(user.getId()))
                .tasks(user.getTasks())
                .isFree(userService.getFreeStatus(user))
                .build();

        return ResponseEntity.ok(response);
    }

    @PostMapping("/{id}/create-task")
    public ResponseEntity<?> saveRemoveTask(@PathVariable Integer id, @RequestBody List<TaskRequest> request) {
        List<Integer> userTaskIdList = taskRepository.getUserTasksById(id);

//        The Ids should be deleted
        List<Integer> idsToRemove = userTaskIdList.stream()
                .filter(ids -> request.stream().noneMatch(task -> task.getId().equals(ids)))
                .toList();
        List<Long> wrappedListOfDeleteIds = idsToRemove.stream().map(Long::valueOf).toList();
        wrappedListOfDeleteIds.forEach(taskRepository::deleteById);

//        The Ids should be added
        List<Task> savedTasks = request.stream()
                .filter(element -> !userTaskIdList.contains(element.getId()))
                .map(element -> taskService.createTaskForUser(userService.getUserById(Math.toIntExact(id)), element))
                .toList();

        if (savedTasks == null ||
            savedTasks.stream().allMatch(task -> task == null))
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        else
            return ResponseEntity.status(HttpStatus.OK).build();
    }

}

