package com.simco.simplecommerce.service;

import com.simco.simplecommerce.dto.BasketDTO;
import com.simco.simplecommerce.dto.NewItemDTO;
import com.simco.simplecommerce.entity.Basket;
import com.simco.simplecommerce.entity.Product;
import com.simco.simplecommerce.exception.CustomException;
import com.simco.simplecommerce.mapper.BasketMapper;
import com.simco.simplecommerce.repository.BasketRepository;
import jakarta.transaction.Transactional;
import org.mapstruct.factory.Mappers;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BasketService {
    private final BasketRepository basketRepository;
    private final BasketMapper mapper = Mappers.getMapper(BasketMapper.class);

    public BasketService(BasketRepository basketRepository) {
        this.basketRepository = basketRepository;
    }
    public Optional<List<BasketDTO>> getBasket() {
        List<Basket> enttiyList = basketRepository.findByUserId("admin");
        List<BasketDTO> dtoList=enttiyList.stream()
                .map(mapper::toDTO).collect(Collectors.toList());
        return Optional.of(dtoList);
    }
    @Transactional
    public void deleteBasket(int id) {
        basketRepository.deleteByIdAndUserId(id, getUserName());
    }

    public void addItemToBasket(NewItemDTO basket) {
        Basket b = new Basket();
        Product p =new Product();
        p.setId(basket.productId());
        b.setProduct(p);
        b.setUserId(getUserName());

        basketRepository.save(b);
    }

    private String getUserName(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            return ((UserDetails) principal).getUsername();
        } else {
            throw new CustomException("User Not found");
        }
    }
}

