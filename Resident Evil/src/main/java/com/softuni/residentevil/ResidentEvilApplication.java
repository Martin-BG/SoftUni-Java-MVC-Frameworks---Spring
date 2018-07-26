package com.softuni.residentevil;

import com.softuni.residentevil.domain.models.binding.UserRegisterBindingModel;
import com.softuni.residentevil.services.UserService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ResidentEvilApplication {

    public static void main(String[] args) {
        SpringApplication.run(ResidentEvilApplication.class, args);

    }

/*    @Bean
    InitializingBean initAuthorities(RoleService roleService) {
        return roleService::initRoles;
    }*/

/*    @Bean
    InitializingBean initUsers(final UserService userService) {
        return () -> {
            userService.create(new UserRegisterBindingModel("root", "root", "root"));
            userService.create(new UserRegisterBindingModel("user", "user", "user"));
            userService.create(new UserRegisterBindingModel("admin", "admin", "admin"));
        };
    }*/
}
