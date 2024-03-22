package com.pi.repositories;

import com.pi.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository  extends JpaRepository<Role, Integer>{

    Role findById(Long id);

}
