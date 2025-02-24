package com.simco.simplecommerce.mapper;

import com.simco.simplecommerce.dto.ProductDTO;
import com.simco.simplecommerce.entity.Product;
import org.mapstruct.Mapper;

@Mapper
public interface ProductMapper {
    ProductDTO toDTO(Product entity);
    Product toEntity(ProductDTO dto);

}