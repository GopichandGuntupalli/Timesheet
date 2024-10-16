package com.ess.timesheet.infrastructure.domain.sql.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "APPROVAL")
public class ApprovalWorkflowEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "AWF_ID")
    private Long id;

    @Column(name = "AWF_SUBMISSION_DATE")
    private LocalDateTime submissionDate;


    @Column(name = "AWF_SUBMISSION_STATUS")
    private Integer submissionStatus;

    @Column(name = "AWF_APPROVAL_DATE")
    private LocalDateTime approvalDate;

    @Column(name = "AWF_APPROVAL")
    private Boolean approval;

    @Column(name = "AWF_RAJECTION_REASON")
    private String rejectionReason;

    // One-to-One with TimesheetEntity
//    @OneToOne(mappedBy = "approvalWorkflow")
//    private TimesheetEntity timesheet;

}
