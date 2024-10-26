package com.ess.timesheet.core.dto;

import com.ess.timesheet.core.util.SubmissionStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TimesheetDTO {
    private Long id;
    private Long employeeId;
    private String employeeName;
    private LocalDate periodStart;
    private LocalDate periodEnd;
    private Map<DayOfWeek, Integer> dailyHours; // Day-wise hours worked
    private String projectId;
    private String assignmentId;
    private SubmissionStatus status; // Current status (e.g., SUBMITTED, APPROVED, REJECTED)
    private String Approver; // Name of the approver, if approved
    private String rejectionReason; // Reason if the timesheet is rejected
    private LocalDate submissionDate; // Date of timesheet submission
    private LocalDate approvalDate; // Date of timesheet approval, if applicable
    private Integer totalHours; // Calculated total hours for the timesheet period
}
