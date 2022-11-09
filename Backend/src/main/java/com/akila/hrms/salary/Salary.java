package com.akila.hrms.salary;

import com.akila.hrms.employee.Employee;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "Salary")
@Getter
@Setter
@RequiredArgsConstructor
@Table(name = "salary")
public class Salary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sal_id")
    private Long salId;

    @Column(name = "sal_month")
    private String salMonth;

    @Column(name = "total_days")
    private Integer totalDays;

    @Column(name = "basic_salary", nullable = false)
    private Double basicSalary;

    @Column(name = "bonus")
    private Double bonus;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE, optional = false)
    @JoinColumn(name = "emp_id", foreignKey = @ForeignKey(name = "salary_fk1_emp_id"))
    private Employee employee;
}
