package com.simco.simplecommerce.repository;

import com.simco.simplecommerce.entity.Basket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BasketRepository extends JpaRepository<Basket, Integer> {

    List<Basket> findByUserId(String userId);

    void deleteByIdAndUserId(Integer id, String userId);
}
