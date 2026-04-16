package org.master_panel.master_panel.repository;

import java.util.Optional;

import org.master_panel.master_panel.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByUsername(String username);

}
