package com.guardians_of_the_code.dtos;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class ClientResponseDTO {
    private UUID id;
    private String name;
    private String email;
    private String uf;
    private String city;
    private String neighborhood;
    private String road;
    private String number_house;
    private String complement;
}
