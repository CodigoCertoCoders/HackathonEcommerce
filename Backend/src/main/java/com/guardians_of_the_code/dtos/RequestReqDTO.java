package com.guardians_of_the_code.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class RequestReqDTO {
    private int quantity;
    private double price;
    @JsonProperty("products")
    private List<String> products;
    private ClientIdDTO client_id;
    private double freight;
}
