package com.medilabo.authorization.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.medilabo.authorization.model.User;

public interface UserRepository extends JpaRepository<User, String> {
}
