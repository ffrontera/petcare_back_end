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

import com.equipo11.petcare.security.service.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import static com.equipo11.petcare.domain.user.entity.enums.RoleName.ROLE_OWNER;

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

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private final AddressNormalizationService normalizationService;

    public AuthResponse registerUser(RegisterRequest request) {

        if (userRepo.findByEmail(request.email()).isPresent()) {

            throw new IllegalArgumentException("Email already in use");
        }

        Role role = new Role();
        if (request.role().isEmpty() || request.role().equals("ROLE_OWNER")) {
            role = roleRepo.findByName(RoleName.ROLE_OWNER).get();
        } else if (request.role().equals("ROLLE_SITTER")) {
            role = roleRepo.findByName(RoleName.ROLE_SITTER).get();
        } else {
            throw new IllegalArgumentException("Rol de usuario no encontrado");
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
                .typeDocument(securityConfig.passwordEncoder().encode(request.typeDocument()))
                .role(role)
                .state(role.getName().equals(ROLE_OWNER))
                .build();

        userRepo.save(user);

        String role_name = role.getName().toString();
        return new AuthResponse(jwtUtils.generateToken(user.getUsername(), role_name));
    }

    public AuthResponse authUser(AuthRequest request) {
        Authentication auth = authManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.email(),
                request.password()));

        Role role = userRepo.findByEmail(request.email()).get().getRole();

        String role_name = role.getName().toString();

        return new AuthResponse(jwtUtils.generateToken(auth.getName(), role_name));
    }
}