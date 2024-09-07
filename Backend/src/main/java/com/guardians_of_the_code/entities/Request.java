package com.guardians_of_the_code.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.guardians_of_the_code.dtos.ProductIdDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "tb_request")
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class Request {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;
    @Column(nullable = false)
    private int quantity;
    @Column(nullable = false)
    private double price;
    @Column(columnDefinition = "JSON", nullable = false)
    private String products;
    @ManyToOne
    @JoinColumn(name = "client",nullable = false)
    private Client client;
    @Column(nullable = false)
    private double freight;
    @Column(name = "created_at",nullable = false,updatable = false)
    private LocalDateTime createdAt;
    @Column(name = "updated_at",nullable = false,updatable = true)
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate(){
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdated(){
        this.updatedAt = LocalDateTime.now();
    }
}
