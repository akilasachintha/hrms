package com.akila.hrms.salary;

import lombok.Data;


@Data
public class SalaryRequestDTO {

    private String salMonth;

    private Integer totalDays;

    private Double basicSalary;

    private Double bonus;

    private Long empId;
}
