package com.akila.hrms.employee;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query("SELECT emp FROM Employee emp WHERE emp.empId=:id")
    Employee findByEmployeeId(Long id);

}

