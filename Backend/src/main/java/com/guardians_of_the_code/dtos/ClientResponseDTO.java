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
    private String cep;
    private String phone;
}
