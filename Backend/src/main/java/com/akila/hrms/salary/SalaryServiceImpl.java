package com.akila.hrms.salary;

import com.akila.hrms.employee.Employee;
import com.akila.hrms.employee.EmployeeRepository;
import com.akila.hrms.error.ResponseMsg;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SalaryServiceImpl implements SalaryService {

    private final SalaryRepository salaryRepository;
    private final EmployeeRepository employeeRepository;

    private Salary convertDTOToEntity(SalaryRequestDTO salaryRequestDTO) {
        Salary salary = new Salary();

        Employee existingEmployee = employeeRepository.findByEmployeeId(salaryRequestDTO.getEmpId());

        salary.setSalMonth(salaryRequestDTO.getSalMonth());
        salary.setBasicSalary(salaryRequestDTO.getBasicSalary());
        salary.setBonus(salaryRequestDTO.getBonus());
        salary.setTotalDays(salaryRequestDTO.getTotalDays());
        salary.setEmployee(existingEmployee);

        return salary;
    }

    private SalaryResponseDTO convertEntityToDTO(Salary salary) {
        SalaryResponseDTO salaryResponseDTO = new SalaryResponseDTO();

        salaryResponseDTO.setBasicSalary(salary.getBasicSalary());
        salaryResponseDTO.setTotalDays(salary.getTotalDays());
        salaryResponseDTO.setSalMonth(salary.getSalMonth());
        salaryResponseDTO.setSalId(salary.getSalId());
        salaryResponseDTO.setBonus(salary.getBonus());
        salaryResponseDTO.setEmpId(salary.getEmployee().getEmpId());
        salaryResponseDTO.setName(salary.getEmployee().getName());

        return salaryResponseDTO;
    }

    @Override
    public List<SalaryResponseDTO> getSalaries() {
        return salaryRepository.findAll().stream().map(this::convertEntityToDTO).collect(Collectors.toList());
    }

    @Override
    public SalaryResponseDTO getSalary(Long id) {
        return convertEntityToDTO(salaryRepository.findBySalaryId(id));
    }

    @Override
    public Salary addSalary(SalaryRequestDTO salary) {
        return salaryRepository.save(convertDTOToEntity(salary));
    }

    @Override
    public ResponseMsg deleteSalary(Long id) {
        Salary existingSalary = salaryRepository.findBySalaryId(id);

        if (existingSalary == null) {
            return new ResponseMsg("Salary Not Found", "Error");
        } else {
            salaryRepository.deleteById(id);
            return new ResponseMsg("Salary Deleted Successfully", "Success");
        }
    }

    @Override
    public ResponseMsg updateSalary(Long id, SalaryRequestDTO salaryRequestDTO) {

        Salary existingSalary = salaryRepository.findBySalaryId(id);

        if(existingSalary != null){
            existingSalary.setTotalDays(salaryRequestDTO.getTotalDays());
            existingSalary.setSalMonth(salaryRequestDTO.getSalMonth());
            existingSalary.setBonus(salaryRequestDTO.getBonus());
            existingSalary.setBasicSalary(salaryRequestDTO.getBasicSalary());

            salaryRepository.save(existingSalary);

            return new ResponseMsg("Salary Updated Successfully", "Success");
        }
        else {
            return new ResponseMsg("Error in Update", "Error");
        }
    }
}
