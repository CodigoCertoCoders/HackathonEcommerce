package com.guardians_of_the_code.dtos;

import lombok.*;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class ClientRequestDTO {
    private String name;
    private String email;
    private String cep;
    private String phone;
    private String password;
}
