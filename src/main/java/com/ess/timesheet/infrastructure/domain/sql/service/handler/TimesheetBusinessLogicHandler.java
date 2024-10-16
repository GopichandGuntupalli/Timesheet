package com.ess.timesheet.infrastructure.domain.sql.service.handler;


import com.ess.timesheet.core.dto.TimesheetDTO;
import com.ess.timesheet.core.exception.ValidationException;

public class TimesheetBusinessLogicHandler {

    public void validateTimesheetForSubmission(TimesheetDTO timesheetDTO) {
        if (timesheetDTO.getEmployeeId() == null || timesheetDTO.getEmployeeId() <= 0) {
            throw new ValidationException("Employee ID must be provided and greater than 0");
        }

        if (timesheetDTO.getPeriod() == null) {
            throw new ValidationException("Timesheet period is required");
        }


    }



}

