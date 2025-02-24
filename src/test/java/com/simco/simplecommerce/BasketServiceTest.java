package com.simco.simplecommerce;

import com.simco.simplecommerce.dto.BasketDTO;
import com.simco.simplecommerce.dto.NewItemDTO;
import com.simco.simplecommerce.entity.Basket;
import com.simco.simplecommerce.entity.Product;
import com.simco.simplecommerce.mapper.BasketMapper;
import com.simco.simplecommerce.repository.BasketRepository;
import com.simco.simplecommerce.service.BasketService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.List;
import java.util.Optional;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.*;

public class BasketServiceTest {

    @Mock
    private BasketRepository basketRepository;

    @Mock
    private BasketMapper basketMapper;

    @InjectMocks
    private BasketService basketService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        basketService = new BasketService(basketRepository);

        // Create a mock Principal object
        UserDetails principal = mock(UserDetails.class);
        when(principal.toString()).thenReturn("admin");
        // Create a mock Authentication object
        Authentication authentication = mock(Authentication.class);
        // Set up the Authentication to return the mock Principal
        when(authentication.getPrincipal()).thenReturn(principal);
        // Create a mock SecurityContext object
        SecurityContext securityContext = mock(SecurityContext.class);
        // Set up the SecurityContext to return the mock Authentication
        when(securityContext.getAuthentication()).thenReturn(authentication);
        // Set the SecurityContext in the SecurityContextHolder
        SecurityContextHolder.setContext(securityContext);
    }

    @Test
    public void testGetBasket() {
        Basket basket = new Basket();
        basket.setId(1);
        basket.setUserId("admin");
        BasketDTO basketDTO = new BasketDTO(1, "admin", null);

        when(basketRepository.findByUserId("admin")).thenReturn(List.of(basket));
        when(basketMapper.toDTO(basket)).thenReturn(basketDTO);

        Optional<List<BasketDTO>> result = basketService.getBasket();
        assertEquals(1, result.get().size());
    }

    @Test
    public void testDeleteBasket() {
        doNothing().when(basketRepository).deleteByIdAndUserId(1, null);

        basketService.deleteBasket(1);
        verify(basketRepository, times(1)).deleteByIdAndUserId(1, null);
    }

    @Test
    public void testAddItemToBasket() {
        NewItemDTO newItemDTO = new NewItemDTO("1");
        Basket basket = new Basket();
        Product product = new Product();
        product.setId("1");
        basket.setProduct(product);
        basket.setUserId("admin");

        when(basketRepository.save(any(Basket.class))).thenReturn(basket);

        basketService.addItemToBasket(newItemDTO);
        verify(basketRepository, times(1)).save(any(Basket.class));
    }
}