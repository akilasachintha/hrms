package com.akila.hrms.employee;

import com.akila.hrms.error.ResponseMsg;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EmployeeServiceImplementation implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    private EmployeeDTO convertEntityToDTO(Employee employee) {
        EmployeeDTO employeeDTO = new EmployeeDTO();

        employeeDTO.setEmpId(employee.getEmpId());
        employeeDTO.setName(employee.getName());
        employeeDTO.setEmail(employee.getEmail());
        employeeDTO.setDob(employee.getDob());
        employeeDTO.setAddress(employee.getAddress());
        employeeDTO.setCountry(employee.getCountry());
        employeeDTO.setGender(employee.getGender());
        employeeDTO.setContactNo(employee.getContactNo());
        employeeDTO.setTitle(employee.getTitle());
        employeeDTO.setNationality(employee.getNationality());

        return employeeDTO;
    }

    private Employee convertDTOToEntity(EmployeeDTO employeeDTO) {
        Employee employee = new Employee();

        employee.setName(employeeDTO.getName());
        employee.setEmail(employeeDTO.getEmail());
        employee.setTitle(employeeDTO.getTitle());
        employee.setDob(employeeDTO.getDob());
        employee.setContactNo(employeeDTO.getContactNo());
        employee.setGender(employeeDTO.getGender());
        employee.setAddress(employeeDTO.getAddress());
        employee.setCountry(employeeDTO.getCountry());
        employee.setNationality(employeeDTO.getNationality());

        return employee;
    }

    @Override
    public List<EmployeeDTO> getEmployees() {
        return employeeRepository.findAll().stream().map(this::convertEntityToDTO).collect(Collectors.toList());
    }

    @Override
    public Employee addEmployee(EmployeeDTO employee) {
        return employeeRepository.save(convertDTOToEntity(employee));
    }

    @Override
    public ResponseMsg deleteEmployee(Long id) {

        Employee existingEmployee = employeeRepository.findByEmployeeId(id);

        if (existingEmployee == null) {
            return new ResponseMsg("Employee Not Found", "Error");
        } else {
            employeeRepository.deleteById(id);
            return new ResponseMsg("Employee Deleted Successfully", "Success");
        }
    }

    @Override
    public ResponseMsg updateEmployee(Long id, EmployeeDTO employee) {

        Employee existingEmployee = employeeRepository.findByEmployeeId(id);

        if (existingEmployee != null) {
            existingEmployee.setTitle(employee.getTitle());
            existingEmployee.setName(employee.getName());
            existingEmployee.setGender(employee.getGender());
            existingEmployee.setAddress(employee.getAddress());
            existingEmployee.setCountry(employee.getCountry());
            existingEmployee.setEmail(employee.getEmail());
            existingEmployee.setContactNo(employee.getContactNo());
            existingEmployee.setNationality(employee.getNationality());
            existingEmployee.setDob(employee.getDob());

            employeeRepository.save(existingEmployee);

            return new ResponseMsg("Employee Successfully Updated", "Success");
        } else {
            return new ResponseMsg("Error in Update", "Error");
        }
    }

    @Override
    public EmployeeDTO getEmployee(Long id) {
        Employee employee = employeeRepository.findByEmployeeId(id);
        return convertEntityToDTO(employee);
    }
}
