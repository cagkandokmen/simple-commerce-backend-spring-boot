package com.simco.simplecommerce.service;

import com.simco.simplecommerce.dto.ProductDTO;
import com.simco.simplecommerce.entity.Product;
import com.simco.simplecommerce.mapper.ProductMapper;
import com.simco.simplecommerce.repository.ProductRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    private final ProductMapper mapper = Mappers.getMapper(ProductMapper.class);

    public Optional<List<ProductDTO>> getAllProducts() {

        List<Product> entityList = productRepository.findAll();
        List<ProductDTO> dtoList = entityList.stream()
                .map(mapper::toDTO).collect(Collectors.toList());
        return Optional.of(dtoList);
    }

    public void saveProduct(ProductDTO product) {
         productRepository.save(mapper.toEntity(product));
    }

    public void deleteProduct(String id) {
        productRepository.deleteById(id);
    }
}
