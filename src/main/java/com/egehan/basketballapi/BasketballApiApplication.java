package com.egehan.basketballapi;

import com.egehan.basketballapi.entity.EnumRole;
import com.egehan.basketballapi.entity.Role;
import com.egehan.basketballapi.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class BasketballApiApplication {

    @Autowired
    private RoleRepository roleRepository;

    public static void main(String[] args) {
        SpringApplication.run(BasketballApiApplication.class, args);
    }

    @PostConstruct
    public void initAuths() {
        roleRepository.save(new Role(1, EnumRole.ROLE_PLAYER));
        roleRepository.save(new Role(2, EnumRole.ROLE_COACH));
    }
}
