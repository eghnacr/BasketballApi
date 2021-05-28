package com.egehan.basketballapi.repository;

import com.egehan.basketballapi.entity.EnumRole;
import com.egehan.basketballapi.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByName(EnumRole name);
}
