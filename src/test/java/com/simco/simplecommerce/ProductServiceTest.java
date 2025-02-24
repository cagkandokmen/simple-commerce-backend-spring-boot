package com.simco.simplecommerce;

import com.simco.simplecommerce.dto.ProductDTO;
import com.simco.simplecommerce.entity.Product;
import com.simco.simplecommerce.mapper.ProductMapper;
import com.simco.simplecommerce.repository.ProductRepository;
import com.simco.simplecommerce.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private ProductMapper productMapper;

    @InjectMocks
    private ProductService productService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllProducts() {
        Product product = new Product();
        product.setId("1");
        ProductDTO productDTO = new ProductDTO("1", "Product 1", "Description 1", "image.jpg");

        when(productRepository.findAll()).thenReturn(List.of(product));

        Optional<List<ProductDTO>> result = productService.getAllProducts();
        assertEquals(1, result.get().size());
    }

    @Test
    public void testSaveProduct() {
        ProductDTO productDTO = new ProductDTO("1", "Product 1", "Description 1", "image.jpg");
        Product product = new Product();
        product.setId("1");
        product.setName("Product 1");
        product.setImagePath("image.jpg");
        product.setType("Type");
        when(productRepository.save(any())).thenReturn(product);

        productService.saveProduct(productDTO);
        verify(productRepository, times(1)).save(any());
    }

    @Test
    public void testDeleteProduct() {
        String productId = "1";
        doNothing().when(productRepository).deleteById(productId);

        productService.deleteProduct(productId);
        verify(productRepository, times(1)).deleteById(productId);
    }
}