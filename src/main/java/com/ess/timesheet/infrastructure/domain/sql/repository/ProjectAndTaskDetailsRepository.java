package com.ess.timesheet.infrastructure.domain.sql.repository;

import com.ess.timesheet.infrastructure.domain.sql.model.ProjectAndTaskDetailsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectAndTaskDetailsRepository extends JpaRepository<ProjectAndTaskDetailsEntity,Long> {
}
