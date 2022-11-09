package com.akila.hrms.employee;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;

@Data
public class EmployeeDTO {

    private Long empId;

    private String title;

    private String name;

    private String email;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dob;

    private String gender;

    private String address;

    private String country;

    private String nationality;

    private String contactNo;
}
