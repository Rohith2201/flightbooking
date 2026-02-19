package com.examly.springapp.service.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.examly.springapp.config.JwtService;
import com.examly.springapp.exception.UsernameAlreadyExistsException;
import com.examly.springapp.model.User;
import com.examly.springapp.repository.UserRepo;
import com.examly.springapp.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    UserRepo userRepo;
    PasswordEncoder passwordEncoder;
    AuthenticationManager authenticationManager;
    JwtService jwtUtils;
    @Autowired
    public UserServiceImpl(UserRepo userRepo, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JwtService jwtUtils  ){
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
    }

    @Override
    public User createUser(User user) {
        User existUser = userRepo.findByUsername(user.getUsername());
        if(existUser != null){
          throw new UsernameAlreadyExistsException("Username or Email already exists");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user = userRepo.save(user);
        return user;
    }

    @Override
    public User loginUser(User user) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        if (authentication.isAuthenticated()) {
            String token = jwtUtils.generateToken(user.getUsername());
            User mainUser = userRepo.findByUsername(user.getUsername());
            mainUser.setToken(token);
            return mainUser;
        }
        return null;

    }

    @Override
    public User getUserById(Long userId){
        return userRepo.findById(userId).orElse(null);
    }

    public List<User> getAllUsers(){
        return userRepo.findAll();
    }
}
