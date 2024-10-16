package com.ess.timesheet.core.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TimeEntryDTO {
    private LocalDate date;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Double totalHours;
    private Double breaks;
    private Boolean billable;
}
