package com.example.firstSpringboot.users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<Users, UUID> {
    @Query("SELECT s FROM Users s WHERE s.email = ?1")
    Optional<Users> findUserByEmail(String email);
}
