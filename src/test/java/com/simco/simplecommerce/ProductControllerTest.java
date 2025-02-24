package com.simco.simplecommerce;

import com.simco.simplecommerce.controller.ProductController;
import com.simco.simplecommerce.dto.ProductDTO;
import com.simco.simplecommerce.service.ProductService;
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

public class ProductControllerTest {

    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductController productController;

    @BeforeEach
    public void setup() {
            MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllProducts() {
        ProductDTO productDTO = new ProductDTO("1", "Product 1", "Description 1", "weqweq");
        when(productService.getAllProducts()).thenReturn(Optional.of(List.of(productDTO)));

        ResponseEntity<List<ProductDTO>> response = productController.getAllProducts();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(productDTO, response.getBody().get(0));
    }

    @Test
    public void testAddProduct() {
        ProductDTO productDTO = new ProductDTO("1", "Product 1", "Description 1", "/dfsd");
        doNothing().when(productService).saveProduct(productDTO);

        ResponseEntity<Void> response = productController.addProduct(productDTO);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    public void testDeleteProduct() {
        String productId = "1";
        doNothing().when(productService).deleteProduct(productId);

        ResponseEntity<Void> response = productController.deleteProduct(productId);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }
}