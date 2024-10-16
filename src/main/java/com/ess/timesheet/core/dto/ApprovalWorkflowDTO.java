package com.ess.timesheet.core.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApprovalWorkflowDTO {
    private LocalDateTime submissionDate;
    private int submissionStatus;
    private LocalDateTime approvalDate;
    private Boolean approval;
    private String rejectionReason;
}
