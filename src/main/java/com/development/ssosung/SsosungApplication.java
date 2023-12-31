package com.development.ssosung;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class SsosungApplication {

    public static void main(String[] args) {
        SpringApplication.run(SsosungApplication.class, args);
        System.out.println("Developer SsoSung");
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

//    @Bean
//    CommandLineRunner run(UserService userService){
//        return args -> {
//            userService.saveRole(new Role(null, "ROLE_USER"));
//            userService.saveRole(new Role(null, "ROLE_MANAGER"));
//            userService.saveRole(new Role(null, "ROLE_ADMIN"));
//            userService.saveRole(new Role(null, "ROLE_SUPER_ADMIN"));
//
//            userService.saveUser(new User("rlfhfh1209", "dkvkxm806!@", "KHS" , "010-1234-5678", "ROLE_USER"));
//            userService.saveUser(new User(null, "Will Smith", "will" , "1234", new ArrayList<>()));
//            userService.saveUser(new User(null, "Jim Carry", "jim" , "1234", new ArrayList<>()));
//            userService.saveUser(new User(null, "Arnold Schwarzenegger", "arnold" , "1234", new ArrayList<>()));
//
//            userService.addRoleToUser("jone", "ROLE_USER");
//            userService.addRoleToUser("jone", "ROLE_MANAGER");
//            userService.addRoleToUser("will", "ROLE_MANAGER");
//            userService.addRoleToUser("jim", "ROLE_ADMIN");
//            userService.addRoleToUser("arnold", "ROLE_SUPER_ADMIN");
//            userService.addRoleToUser("arnold", "ROLE_ADMIN");
//            userService.addRoleToUser("arnold", "ROLE_USER");
//        };
//    }

}
