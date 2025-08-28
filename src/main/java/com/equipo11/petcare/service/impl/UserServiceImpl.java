package com.equipo11.petcare.service.impl;

import com.equipo11.petcare.domain.user.entity.Sitter;
import com.equipo11.petcare.domain.user.entity.User;
import com.equipo11.petcare.domain.user.repository.UserRepository;
import com.equipo11.petcare.exception.BusinessException;
import com.equipo11.petcare.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
//@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<Sitter> findSitterById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Sitter ID cannot be null");
        }
        
        return userRepository.findById(id)
                .map(user -> {
                    if (user instanceof Sitter) {
                        return (Sitter) user;
                    }
                    return null;
                });
    }

    @Override
    public Sitter getSitterByIdOrThrow(Long id) {
        return findSitterById(id)
                .orElseThrow(() -> new BusinessException("Sitter not found with id: " + id));
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new BusinessException("User not found with id: " + id));
    }
}
