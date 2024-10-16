package com.ess.timesheet.infrastructure.domain.sql.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "TIMESHEET")

public class TimesheetEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TS_TIMESHEET_ID")
    private Long timesheetId;

    @Column(name = "TS_EMPLOYEE_NAME")
    private String employeeName;

    @Column(name = "TS_EMPLOYEE_ID")
    private Long employeeId;

    @Column(name = "TS_DEPARTMENT")
    private String department;

    @Column(name = "TS_PERIOD")
    private String period;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "approval_workflow_id")
    private ApprovalWorkflowEntity approvalWorkflow;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "project_and_task_details_id")
    private ProjectAndTaskDetailsEntity projectAndTaskDetails;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "timesheet_id")
    private List<TimeEntryEntity> timeEntries;



}



