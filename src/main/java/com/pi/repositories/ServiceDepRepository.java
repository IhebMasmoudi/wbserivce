package com.pi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.pi.entities.ServiceDep;
public interface ServiceDepRepository extends JpaRepository<ServiceDep, Integer> {


    ServiceDep findById(Long id);
}
