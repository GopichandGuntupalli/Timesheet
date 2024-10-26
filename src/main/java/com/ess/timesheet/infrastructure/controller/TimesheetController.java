package com.ess.timesheet.infrastructure.controller;

import com.ess.timesheet.core.api.TimesheetService;
import com.ess.timesheet.core.dto.TimesheetDTO;
import com.ess.timesheet.core.req.RejectTimesheetRequest;
import com.ess.timesheet.core.util.SubmissionStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/timesheets")
public class TimesheetController {

    @Autowired
    private TimesheetService timesheetService;

    @PostMapping("/submit")
    public ResponseEntity<TimesheetDTO> submitTimesheet(@RequestBody TimesheetDTO request) {
        return ResponseEntity.ok(timesheetService.submitTimesheet(request));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<TimesheetDTO> updateTimesheet(@PathVariable Long id, @RequestBody TimesheetDTO request) {
        return ResponseEntity.ok(timesheetService.updateTimesheet(id, request));
    }

    @PostMapping("/approve/{id}")
    public ResponseEntity<TimesheetDTO> approveTimesheet(@PathVariable Long id, @RequestBody String approver) {
        return ResponseEntity.ok(timesheetService.approveTimesheet(id, approver));
    }

    @PostMapping("/reject/{id}")
    public ResponseEntity<TimesheetDTO> rejectTimesheet(@PathVariable Long id, @RequestBody RejectTimesheetRequest request) {
        return ResponseEntity.ok(timesheetService.rejectTimesheet(id, request));
    }

    @PostMapping("/resubmit/{id}")
    public ResponseEntity<TimesheetDTO> resubmitTimesheet(@PathVariable Long id, @RequestBody TimesheetDTO request) {
        return ResponseEntity.ok(timesheetService.resubmitTimesheet(id, request));
    }

    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<List<TimesheetDTO>> getEmployeeTimesheets(@PathVariable Long employeeId) {
        return ResponseEntity.ok(timesheetService.getEmployeeTimesheets(employeeId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TimesheetDTO> getTimesheetById(@PathVariable Long id) {
        return ResponseEntity.ok(timesheetService.getTimesheetById(id));
    }

    @GetMapping("/defaultHours")
    public ResponseEntity<Map<String, Integer>> getDefaultHours() {
        return ResponseEntity.ok(timesheetService.getDefaultHours());
    }

    @GetMapping("/statusOptions")
    public ResponseEntity<List<String>> getStatusOptions() {
        return ResponseEntity.ok(timesheetService.getStatusOptions());
    }

    @GetMapping("/for-approval")
    public ResponseEntity<List<TimesheetDTO>> getTimesheetsForApproval() {
        List<TimesheetDTO> timesheetsForApproval = timesheetService.getTimesheetsForApproval();
        return ResponseEntity.ok(timesheetsForApproval);
    }
}
