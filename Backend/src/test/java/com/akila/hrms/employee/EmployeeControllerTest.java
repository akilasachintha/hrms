package com.akila.hrms.employee;

import com.akila.hrms.error.ResponseMsg;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Date;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class EmployeeControllerTest {

    @Autowired
    EmployeeController employeeController;

    @Test
    void deleteEmployee() {
        ResponseMsg responseMsg = employeeController.deleteEmployee(2L);
        assertEquals("Success", responseMsg.getStatus());

        ResponseMsg responseMsg2 = employeeController.deleteEmployee(null);
        assertEquals("Error", responseMsg2.getStatus());
    }

    @Test
    void addEmployee() {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setTitle("Mr");
        employeeDTO.setName("Akila Test");
        employeeDTO.setAddress("Welewatta, Matara");
        employeeDTO.setDob(Date.valueOf("1997-02-03"));
        employeeDTO.setEmail("akila@gmail.com");
        employeeDTO.setGender("Male");
        employeeDTO.setNationality("Sri Lankan");
        employeeDTO.setCountry("Sri Lanka");
        employeeDTO.setContactNo("0714383413");

        ResponseMsg employee = employeeController.addEmployee(employeeDTO).getBody();

        if (employee != null) {
            assertEquals("Success", employee.getStatus());
        }
    }

    @Test
    void getEmployees() {
        List<EmployeeDTO> employeeList = employeeController.getEmployees();
        assertThat(employeeList.size()).isNotEqualTo(0);
    }

    @Test
    void updateEmployee() {

        EmployeeDTO updatedEmployee = new EmployeeDTO();

        updatedEmployee.setTitle("Mr");
        updatedEmployee.setName("Updated Name");
        updatedEmployee.setEmail("akilaupdated@gmail.com");
        updatedEmployee.setGender("Male");
        updatedEmployee.setContactNo("0714383413");
        updatedEmployee.setAddress("Welewatta, Matara");

        ResponseMsg responseMsg = employeeController.updateEmployee(2L, updatedEmployee);

        assertEquals("Success", responseMsg.getStatus());
    }
}