package com.ess.timesheet.core.api;

import com.ess.timesheet.core.dto.TimesheetDTO;
import com.ess.timesheet.core.req.TimesheetReq;

import java.util.List;

public interface TimesheetService {
    TimesheetDTO createTimesheet(TimesheetDTO timesheetDTO);

    TimesheetDTO getTimesheetById(Long id);

    TimesheetDTO updateTimesheet(Long id, TimesheetDTO timesheetDTO);

    List<TimesheetDTO> getAllTimesheets();

    void deleteTimesheet(Long id);
}
