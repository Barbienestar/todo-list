package com.itesm.application.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.itesm.application.dto.CreateUserDto;
import com.itesm.domain.models.User;
import com.itesm.domain.repository.UserRepository;
import com.itesm.domain.repository.UserTokenCreationService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

/** CreateUserUseCaseTest */
public class CreateUserUseCaseTest {
    private CreateUserUseCase createUserUseCase;
    private UserRepository userRepository;
    private UserTokenCreationService userTokenService;

    @BeforeEach
    void setup() {
        userRepository = mock(UserRepository.class);
        userTokenService = mock(UserTokenCreationService.class);

        when(userRepository.findByEmail(any(String.class))).thenReturn(Optional.empty());
        when(userTokenService.createUser(any(String.class), any(String.class)))
                .thenReturn("mock_uid");
        when(userRepository.save(any(User.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));
        createUserUseCase = new CreateUserUseCase(userRepository, userTokenService);
    }

    @Test
    void execute_shouldCreateUserWithCompleteDto() {
        CreateUserDto createUserDto = new CreateUserDto("test@test.com", "John Pork", "123456789");
        User result = createUserUseCase.execute(createUserDto);
        assertNotNull(result.getId());
        assertNotNull(result.getProviderUid());
        assertEquals("test@test.com", result.getEmail());
        assertEquals("John Pork", result.getFullName());
    }

    @Test
    void execute_shouldNotCreateUserWithDuplicatedEmail() {
        when(userRepository.findByEmail(any(String.class))).thenReturn(Optional.of(new User()));
        CreateUserDto createUserDto = new CreateUserDto("test@test.com", "John Pork", "123456789");
        assertThrows(Exception.class, () -> createUserUseCase.execute(createUserDto));
    }

    @Test
    void execute_shouldCreateUniqueIds() {
        CreateUserDto createUserDto = new CreateUserDto("test@test.com", "John Pork", "123456789");
        User result1 = createUserUseCase.execute(createUserDto);
        User result2 = createUserUseCase.execute(createUserDto);
        assertNotEquals(result1.getId(), result2.getId());
    }
}
