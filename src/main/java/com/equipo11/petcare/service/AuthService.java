package com.equipo11.petcare.service;
import com.equipo11.petcare.dto.AuthRequest;
import com.equipo11.petcare.dto.AuthResponse;
import com.equipo11.petcare.security.SecurityConfig;
import com.equipo11.petcare.domain.address.service.AddressNormalizationService;
import com.equipo11.petcare.domain.user.entity.Role;
import com.equipo11.petcare.domain.user.entity.enums.RoleName;
import com.equipo11.petcare.domain.user.repository.RoleRepository;
import com.equipo11.petcare.dto.ParsedAddress;
import com.equipo11.petcare.dto.RegisterRequest;
import com.equipo11.petcare.domain.user.entity.User;
import com.equipo11.petcare.domain.user.repository.UserRepository;

import com.equipo11.petcare.security.jwt.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthService {

    @Autowired
    private final UserRepository userRepo;

    @Autowired
    private final RoleRepository roleRepo;

    @Autowired
    private final SecurityConfig securityConfig;

    @Autowired
    private AuthenticationManager authManager;

    private JwtUtils jwtUtils;


    @Autowired
    private final AddressNormalizationService normalizationService;

    public AuthResponse register(RegisterRequest request) {

        if (userRepo.findByEmail(request.email()).isPresent()) {
            throw new IllegalArgumentException("Email already in use");
        }

        Set<String> strRoles = request.roles();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role defaultRole = roleRepo.findByName(RoleName.ROLE_OWNER)
                    .orElseThrow(() -> new RuntimeException("Role no encontrado"));
            roles.add(defaultRole);
        } else {
            strRoles.forEach(roleName -> {
                RoleName rn = RoleName.valueOf("ROLE_SITTER");
                Role role = roleRepo.findByName(rn)
                        .orElseThrow(() -> new RuntimeException("Role no encontrado: " + rn));
                roles.add(role);
            });
        }


        User user = User.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .birthdate(request.birthdate())
                .email(request.email())
                .password(securityConfig.passwordEncoder().encode(request.password()))
                .address(normalizationService.normalize(
                        new ParsedAddress(
                                request.address().streetName(),
                                request.address().streetNumber(),
                                request.address().unit(),
                                request.address().postalCode(),
                                request.address().city(),
                                request.address().region(),
                                request.address().countryCode())))
                .numberPhone(request.numberPhone())
                .typeDocument(request.typeDocument())
                .roles(roles)
                .build();

        userRepo.save(user);


        return new AuthResponse(jwtUtils.generateToken(user.getUsername()));
    }

    public AuthResponse authUser(AuthRequest request) {
        Authentication auth = authManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.email(),
                request.password()));
        return new AuthResponse(jwtUtils.generateToken(auth.getName()));
    }
}