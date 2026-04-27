package com.itesm.application.usecase;

import com.itesm.application.dto.CreateUserDto;
import com.itesm.domain.models.User;
import com.itesm.domain.repository.UserRepository;
import com.itesm.domain.repository.UserTokenCreationService;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;

import java.util.Optional;
import java.util.UUID;

/** CreateUserUseCase */
@ApplicationScoped
public class CreateUserUseCase {
    private final UserRepository userRepository;
    private final UserTokenCreationService userAuthService;

    @Inject
    public CreateUserUseCase(
            UserRepository userRepository, UserTokenCreationService userAuthService) {
        this.userRepository = userRepository;
        this.userAuthService = userAuthService;
    }

    public User execute(CreateUserDto createUserDto) {
        Optional<User> dbUser = userRepository.findByEmail(createUserDto.getEmail());
        if (dbUser.isPresent()) {
            throw new WebApplicationException("User already exists", Response.Status.CONFLICT);
        }
        User user = new User();
        user.setId(UUID.randomUUID());
        user.setEmail(createUserDto.getEmail());
        user.setFullName(createUserDto.getFullName());
        user.setActive(true);
        user.setRole(createUserDto.getRole());
        String providerUid =
                userAuthService.createUser(createUserDto.getEmail(), createUserDto.getPassword());
        user.setProviderUid(providerUid);
        return userRepository.save(user);
    }
}
