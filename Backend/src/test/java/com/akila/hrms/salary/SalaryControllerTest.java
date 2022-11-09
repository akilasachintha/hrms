package com.akila.hrms.salary;

import com.akila.hrms.error.ResponseMsg;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SalaryControllerTest {

    @Autowired
    SalaryController salaryController;


    @Test
    void deleteSalary() {
        ResponseMsg responseMsg = salaryController.deleteSalary(4L);
        assertEquals("Success", responseMsg.getStatus());

        ResponseMsg responseMsg2 = salaryController.deleteSalary(null);
        assertEquals("Error", responseMsg2.getStatus());
    }
}