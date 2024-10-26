package com.ess.timesheet.infrastructure.domain.sql.model;

import com.ess.timesheet.core.util.SubmissionStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "TIMESHEET")
public class TimesheetEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long employeeId;
    private String employeeName;
    private LocalDate periodStart;
    private LocalDate periodEnd;

    @ElementCollection
    @CollectionTable(name = "DAILY_HOURS", joinColumns = @JoinColumn(name = "timesheet_id"))
    @MapKeyColumn(name = "day_of_week")
    private Map<DayOfWeek, Integer> dailyHours = new HashMap<>();

    private String projectId;
    private String assignmentId;

    @Enumerated(EnumType.STRING)
    private SubmissionStatus status;

    private LocalDate submissionDate; // Date of timesheet submission
    private LocalDate approvalDate; // Date of timesheet approval
    private String approver; // Name of the approver
    private String rejectionReason; // Reason for rejection

    private Integer totalHours; // Total hours for the timesheet period

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt; // Timestamp for creation

    @Column(name = "updated_at")
    private LocalDateTime updatedAt; // Timestamp for last update

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
