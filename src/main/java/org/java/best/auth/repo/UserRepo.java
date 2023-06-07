package org.java.best.auth.repo;

import java.util.Optional;

import org.java.best.auth.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Integer> {

	public Optional<User> findByUsername(String username);
}
