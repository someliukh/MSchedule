package com.example.mschedule.dto;

import com.example.mschedule.entity.Task;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StaffResponse {

    private Integer id;
    private String firstname;
    private String lastname;
    private Boolean isFree;
    private List<Task> tasks;
    private long taskNums;

}
