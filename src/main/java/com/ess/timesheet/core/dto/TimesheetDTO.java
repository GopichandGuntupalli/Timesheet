package com.ess.timesheet.core.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TimesheetDTO {
    private Long timesheetId;
    private String employeeName;
    private Long employeeId;
    private String department;
    private String period;
    private List<TimeEntryDTO> timeEntryDTOList;
    private ProjectAndTaskDetailsDTO projectAndTaskDetailsDTO;
    private ApprovalWorkflowDTO approvalWorkflowDTO;

}
