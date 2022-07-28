package com.matthew.employeemanagementsystem.service.user;

import com.matthew.employeemanagementsystem.domain.entities.UserEntity;
import com.matthew.employeemanagementsystem.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserFindingServiceImpl implements UserFindingService {

    private final UserRepository userRepository;

    @Override
    public UserEntity getUserEntity(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> {
            throw new UsernameNotFoundException(username + "User not found");
        });
    }
}
