package org.mosesidowu.smeecommerce.config;

import lombok.RequiredArgsConstructor;
import org.mosesidowu.smeecommerce.data.models.Role;
import org.mosesidowu.smeecommerce.data.models.User;
import org.mosesidowu.smeecommerce.data.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AdminConfig implements CommandLineRunner {

        private final UserRepository userRepository;
        private final PasswordEncoder passwordEncoder;

        @Override
        public void run(String... args) {
            if (!userRepository.existsByEmail("mosesidowu@sme.com")) {

                User admin = new User();
                admin.setEmail("mosesidowu@sme.com");
                admin.setPassword(passwordEncoder.encode("admin1"));
                admin.setRole(Role.ADMIN);
                admin.setFullName("Super Admin");

                userRepository.save(admin);
            }
        }

}
