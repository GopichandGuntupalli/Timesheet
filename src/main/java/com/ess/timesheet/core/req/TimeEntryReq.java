package com.ess.timesheet.core.req;

import com.ess.timesheet.core.dto.TimeEntryDTO;
import lombok.Data;


@Data
public class TimeEntryReq {
    private TimeEntryDTO timeEntryDTO=new TimeEntryDTO();
}
