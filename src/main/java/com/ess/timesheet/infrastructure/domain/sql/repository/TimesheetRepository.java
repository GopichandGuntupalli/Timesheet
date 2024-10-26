package com.ess.timesheet.infrastructure.domain.sql.repository;

import com.ess.timesheet.core.util.SubmissionStatus;
import com.ess.timesheet.infrastructure.domain.sql.model.TimesheetEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface TimesheetRepository extends JpaRepository<TimesheetEntity, Long> {
    List<TimesheetEntity> findByEmployeeId(Long employeeId);
    // Method to find timesheets by their status
    List<TimesheetEntity> findByStatus(SubmissionStatus status);
}
