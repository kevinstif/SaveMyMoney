package com.smw.security.domain.persistence;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smw.security.domain.model.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
    Optional<User> findOneByEmail(String email);
}
