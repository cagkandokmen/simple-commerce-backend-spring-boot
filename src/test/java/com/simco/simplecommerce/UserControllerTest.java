package com.simco.simplecommerce;

import com.simco.simplecommerce.controller.UserController;
import com.simco.simplecommerce.dto.TokenDTO;
import com.simco.simplecommerce.dto.UserDTO;
import com.simco.simplecommerce.service.AuthService;
import com.simco.simplecommerce.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class UserControllerTest {

    @Mock
    private UserService userService;

    @Mock
    private AuthService authService;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testRegisterUser() {
        UserDTO user = new UserDTO("abc","abc","ADMIN");
        ResponseEntity<Void> response = userController.registerUser(user);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    public void testLogin() {
        UserDTO user = new UserDTO("abc","abc","ADMIN");
        TokenDTO token = new TokenDTO("asdas");
        when(authService.authenticate(user)).thenReturn(token);

        ResponseEntity<TokenDTO> response = userController.login(user);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(token, response.getBody());
    }
}