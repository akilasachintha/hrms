package com.akila.hrms.salary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface SalaryRepository extends JpaRepository<Salary, Long> {

    @Query("SELECT sal FROM Salary sal WHERE sal.salId=:id")
    Salary findBySalaryId(Long id);

    @Modifying
    @Transactional
    @Query("DELETE FROM Salary sal WHERE sal.salId=:id")
    void deleteBySalaryId(Long id);
}
