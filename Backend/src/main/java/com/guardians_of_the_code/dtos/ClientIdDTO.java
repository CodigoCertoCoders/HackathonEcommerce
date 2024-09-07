package com.guardians_of_the_code.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class ClientIdDTO {
    private UUID id;

    public ClientIdDTO(){

    }

    public ClientIdDTO(String id){
        this.id = UUID.fromString(id);
    }
}
