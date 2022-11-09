package com.akila.hrms.jobleave;

import com.akila.hrms.employee.Employee;
import com.akila.hrms.employee.EmployeeDTO;
import com.akila.hrms.error.ResponseMsg;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("api/v1/jobleave")
@AllArgsConstructor
public class JobLeaveController {

    private final JobLeaveService jobLeaveService;

    @GetMapping
    public List<JobLeaveResponseDTO> getLeaves(){
        return jobLeaveService.getLeaves();
    }

    @GetMapping("/{id}")
    public JobLeaveResponseDTO getLeave(@PathVariable(name = "id") Long id){
        return jobLeaveService.getLeave(id);
    }


    @PostMapping
    public ResponseEntity<ResponseMsg> addJobLeave(@RequestBody JobLeaveRequestDTO jobLeaveRequestDTO){
        ResponseMsg responseMsg = new ResponseMsg("Successfully Added Job Leave", "Success");

        JobLeave existingJobLeave = jobLeaveService.addJobLeave(jobLeaveRequestDTO);
        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(existingJobLeave.getLeaveId()).toUri()).body(responseMsg);
    }

    @PutMapping("/{id}")
    public ResponseMsg updateLeave(@PathVariable(name = "id") Long id, @RequestBody JobLeaveRequestDTO jobLeaveRequestDTO ){
        return jobLeaveService.updateLeave(id, jobLeaveRequestDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseMsg deleteLeave(@PathVariable(name = "id") Long id ){
        return jobLeaveService.deleteLeave(id);
    }


}
