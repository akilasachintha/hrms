package com.akila.hrms.employee;

import com.akila.hrms.error.ResponseMsg;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/api/v1/employee")
@AllArgsConstructor
@CrossOrigin
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping
    public List<EmployeeDTO> getEmployees(){
       return employeeService.getEmployees();
    }

    @GetMapping("/{id}")
    public EmployeeDTO getEmployee(@PathVariable(name = "id") Long id){
        return employeeService.getEmployee(id);
    }

    @PostMapping
    public ResponseEntity<ResponseMsg> addEmployee(@RequestBody EmployeeDTO employee){
        ResponseMsg responseMsg = new ResponseMsg("Successfully Added Employee", "Success");

        Employee existingEmployee = employeeService.addEmployee(employee);
        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(existingEmployee.getEmpId()).toUri()).body(responseMsg);
    }

    @DeleteMapping("/{id}")
    public ResponseMsg deleteEmployee(@PathVariable(name = "id") Long id ){
        return employeeService.deleteEmployee(id);
    }

    @PutMapping("/{id}")
    public ResponseMsg updateEmployee(@PathVariable(name = "id") Long id, @RequestBody EmployeeDTO employee ){
        return employeeService.updateEmployee(id, employee);
    }
}
