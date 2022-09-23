package com.example.museummanagement.service.impl;

import com.example.museummanagement.auth.CustomUserDetails;
import com.example.museummanagement.auth.SignUpDto;
import com.example.museummanagement.entity.User;
import com.example.museummanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) {
        // Kiểm tra xem user có tồn tại trong database không?
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return new CustomUserDetails(user);
    }

    @Transactional
    public UserDetails loadUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new UsernameNotFoundException("User not found with id : " + id)
        );

        return new CustomUserDetails(user);
    }

    public SignUpDto registerUser(SignUpDto signUpDto) throws Exception {
            // add check for username exists in a DB
            if(userRepository.existsByUsername(signUpDto.getUsername())){
               throw new Exception("Username is already taken!");
            }

            // create user object
            User user = new User();
            user.setName(signUpDto.getName());
            user.setUsername(signUpDto.getUsername());
            user.setPassword(passwordEncoder.encode(signUpDto.getPassword()));
            userRepository.save(user);
            return signUpDto;
    }

}