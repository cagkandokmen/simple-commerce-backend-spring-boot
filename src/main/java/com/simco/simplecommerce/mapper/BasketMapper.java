package com.simco.simplecommerce.mapper;

import com.simco.simplecommerce.dto.BasketDTO;
import com.simco.simplecommerce.entity.Basket;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = ProductMapper.class)
public interface BasketMapper {

    @Mapping(source = "product", target = "product")
    @Mapping(source = "product.id", target = "productId")
    BasketDTO toDTO(Basket entity);

}
