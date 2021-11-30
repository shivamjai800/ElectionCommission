package com.electioncomission.ec.config;

import com.electioncomission.ec.entity.Users;
import com.electioncomission.ec.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class CustomUserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UsersRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String mobileNumber) throws UsernameNotFoundException {
        Users user = userRepository.findUsersByMobileNumber(mobileNumber);

        if (user == null) {
            throw new UsernameNotFoundException("User with the given username not found");
        }

        CustomUsersDetails customUserDetails = new CustomUsersDetails(user);
        return customUserDetails;
    }
}
