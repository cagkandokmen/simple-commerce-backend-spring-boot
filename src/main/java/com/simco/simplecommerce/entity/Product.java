package com.simco.simplecommerce.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "\"Product\"")
@Getter
@Setter
public class Product extends BaseEntity {
    @Id
    @Column(name = "id", nullable = false, length = 20)
    private String id;
    @Column(name = "name", nullable = false, length = 100)
    private String name;
    @Column(name = "type", nullable = false, length = 100)
    private String type;
    @Column(name = "imagePath", nullable = false, length = 100)
    private String imagePath;
}