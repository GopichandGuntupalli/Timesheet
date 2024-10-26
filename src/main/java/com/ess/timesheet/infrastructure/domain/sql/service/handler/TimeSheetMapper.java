package com.ess.timesheet.infrastructure.domain.sql.service.handler;


import com.ess.timesheet.core.dto.TimesheetDTO;
import com.ess.timesheet.infrastructure.domain.sql.model.TimesheetEntity;

public interface TimeSheetMapper {
    TimesheetEntity toEntity(TimesheetDTO dto);
    TimesheetDTO     toDto(TimesheetEntity entity);

    void updateEntity(TimesheetEntity entity, TimesheetDTO dto);
}



