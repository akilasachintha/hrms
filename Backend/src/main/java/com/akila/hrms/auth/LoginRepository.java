package com.akila.hrms.auth;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginRepository extends JpaRepository<UserLogin, String> {

    @Query("SELECT userLogin FROM UserLogin userLogin WHERE userLogin.email=:email")
    UserLogin findByEmail(String email);
}
