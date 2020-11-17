package com.knoflik.repositories.role;

import com.knoflik.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
   Role findRoleByName(String name);
}
