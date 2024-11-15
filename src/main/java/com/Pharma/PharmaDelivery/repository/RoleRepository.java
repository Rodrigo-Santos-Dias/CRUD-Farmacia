package com.Pharma.PharmaDelivery.repository;

import com.Pharma.PharmaDelivery.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Long> {

    Optional<Role> findByName(String name);
}
