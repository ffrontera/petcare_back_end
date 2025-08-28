package com.equipo11.petcare.service;

import com.equipo11.petcare.domain.user.entity.Sitter;
import com.equipo11.petcare.domain.user.entity.User;

import java.util.Optional;


public interface UserService {

    Optional<Sitter> findSitterById(Long id);

    Sitter getSitterByIdOrThrow(Long id);

    User getUserById(Long id);
}
