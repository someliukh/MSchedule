package com.example.mschedule.controller;

import com.example.mschedule.dto.StaffResponse;
import com.example.mschedule.dto.TaskRequest;
import com.example.mschedule.entity.Task;
import com.example.mschedule.entity.User;
import com.example.mschedule.repository.UserRepository;
import com.example.mschedule.service.TaskService;
import com.example.mschedule.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@RestController
@RequestMapping("/api/v1/staff")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final TaskService taskService;

    @GetMapping()
    public ResponseEntity<List<StaffResponse>> allStaff() {
        List<User> users = userService.getAllUser();

        List<StaffResponse> staffList = users.stream().map(user -> StaffResponse.builder()
                .id(user.getId())
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .taskNums(taskService.taskNumber(user.getId()))
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
                .build();

        return ResponseEntity.ok(response);
    }

    @PostMapping("/{id}/create-task")
    public RedirectView saveTask(@PathVariable Long id, @RequestBody TaskRequest request) {
        String redirectUrl = "http://localhost:8080/api/v1/staff/" + id;
        taskService.createTaskForUser(userService.getUserById(Math.toIntExact(id)), request);

        return new RedirectView(redirectUrl);
    }



}
