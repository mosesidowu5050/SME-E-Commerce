package org.mosesidowu.smeecommerce.config;

import lombok.RequiredArgsConstructor;
import org.mosesidowu.smeecommerce.data.models.Role;
import org.mosesidowu.smeecommerce.data.models.User;
import org.mosesidowu.smeecommerce.data.repository.UserRepository;
import org.mosesidowu.smeecommerce.dtos.requests.UserLoginRequestDTO;
import org.mosesidowu.smeecommerce.dtos.responses.JwtResponse;
import org.mosesidowu.smeecommerce.exception.UserException;
import org.mosesidowu.smeecommerce.security.JwtUtil;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class AdminConfig implements CommandLineRunner {

        private final UserRepository userRepository;
        private final PasswordEncoder passwordEncoder;
        private final JwtUtil jwtUtil;

        @Override
        public void run(String... args) {
            if (!userRepository.existsByEmail("mosesidowu@sme.com")) {

                User admin = new User();
                admin.setEmail("mosesidowu@sme.com");
                admin.setPassword(passwordEncoder.encode("admin1"));
                admin.setRole(Role.ADMIN);
                admin.setFullName("Super Admin");
                admin.setEnabled(true);

                userRepository.save(admin);
            }
            if (!userRepository.existsByEmail("peejay@sme.com")) {

                User subAdmin = new User();
                subAdmin.setEmail("peejay@sme.com");
                subAdmin.setPassword(passwordEncoder.encode("admin2"));
                subAdmin.setRole(Role.ADMIN);
                subAdmin.setFullName("Sub Admin");
                subAdmin.setEnabled(true);

                userRepository.save(subAdmin);
            }
            if (!userRepository.existsByEmail("ericalli@sme.com")) {

                User subAdmin = new User();
                subAdmin.setEmail("ericalli@sme.com");
                subAdmin.setPassword(passwordEncoder.encode("admin3"));
                subAdmin.setRole(Role.ADMIN);
                subAdmin.setFullName("Sub Admin 2");
                subAdmin.setEnabled(true);

                userRepository.save(subAdmin);
            }
        }


}
