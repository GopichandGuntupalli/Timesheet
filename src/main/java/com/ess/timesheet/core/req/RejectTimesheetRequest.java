package com.ess.timesheet.core.req;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RejectTimesheetRequest {
    private String approver;
    private String rejectionReason;
}