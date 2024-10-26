package com.ess.timesheet.core.api;


import com.ess.timesheet.core.dto.TimesheetDTO;
import com.ess.timesheet.core.req.RejectTimesheetRequest;
import com.ess.timesheet.core.util.SubmissionStatus;

import java.util.List;
import java.util.Map;

public interface TimesheetService {
    TimesheetDTO submitTimesheet(TimesheetDTO request);

    TimesheetDTO updateTimesheet(Long id, TimesheetDTO request);

    TimesheetDTO approveTimesheet(Long id, String approver);

    TimesheetDTO rejectTimesheet(Long id, String approver, String reason);

    TimesheetDTO resubmitTimesheet(Long id, TimesheetDTO request);

    List<TimesheetDTO> getEmployeeTimesheets(Long employeeId);

    List<TimesheetDTO> getTimesheetsForApproval();

    TimesheetDTO getTimesheetById(Long id);

    Map<String, Integer> getDefaultHours();

    List<String> getStatusOptions();
    TimesheetDTO rejectTimesheet(Long id, RejectTimesheetRequest request);

    //List<TimesheetDTO> getTimesheetsByStatus(SubmissionStatus submissionStatus);
}
