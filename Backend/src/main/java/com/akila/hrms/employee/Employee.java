package com.akila.hrms.employee;

import com.akila.hrms.department.Department;
import com.akila.hrms.jobleave.JobLeave;
import com.akila.hrms.role.Role;
import com.akila.hrms.salary.Salary;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "Employee")
@Getter
@Setter
@RequiredArgsConstructor
@Table(name = "employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "emp_id")
    private Long empId;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "dob")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dob;

    @Column(name = "gender", nullable = false)
    private String gender;

    @Column(name = "address")
    private String address;

    @Column(name = "country")
    private String country;

    @Column(name = "nationality")
    private String nationality;

    @Column(name = "contact_no", nullable = false)
    private String contactNo;

    @ManyToOne
    @JoinColumn(name = "dept_id", foreignKey = @ForeignKey(name = "employee_fk1_dept_id"))
    private Department department;

    @ManyToOne
    @JoinColumn(name = "role_id", foreignKey = @ForeignKey(name = "employee_fk3_role_id"))
    private Role role;

    @OneToMany(mappedBy = "employee")
    private Set<Salary> salaries = new HashSet<>();

    @OneToMany(mappedBy = "employee")
    private Set<JobLeave> jobLeaves = new HashSet<>();
}
