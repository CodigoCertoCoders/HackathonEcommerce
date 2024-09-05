package com.guardians_of_the_code.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class RequestResDTO {
    private UUID id;
    private int quantity;
    private double price;
    @JsonProperty("products")
    private List<String> products;
    private ClientIdDTO client_id;
    private double freight;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
