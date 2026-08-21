package com.yonsai.Day63_20260821.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class AttendanceResponseDTO {
    private String studentName;

    private LocalDateTime checkTime;

    private String status;

}
