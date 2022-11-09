package com.akila.hrms.jobleave;

import com.akila.hrms.error.ResponseMsg;

import java.util.List;

public interface JobLeaveService {
    List<JobLeaveResponseDTO> getLeaves();

    JobLeave addJobLeave(JobLeaveRequestDTO jobLeaveRequestDTO);

    JobLeaveResponseDTO getLeave(Long id);

    ResponseMsg updateLeave(Long id, JobLeaveRequestDTO jobLeaveRequestDTO);

    ResponseMsg deleteLeave(Long id);
}
