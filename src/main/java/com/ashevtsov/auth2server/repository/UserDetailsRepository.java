package com.ashevtsov.auth2server.repository;


import com.ashevtsov.auth2server.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserDetailsRepository extends JpaRepository<AppUser, Integer> {
    Optional<AppUser> findByUsername(String name);
}
