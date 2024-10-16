package com.ess.timesheet.infrastructure.controller;

import com.ess.timesheet.core.api.TimesheetService;
import com.ess.timesheet.core.dto.TimesheetDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/timesheets")
public class TimesheetController {

    @Autowired
    private TimesheetService timesheetService;

    @PostMapping
    public ResponseEntity<TimesheetDTO> createTimesheet(@RequestBody TimesheetDTO timesheetDTO) {
        TimesheetDTO createdTimesheet = timesheetService.createTimesheet(timesheetDTO);
        return ResponseEntity.ok(createdTimesheet);
    }
    @GetMapping
    public ResponseEntity<List<TimesheetDTO>> getAllTimesheets() {
        List<TimesheetDTO> timesheets = timesheetService.getAllTimesheets();
        return ResponseEntity.ok(timesheets);
    }


    @GetMapping("/{id}")
    public ResponseEntity<TimesheetDTO> getTimesheetById(@PathVariable Long id) {
        TimesheetDTO timesheetDTO = timesheetService.getTimesheetById(id);
        return ResponseEntity.ok(timesheetDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TimesheetDTO> updateTimesheet(@PathVariable Long id, @RequestBody TimesheetDTO timesheetDTO) {
        TimesheetDTO updatedTimesheet = timesheetService.updateTimesheet(id, timesheetDTO);
        return ResponseEntity.ok(updatedTimesheet);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTimesheet(@PathVariable Long id) {
        timesheetService.deleteTimesheet(id);
        return ResponseEntity.noContent().build();
    }
}
