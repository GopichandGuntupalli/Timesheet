package com.ess.timesheet.core.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectAndTaskDetailsDTO {
    private long projectId;
    private String projectName;
    private String description;
    private String taskName;
    private long taskId;
}
