package com.guardians_of_the_code.entities;

import com.guardians_of_the_code.enums.Category;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "tb_product")
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;
    @Column(nullable = false,columnDefinition = "VARCHAR(20)")
    private String name;
    @Column(nullable = false,columnDefinition = "VARCHAR(200)")
    private String url;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Category category;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private Double price;
}
