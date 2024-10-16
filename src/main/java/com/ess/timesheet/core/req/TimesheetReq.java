package com.ess.timesheet.core.req;

import com.ess.timesheet.core.dto.TimesheetDTO;
import lombok.Data;

@Data
public class TimesheetReq {
    private TimesheetDTO timesheetDTO = new TimesheetDTO();
}
