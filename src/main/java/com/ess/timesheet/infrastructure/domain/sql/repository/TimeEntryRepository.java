package com.ess.timesheet.infrastructure.domain.sql.repository;

import com.ess.timesheet.infrastructure.domain.sql.model.TimeEntryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TimeEntryRepository extends JpaRepository<TimeEntryEntity,Long> {
}
