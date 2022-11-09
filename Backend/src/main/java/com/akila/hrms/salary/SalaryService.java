package com.akila.hrms.salary;

import com.akila.hrms.error.ResponseMsg;

import java.util.List;

public interface SalaryService {
    List<SalaryResponseDTO> getSalaries();

    SalaryResponseDTO getSalary(Long id);

    Salary addSalary(SalaryRequestDTO salary);

    ResponseMsg deleteSalary(Long id);

    ResponseMsg updateSalary(Long id, SalaryRequestDTO salaryRequestDTO);
}
