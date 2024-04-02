package com.example.mschedule.service.impl;

import com.example.mschedule.entity.User;
import com.example.mschedule.repository.UserRepository;
import com.example.mschedule.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
//    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Override
    public List<User> getAllUser() {
        return userRepository.findAllWithMemberAccess();
    }

    @Override
    public User getUserById(Integer id) {
        Optional<User> user = userRepository.findById(id);

        return user.orElse(null);
    }

}
