package com.ess.timesheet.core.req;

import com.ess.timesheet.core.dto.ApprovalWorkflowDTO;
import lombok.Data;

import java.time.LocalDateTime;
@Data
public class ApprovalWorkflowReq {
    private ApprovalWorkflowDTO approvalWorkflowDTO=new ApprovalWorkflowDTO();
}
