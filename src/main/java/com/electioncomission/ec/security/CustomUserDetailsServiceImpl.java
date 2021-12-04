package com.electioncomission.ec.security;

import com.electioncomission.ec.entity.Users;
import com.electioncomission.ec.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsServiceImpl  implements UserDetailsService {
    @Autowired
    private UsersRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String mobileNumber) throws UsernameNotFoundException {
//        System.out.println("mobileNumber="+mobileNumber);
        Users user = userRepository.findUsersByMobileNumber(mobileNumber);
        System.out.println("user = "+user);
        if (user == null) {
            throw new UsernameNotFoundException("User with the given username not found");
        }
        CustomUserDetails customUserDetails = new CustomUserDetails(user);
        return customUserDetails;
    }

}
