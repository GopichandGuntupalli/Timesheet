package com.ess.timesheet.infrastructure.domain.sql.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor

@Table(name = "TASKDETAILS")
public class ProjectAndTaskDetailsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "PTD_PROJECT-ID")
    private Long projectId;

    @Column(name = "PTD_PROJECT-NAME")
    private String projectName;

    @Column(name = "PTD_DESCRIPTION")
    private String description;

    @Column(name = "PTD_TASK_NAME")
    private String taskName;

    @Column(name = "PTD_TASK_ID")
    private Long taskId;
    // One-to-One with TimesheetEntity
//    @OneToOne(mappedBy = "projectAndTaskDetails")
//    private TimesheetEntity timesheet;

}
