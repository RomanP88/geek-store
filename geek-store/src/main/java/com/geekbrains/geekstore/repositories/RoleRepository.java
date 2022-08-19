package com.geekbrains.geekstore.repositories;


import com.geekbrains.geekstore.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(String name);

}
