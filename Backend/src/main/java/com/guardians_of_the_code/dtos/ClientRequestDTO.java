package com.guardians_of_the_code.dtos;

import lombok.*;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class ClientRequestDTO {
    private String name;
    private String email;
    private String uf;
    private String city;
    private String neighborhood;
    private String road;
    private String number_house;
    private String complement;
    private String password;
}
