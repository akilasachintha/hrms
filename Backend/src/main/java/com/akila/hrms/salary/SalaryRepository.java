package com.akila.hrms.salary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SalaryRepository extends JpaRepository<Salary, Long> {

    @Query("SELECT sal FROM Salary  sal WHERE sal.salId=:id")
    Salary findBySalaryId(Long id);
}
