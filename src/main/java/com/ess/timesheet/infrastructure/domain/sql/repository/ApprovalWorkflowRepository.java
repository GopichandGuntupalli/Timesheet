package com.ess.timesheet.infrastructure.domain.sql.repository;

import com.ess.timesheet.infrastructure.domain.sql.model.ApprovalWorkflowEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApprovalWorkflowRepository extends JpaRepository<ApprovalWorkflowEntity,Long> {
}
