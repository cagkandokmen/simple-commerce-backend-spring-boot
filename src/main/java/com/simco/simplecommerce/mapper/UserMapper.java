package com.simco.simplecommerce.mapper;

import com.simco.simplecommerce.dto.UserDTO;
import com.simco.simplecommerce.entity.User;
import org.mapstruct.Mapper;

@Mapper
public interface UserMapper {
    UserDTO toDTO(User entity);
    User toEntity(UserDTO dto);
}
