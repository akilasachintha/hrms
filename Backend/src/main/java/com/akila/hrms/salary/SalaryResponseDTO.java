package com.akila.hrms.salary;

import lombok.Data;

@Data
public class SalaryResponseDTO {

    private Long salId;

    private String salMonth;

    private Integer totalDays;

    private Double basicSalary;

    private Double bonus;

    private Long empId;

    private String name;
}
