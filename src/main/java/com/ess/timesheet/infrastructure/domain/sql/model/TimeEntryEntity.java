package com.ess.timesheet.infrastructure.domain.sql.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "TIMEENTRY")
public class TimeEntryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TE_ID")
    private Long id;

    @Column(name = "TE_DATE")
    private LocalDate date;

    @Column(name = "TE_START_TIME")
    private LocalDateTime startTime;

    @Column(name = "TE_END_TIME")
    private LocalDateTime endTime;

    @Column(name = "TE_TOTALHOURS")
    private Double totalHours;

    @Column(name = "TE_BREAKS")
    private Double breaks;

    @Column(name = "TE_BILLABLE")
    private Boolean billable;

    // Many-to-One relationship with TimesheetEntity
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "timesheet_id")
    private TimesheetEntity timesheet;


}
