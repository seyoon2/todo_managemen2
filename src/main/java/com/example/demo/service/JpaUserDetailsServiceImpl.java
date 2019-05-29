package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.entity.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Component
@Transactional
public class JpaUserDetailsServiceImpl implements UserDetailsService {

    /** アカウントリポジトリ */
    private final UserRepository userRepository;

    @Autowired
    public JpaUserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String accountId) throws UsernameNotFoundException {
        User user = userRepository.findByAccountId(accountId);
        if (Objects.isNull(user)) {
            return new User();
        }
        return user;
    }
}