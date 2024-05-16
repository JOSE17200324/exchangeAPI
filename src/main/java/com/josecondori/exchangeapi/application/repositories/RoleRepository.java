package com.josecondori.exchangeapi.application.repositories;

import com.josecondori.exchangeapi.infrastructure.db.springdata.dbo.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}