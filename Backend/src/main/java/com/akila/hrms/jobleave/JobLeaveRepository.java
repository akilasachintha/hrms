package com.akila.hrms.jobleave;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface JobLeaveRepository extends JpaRepository<JobLeave, Long> {

    @Query("SELECT jleave FROM JobLeave jleave WHERE jleave.leaveId=:id")
    JobLeave findJobLeaveById(Long id);
}
