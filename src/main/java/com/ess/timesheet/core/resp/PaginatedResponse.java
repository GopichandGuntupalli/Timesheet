package com.ess.timesheet.core.resp;

import com.ess.timesheet.core.dto.TimesheetDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaginatedResponse {
    private List<TimesheetDTO> data;
    private Integer currentPage;
    private Integer totalPages;
    private long totalItems;


    public static PaginatedResponse from(List<TimesheetDTO> data, int currentPage, int totalPages, long totalItems) {
        return new PaginatedResponse(data, currentPage, totalPages, totalItems);
    }
}


