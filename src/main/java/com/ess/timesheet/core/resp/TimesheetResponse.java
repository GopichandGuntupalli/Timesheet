package com.ess.timesheet.core.resp;

import com.ess.timesheet.core.dto.TimesheetDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TimesheetResponse {
    private boolean success;
    private String message;
    private TimesheetDTO tImesheetDTO;
}
