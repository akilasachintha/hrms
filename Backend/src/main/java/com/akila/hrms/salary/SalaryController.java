package com.akila.hrms.salary;

import com.akila.hrms.employee.EmployeeDTO;
import com.akila.hrms.error.ResponseMsg;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/salary")
@CrossOrigin
public class SalaryController {

    private final SalaryService salaryService;

    @GetMapping
    public List<SalaryResponseDTO> getSalaries(){
        return salaryService.getSalaries();
    }

    @GetMapping("/{id}")
    public SalaryResponseDTO getSalary(@PathVariable(name = "id") Long id){
        return salaryService.getSalary(id);
    }

    @PostMapping
    public ResponseEntity<ResponseMsg> addSalary(@RequestBody SalaryRequestDTO salary){
        ResponseMsg responseMsg = new ResponseMsg("Successfully Added Salary", "Success");

        Salary existingSalary = salaryService.addSalary(salary);
        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(existingSalary.getSalId()).toUri()).body(responseMsg);
    }

    @PutMapping("/{id}")
    public ResponseMsg updateSalary(@PathVariable(name = "id") Long id, @RequestBody SalaryRequestDTO salaryRequestDTO ){
        return salaryService.updateSalary(id, salaryRequestDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseMsg deleteSalary(@PathVariable(name = "id") Long id ){
        return salaryService.deleteSalary(id);
    }
}
