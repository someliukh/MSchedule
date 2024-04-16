package com.example.mschedule.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TaskRequest {

    private Integer id;
    private String subject;
    private String description;
    private String startTime;
    private String endTime;

}

