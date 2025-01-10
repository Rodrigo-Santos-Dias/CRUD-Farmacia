package com.Pharma.PharmaDelivery.security;

import com.Pharma.PharmaDelivery.model.Role;
import com.Pharma.PharmaDelivery.model.User;
import com.Pharma.PharmaDelivery.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;


    private final PasswordEncoder passwordEncoder;

    public DataInitializer(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
      ;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public void run(String... args) throws Exception {


        if (userRepository.findByEmail("admin@admin").isEmpty()){
            User admin = new User("admin","admin@admin", passwordEncoder.encode("password"),true , Role.ADMIN);

            userRepository.save(admin);
        }

        if (userRepository.findByEmail("user@user").isEmpty()){
            User user = new User("user","user@user" ,passwordEncoder.encode("password"),true,Role.USER );

            userRepository.save(user);
        }
    }
}
