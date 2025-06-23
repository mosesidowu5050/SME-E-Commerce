package org.mosesidowu.empirezone.security;

import lombok.RequiredArgsConstructor;
import org.mosesidowu.empirezone.data.models.User;
import org.mosesidowu.empirezone.data.repository.UserRepository;
import org.mosesidowu.empirezone.exception.PhoneNumberExistException;
import org.mosesidowu.empirezone.exception.UserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository; // your MongoDB repo

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findUsersByEmail(email).orElseThrow(() -> new UserException("User not found"));
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                new ArrayList<>()
        );
    }
}
