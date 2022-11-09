package com.akila.hrms.jobleave;

import com.akila.hrms.employee.Employee;
import com.akila.hrms.employee.EmployeeRepository;
import com.akila.hrms.error.ResponseMsg;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class JobLeaveServiceImplementation implements JobLeaveService {

    private final JobLeaveRepository jobLeaveRepository;
    private final EmployeeRepository employeeRepository;

    private JobLeave convertDTOToEntity(JobLeaveRequestDTO jobLeaveResponseDTO) {
        JobLeave jobLeave = new JobLeave();
        Employee employee = employeeRepository.findByEmployeeId(jobLeaveResponseDTO.getEmpId());
        jobLeave.setEmployee(employee);
        jobLeave.setToDate(jobLeaveResponseDTO.getToDate());
        jobLeave.setFromDate(jobLeaveResponseDTO.getFromDate());
        jobLeave.setDescription(jobLeaveResponseDTO.getDescription());
        return jobLeave;
    }


    private JobLeaveResponseDTO convertEntityToDTO(JobLeave jobLeave) {
        JobLeaveResponseDTO jobLeaveResponseDTO = new JobLeaveResponseDTO();

        jobLeaveResponseDTO.setLeaveId(jobLeave.getLeaveId());
        jobLeaveResponseDTO.setFromDate(jobLeave.getFromDate());
        jobLeaveResponseDTO.setToDate(jobLeave.getToDate());
        jobLeaveResponseDTO.setDescription(jobLeave.getDescription());
        jobLeaveResponseDTO.setEmpId(jobLeave.getEmployee().getEmpId());
        jobLeaveResponseDTO.setName(jobLeave.getEmployee().getName());

        return jobLeaveResponseDTO;
    }

    @Override
    public List<JobLeaveResponseDTO> getLeaves() {
        return jobLeaveRepository.findAll().stream().map(this::convertEntityToDTO).collect(Collectors.toList());
    }

    @Override
    public JobLeave addJobLeave(JobLeaveRequestDTO jobLeaveRequestDTO) {
        return jobLeaveRepository.save(convertDTOToEntity(jobLeaveRequestDTO));
    }

    @Override
    public JobLeaveResponseDTO getLeave(Long id) {
        JobLeave jobLeave = jobLeaveRepository.findJobLeaveById(id);
        return convertEntityToDTO(jobLeave);
    }

    @Override
    public ResponseMsg updateLeave(Long id, JobLeaveRequestDTO jobLeaveRequestDTO) {

        JobLeave existingJobLeave = jobLeaveRepository.findJobLeaveById(id);

        if(existingJobLeave != null){
            existingJobLeave.setFromDate(jobLeaveRequestDTO.getFromDate());
            existingJobLeave.setToDate(jobLeaveRequestDTO.getToDate());
            existingJobLeave.setDescription(jobLeaveRequestDTO.getDescription());

            jobLeaveRepository.save(existingJobLeave);

            return new ResponseMsg("Leave Successfully Updated", "Success");
        }
        else {
            return new ResponseMsg("Error in Update", "Error");
        }
    }

    @Override
    public ResponseMsg deleteLeave(Long id) {

        JobLeave existingLeave = jobLeaveRepository.findJobLeaveById(id);

        if (existingLeave == null) {
            return new ResponseMsg("Leave Not Found", "Error");
        } else {
            jobLeaveRepository.deleteByLeaveId(id);
            return new ResponseMsg("Leave Deleted Successfully", "Success");
        }
    }
}
