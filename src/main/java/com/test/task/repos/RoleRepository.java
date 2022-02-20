package com.test.task.repos;

import java.util.Optional;

import com.test.task.model.enums.ERole;
import com.test.task.model.jpa.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
	Optional<Role> findByName(ERole name);
}
