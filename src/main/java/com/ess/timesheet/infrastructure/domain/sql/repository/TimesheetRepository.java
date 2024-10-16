package com.ess.timesheet.infrastructure.domain.sql.repository;

import com.ess.timesheet.infrastructure.domain.sql.model.TimesheetEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TimesheetRepository extends JpaRepository<TimesheetEntity, Long> {
}
