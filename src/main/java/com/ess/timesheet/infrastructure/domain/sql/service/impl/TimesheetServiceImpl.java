package com.ess.timesheet.infrastructure.domain.sql.service.impl;

import com.ess.timesheet.core.api.TimesheetService;
import com.ess.timesheet.core.dto.ApprovalWorkflowDTO;
import com.ess.timesheet.core.dto.ProjectAndTaskDetailsDTO;
import com.ess.timesheet.core.dto.TimeEntryDTO;
import com.ess.timesheet.core.dto.TimesheetDTO;
import com.ess.timesheet.infrastructure.domain.sql.model.ApprovalWorkflowEntity;
import com.ess.timesheet.infrastructure.domain.sql.model.ProjectAndTaskDetailsEntity;
import com.ess.timesheet.infrastructure.domain.sql.model.TimeEntryEntity;
import com.ess.timesheet.infrastructure.domain.sql.model.TimesheetEntity;
import com.ess.timesheet.infrastructure.domain.sql.repository.TimesheetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TimesheetServiceImpl implements TimesheetService {

    @Autowired
    private TimesheetRepository timesheetRepository;

    @Override
    public TimesheetDTO createTimesheet(TimesheetDTO timesheetDTO) {
        TimesheetEntity timesheetEntity = toEntity(timesheetDTO);
        TimesheetEntity savedTimesheet = timesheetRepository.save(timesheetEntity);
        return toDTO(savedTimesheet);
    }

    @Override
    public TimesheetDTO getTimesheetById(Long id) {
        TimesheetEntity timesheet = timesheetRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Timesheet not found"));
        return toDTO(timesheet);
    }

    @Override
    public TimesheetDTO updateTimesheet(Long id, TimesheetDTO timesheetDTO) {
        TimesheetEntity existingTimesheet = timesheetRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Timesheet not found"));

        existingTimesheet.setEmployeeName(timesheetDTO.getEmployeeName());
        existingTimesheet.setEmployeeId(timesheetDTO.getEmployeeId());
        existingTimesheet.setDepartment(timesheetDTO.getDepartment());
        existingTimesheet.setPeriod(timesheetDTO.getPeriod());

        if (timesheetDTO.getTimeEntryDTOList() != null) {
            List<TimeEntryEntity> timeEntryEntities = new ArrayList<>();
            for (TimeEntryDTO timeEntryDTO : timesheetDTO.getTimeEntryDTOList()) {
                TimeEntryEntity timeEntryEntity = toTimeEntryEntity(timeEntryDTO);
                timeEntryEntity.setTimesheet(existingTimesheet);  // Set parent relationship
                timeEntryEntities.add(timeEntryEntity);
            }
            existingTimesheet.setTimeEntries(timeEntryEntities);
        }

        if (timesheetDTO.getProjectAndTaskDetailsDTO() != null) {
            existingTimesheet.setProjectAndTaskDetails(toProjectAndTaskDetailsEntity(timesheetDTO.getProjectAndTaskDetailsDTO()));
        }

        if (timesheetDTO.getApprovalWorkflowDTO() != null) {
            existingTimesheet.setApprovalWorkflow(toApprovalWorkflowEntity(timesheetDTO.getApprovalWorkflowDTO()));
        }

        TimesheetEntity updatedTimesheet = timesheetRepository.save(existingTimesheet);
        return toDTO(updatedTimesheet);
    }

    @Override
    public List<TimesheetDTO> getAllTimesheets() {
        List<TimesheetEntity> timesheets = timesheetRepository.findAll();
        List<TimesheetDTO> timesheetDTOList = new ArrayList<>();
        for (TimesheetEntity entity : timesheets) {
            timesheetDTOList.add(toDTO(entity));
        }
        return timesheetDTOList;
    }

    @Override
    public void deleteTimesheet(Long id) {
        TimesheetEntity timesheet = timesheetRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Timesheet not found"));
        timesheetRepository.delete(timesheet);
    }

    // Conversion methods
    private TimesheetDTO toDTO(TimesheetEntity entity) {
        TimesheetDTO dto = new TimesheetDTO();
        dto.setTimesheetId(entity.getTimesheetId());
        dto.setEmployeeName(entity.getEmployeeName());
        dto.setEmployeeId(entity.getEmployeeId());
        dto.setDepartment(entity.getDepartment());
        dto.setPeriod(entity.getPeriod());

        if (entity.getTimeEntries() != null) {
            List<TimeEntryDTO> timeEntryDTOList = new ArrayList<>();
            for (TimeEntryEntity timeEntry : entity.getTimeEntries()) {
                timeEntryDTOList.add(toTimeEntryDTO(timeEntry));
            }
            dto.setTimeEntryDTOList(timeEntryDTOList);
        }

        if (entity.getProjectAndTaskDetails() != null) {
            dto.setProjectAndTaskDetailsDTO(toProjectAndTaskDetailsDTO(entity.getProjectAndTaskDetails()));
        }

        if (entity.getApprovalWorkflow() != null) {
            dto.setApprovalWorkflowDTO(toApprovalWorkflowDTO(entity.getApprovalWorkflow()));
        }

        return dto;
    }

    private TimesheetEntity toEntity(TimesheetDTO dto) {
        TimesheetEntity entity = new TimesheetEntity();
        entity.setEmployeeName(dto.getEmployeeName());
        entity.setEmployeeId(dto.getEmployeeId());
        entity.setDepartment(dto.getDepartment());
        entity.setPeriod(dto.getPeriod());

        // Ensure child entities are properly set
        if (dto.getApprovalWorkflowDTO() != null) {
            entity.setApprovalWorkflow(toApprovalWorkflowEntity(dto.getApprovalWorkflowDTO()));
        }

        if (dto.getProjectAndTaskDetailsDTO() != null) {
            entity.setProjectAndTaskDetails(toProjectAndTaskDetailsEntity(dto.getProjectAndTaskDetailsDTO()));
        }

        if (dto.getTimeEntryDTOList() != null) {
            List<TimeEntryEntity> timeEntryEntities = dto.getTimeEntryDTOList()
                    .stream()
                    .map(this::toTimeEntryEntity)
                    .collect(Collectors.toList());
            entity.setTimeEntries(timeEntryEntities);
        }

        return entity;
    }

    private TimeEntryDTO toTimeEntryDTO(TimeEntryEntity entity) {
        TimeEntryDTO dto = new TimeEntryDTO();
        dto.setDate(entity.getDate());
        dto.setStartTime(entity.getStartTime());
        dto.setEndTime(entity.getEndTime());
        dto.setTotalHours(entity.getTotalHours());
        dto.setBreaks(entity.getBreaks());
        dto.setBillable(entity.getBillable());
        return dto;
    }

    private TimeEntryEntity toTimeEntryEntity(TimeEntryDTO dto) {
        TimeEntryEntity entity = new TimeEntryEntity();
        entity.setDate(dto.getDate());
        entity.setStartTime(dto.getStartTime());
        entity.setEndTime(dto.getEndTime());
        entity.setTotalHours(dto.getTotalHours());
        entity.setBreaks(dto.getBreaks());
        entity.setBillable(dto.getBillable());
        return entity;
    }

    private ProjectAndTaskDetailsDTO toProjectAndTaskDetailsDTO(ProjectAndTaskDetailsEntity entity) {
        ProjectAndTaskDetailsDTO dto = new ProjectAndTaskDetailsDTO();
        dto.setProjectId(entity.getProjectId());
        dto.setProjectName(entity.getProjectName());
        dto.setTaskName(entity.getTaskName());
        dto.setTaskId(entity.getTaskId());
        dto.setDescription(entity.getDescription());
        return dto;
    }

    private ProjectAndTaskDetailsEntity toProjectAndTaskDetailsEntity(ProjectAndTaskDetailsDTO dto) {
        ProjectAndTaskDetailsEntity entity = new ProjectAndTaskDetailsEntity();
        entity.setProjectId(dto.getProjectId());
        entity.setProjectName(dto.getProjectName());
        entity.setTaskName(dto.getTaskName());
        entity.setTaskId(dto.getTaskId());
        entity.setDescription(dto.getDescription());
        return entity;
    }

    private ApprovalWorkflowDTO toApprovalWorkflowDTO(ApprovalWorkflowEntity entity) {
        ApprovalWorkflowDTO dto = new ApprovalWorkflowDTO();
        dto.setSubmissionDate(entity.getSubmissionDate());
        dto.setSubmissionStatus(entity.getSubmissionStatus());
        dto.setApprovalDate(entity.getApprovalDate());
        dto.setApproval(entity.getApproval());
        dto.setRejectionReason(entity.getRejectionReason());
        return dto;
    }

    private ApprovalWorkflowEntity toApprovalWorkflowEntity(ApprovalWorkflowDTO dto) {
        ApprovalWorkflowEntity entity = new ApprovalWorkflowEntity();
        entity.setSubmissionDate(dto.getSubmissionDate());
        entity.setSubmissionStatus(dto.getSubmissionStatus());
        entity.setApprovalDate(dto.getApprovalDate());
        entity.setApproval(dto.getApproval());
        entity.setRejectionReason(dto.getRejectionReason());
        return entity;
    }
}
