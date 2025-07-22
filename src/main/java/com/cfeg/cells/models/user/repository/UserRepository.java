package com.cfeg.cells.models.user.repository;

import com.cfeg.cells.models.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository <User , Long> {

    UserDetails findByEmail(String email);
    boolean existsByEmail(String email);
}
