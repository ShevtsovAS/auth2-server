package com.ashevtsov.auth2server.service;

import com.ashevtsov.auth2server.model.AppUser;
import com.ashevtsov.auth2server.repository.UserDetailsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AccountStatusUserDetailsChecker;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JpaUserDetailsService implements UserDetailsManager {

    private final UserDetailsRepository userDetailsRepository;
    private final AccountStatusUserDetailsChecker userDetailsChecker;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser user = userDetailsRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Username or password wrong"));
        userDetailsChecker.check(user);
        return user;
    }

    @Override
    public void createUser(UserDetails user) {
        //TODO...
    }

    @Override
    public void updateUser(UserDetails user) {
        //TODO...
    }

    @Override
    public void deleteUser(String username) {
        //TODO...
    }

    @Override
    public void changePassword(String oldPassword, String newPassword) {
        //TODO...
    }

    @Override
    public boolean userExists(String username) {
        //TODO...
        return false;
    }

}
