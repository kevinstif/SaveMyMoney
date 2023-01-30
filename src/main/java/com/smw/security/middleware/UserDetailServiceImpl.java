package com.smw.security.middleware;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.smw.security.domain.model.entity.User;
import com.smw.security.domain.persistence.UserRepository;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findOneByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User with username" + username + " not found"));

        return new UserDetailsImpl(user);
    }

}
