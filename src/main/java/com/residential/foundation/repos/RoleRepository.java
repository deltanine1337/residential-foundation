package com.residential.foundation.repos;

import java.util.Optional;

import com.residential.foundation.model.enums.ERole;
import com.residential.foundation.model.jpa.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
	Optional<Role> findByName(ERole name);
}
