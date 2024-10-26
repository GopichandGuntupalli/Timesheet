package com.ess.timesheet.infrastructure.domain.sql.service.impl;

import com.ess.timesheet.core.api.TimesheetService;

import com.ess.timesheet.core.dto.TimesheetDTO;
import com.ess.timesheet.core.exception.ResourceNotFoundException;
import com.ess.timesheet.core.exception.TimesheetException;
import com.ess.timesheet.core.req.RejectTimesheetRequest;
import com.ess.timesheet.core.util.SubmissionStatus;
import com.ess.timesheet.infrastructure.domain.sql.model.TimesheetEntity;
import com.ess.timesheet.infrastructure.domain.sql.repository.TimesheetRepository;
import com.ess.timesheet.infrastructure.domain.sql.service.handler.TimeSheetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class TimesheetServiceImpl implements TimesheetService {

    @Autowired
    private TimesheetRepository timesheetRepository;

    @Autowired
    private TimeSheetMapper timeSheetMapper;

    @Override
    public TimesheetDTO submitTimesheet(TimesheetDTO request) {
        validateTimesheetHours(request);
        TimesheetEntity entity = timeSheetMapper.toEntity(request);
        entity.setStatus(SubmissionStatus.SUBMITTED);
        entity.setSubmissionDate(LocalDate.now());

        // Calculate total hours
        int totalHours = request.getDailyHours().values().stream().mapToInt(Integer::intValue).sum();
        entity.setTotalHours(totalHours); // Set the total hours in the entity

        // Save to repository
        TimesheetEntity savedEntity = timesheetRepository.save(entity);

        // Map to DTO and return
        return timeSheetMapper.toDto(savedEntity);
    }


    @Override
    public TimesheetDTO updateTimesheet(Long id, TimesheetDTO request) {
        TimesheetEntity entity = findTimesheetById(id);
        timeSheetMapper.updateEntity(entity, request);  // Ensure a mapper method for updating
        return timeSheetMapper.toDto(timesheetRepository.save(entity));
    }

    @Override
    public TimesheetDTO approveTimesheet(Long id, String approver) {
        TimesheetEntity entity = findTimesheetById(id);
        entity.setStatus(SubmissionStatus.APPROVED);
        entity.setApprover(approver);
        entity.setApprovalDate(LocalDate.now());
        return timeSheetMapper.toDto(timesheetRepository.save(entity));
    }

    @Override
    public TimesheetDTO rejectTimesheet(Long id, String approver, String reason) {
        TimesheetEntity entity = findTimesheetById(id);
        entity.setStatus(SubmissionStatus.REJECTED);
        entity.setRejectionReason(reason);
        entity.setApprover(approver);
        return timeSheetMapper.toDto(timesheetRepository.save(entity));
    }

    @Override
    public TimesheetDTO resubmitTimesheet(Long id, TimesheetDTO request) {
        TimesheetEntity entity = findTimesheetById(id);

        // Update the entity's fields based on the request
        timeSheetMapper.updateEntity(entity, request);

        // Recalculate total hours
        int totalHours = request.getDailyHours().values().stream().mapToInt(Integer::intValue).sum();
        entity.setTotalHours(totalHours); // Update total hours

        // Update the status to SUBMITTED and clear any rejection reason if applicable
        entity.setStatus(SubmissionStatus.SUBMITTED);
        entity.setRejectionReason(null); // Clear rejection reason on resubmission
        entity.setSubmissionDate(LocalDate.now()); // Update submission date

        // Save the updated entity to the repository
        return timeSheetMapper.toDto(timesheetRepository.save(entity));
    }


    @Override
    public List<TimesheetDTO> getEmployeeTimesheets(Long employeeId) {
        return timesheetRepository.findByEmployeeId(employeeId).stream()
                .map(timeSheetMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<TimesheetDTO> getTimesheetsForApproval() {
        List<TimesheetEntity> entities = timesheetRepository.findByStatus(SubmissionStatus.SUBMITTED);
        System.out.println("Fetched entities: " + entities);
        return entities.stream()
                .map(entity -> {
                    TimesheetDTO dto = timeSheetMapper.toDto(entity);
                    System.out.println("Mapped DTO: " + dto);
                    return dto;
                })
                .collect(Collectors.toList());
    }


    @Override
    public TimesheetDTO getTimesheetById(Long id) {
        return timeSheetMapper.toDto(findTimesheetById(id));
    }

    @Override
    public Map<String, Integer> getDefaultHours() {
        return Map.of(
                "MONDAY", 8,
                "TUESDAY", 8,
                "WEDNESDAY", 8,
                "THURSDAY", 8,
                "FRIDAY", 8
        );
    }

    @Override
    public List<String> getStatusOptions() {
        return List.of("DRAFT", "SUBMITTED", "APPROVED", "REJECTED");
    }

    private TimesheetEntity findTimesheetById(Long id) {
        return timesheetRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Timesheet not found with id: " + id));
    }

    private void validateTimesheetHours(TimesheetDTO request) {
        int totalHours = request.getDailyHours().values().stream().mapToInt(Integer::intValue).sum();
        if (totalHours != 40) {
            throw new IllegalArgumentException("Total weekly hours must be exactly 40. Your total: " + totalHours);
        }
    }

    @Override
    public TimesheetDTO rejectTimesheet(Long id, RejectTimesheetRequest request) {
        TimesheetEntity entity = findTimesheetById(id);
        entity.setStatus(SubmissionStatus.REJECTED);
        entity.setRejectionReason(request.getRejectionReason());
        entity.setApprover(request.getApprover());
        return timeSheetMapper.toDto(timesheetRepository.save(entity));
    }

//    @Override
//    public List<TimesheetDTO> getTimesheetsByStatus(SubmissionStatus submissionStatus) {
//        List<TimesheetEntity> timesheets = timesheetRepository.findByStatus(submissionStatus);
//        // Convert timesheets from entity to DTO
//        return convertToDTO(timesheets);
//    }


}


