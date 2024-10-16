package com.ess.timesheet.core.exception;

public class TimesheetNotSubmittedException extends RuntimeException {
    public TimesheetNotSubmittedException(String message) {
        super(message);
    }
}
