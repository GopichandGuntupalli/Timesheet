package com.ess.timesheet.infrastructure.domain.sql.service.handler;

import com.ess.timesheet.core.dto.TimesheetDTO;
import com.ess.timesheet.infrastructure.domain.sql.model.TimesheetEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TimeSheetMapperImpl implements TimeSheetMapper {

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public TimesheetEntity toEntity(TimesheetDTO dto) {
        // Map and directly return the result using object initialization
        return dto == null ? null : modelMapper.map(dto, TimesheetEntity.class);
    }

    @Override
    public TimesheetDTO toDto(TimesheetEntity entity) {
        // Map and directly return the result using object initialization
        return entity == null ? null : modelMapper.map(entity, TimesheetDTO.class);
    }

    @Override
    public void updateEntity(TimesheetEntity entity, TimesheetDTO dto) {
        if (dto == null || entity == null) {
            return; // No updates if dto or entity is null
        }

        // Use a utility method to check and set non-null fields
        if (dto.getEmployeeId() != null) {
            entity.setEmployeeId(dto.getEmployeeId());
        }
        if (dto.getSubmissionDate() != null) {
            entity.setSubmissionDate(dto.getSubmissionDate());
        }
        if (dto.getStatus() != null) {
            entity.setStatus(dto.getStatus());
        }
        if (dto.getTotalHours() != null) {
            entity.setTotalHours(dto.getTotalHours());
        }
        if (dto.getApprover() != null) {
            entity.setApprover(dto.getApprover());
        }
        if (dto.getRejectionReason() != null) {
            entity.setRejectionReason(dto.getRejectionReason());
        }
        if (dto.getApprovalDate() != null) {
            entity.setApprovalDate(dto.getApprovalDate());
        }

        // Update the period start and end if present
        if (dto.getPeriodStart() != null) {
            entity.setPeriodStart(dto.getPeriodStart());
        }
        if (dto.getPeriodEnd() != null) {
            entity.setPeriodEnd(dto.getPeriodEnd());
        }

        // Update daily hours if present
        if (dto.getDailyHours() != null) {
            entity.setDailyHours(dto.getDailyHours());
        }

        // You may also want to handle any other fields that need to be updated
    }

}
