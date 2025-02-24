package com.simco.simplecommerce;

import com.simco.simplecommerce.dto.UserDTO;
import com.simco.simplecommerce.entity.User;
import com.simco.simplecommerce.mapper.UserMapper;
import com.simco.simplecommerce.repository.UserRepository;
import com.simco.simplecommerce.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserService userService;

    @Mock
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    public void setUp() {
            MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveUser() {
        UserDTO userDTO = new UserDTO("username", "password", "ROLE_USER");
        User user = new User();
        user.setUsername("username");
        user.setPassword("password");
        user.setRole("ROLE_USER");

        when(userMapper.toEntity(userDTO)).thenReturn(user);
        when(userRepository.save(user)).thenReturn(user);

        userService.saveUser(userDTO);
        verify(userRepository, times(1)).save(any());
    }

    @Test
    public void testFindUserByUsername() {
        String username = "username";
        User user = new User();
        user.setUsername("username");
        user.setPassword("password");
        user.setRole("USER");

        when(userRepository.findByUsername(username)).thenReturn(Optional.of(user));
        UserDTO userDTO = new UserDTO(username, "password", "USER");

        UserDetails result = userService.loadUserByUsername(username);
        assertEquals(userDTO.username(), result.getUsername());
    }
}