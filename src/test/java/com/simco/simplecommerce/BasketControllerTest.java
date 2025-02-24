package com.simco.simplecommerce;

import com.simco.simplecommerce.controller.BasketController;
import com.simco.simplecommerce.dto.BasketDTO;
import com.simco.simplecommerce.dto.NewItemDTO;
import com.simco.simplecommerce.service.BasketService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

public class BasketControllerTest {

    @Mock
    private BasketService basketService;

    @InjectMocks
    private BasketController basketController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddItemToBasket() {
        NewItemDTO basketDTO = new NewItemDTO("dfsd");
        doNothing().when(basketService).addItemToBasket(basketDTO);

        ResponseEntity<?> response = basketController.addItemToBasket(basketDTO);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    public void testGetBasket() {
        BasketDTO basketDTO = new BasketDTO(1,"dfsd",null);
        when(basketService.getBasket()).thenReturn(Optional.of(List.of(basketDTO)));

        ResponseEntity<List<BasketDTO>> response = basketController.getBasket();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(basketDTO, response.getBody().get(0));
    }

    @Test
    public void testRemoveItemFromBasket() {
        int itemId = 1;
        ResponseEntity<Void> response = basketController.deleteItem(itemId);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }
}
