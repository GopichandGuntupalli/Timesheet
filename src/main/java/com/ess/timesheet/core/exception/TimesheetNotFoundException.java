package com.ess.timesheet.core.exception;

public class TimesheetNotFoundException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public TimesheetNotFoundException(String message) {
        super(message);
    }

    public TimesheetNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}