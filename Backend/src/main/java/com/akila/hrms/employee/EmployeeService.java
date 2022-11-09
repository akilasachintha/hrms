package com.akila.hrms.employee;

import com.akila.hrms.error.ResponseMsg;

import java.util.List;

public interface EmployeeService {
    List<EmployeeDTO> getEmployees();

    Employee addEmployee(EmployeeDTO employee);

    ResponseMsg deleteEmployee(Long id);

    ResponseMsg updateEmployee(Long id, EmployeeDTO employee);

    EmployeeDTO getEmployee(Long id);
}
