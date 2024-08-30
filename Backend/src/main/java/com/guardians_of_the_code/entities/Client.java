package com.guardians_of_the_code.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

import java.util.UUID;

@Entity
@Table(name = "tb_client")
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class Client extends RepresentationModel<Client> {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;
    @Column(length = 30)
    private String name;
    @Column(columnDefinition = "CHAR(8)")
    private String cep;
    @Column(length = 120,unique = true)
    private String email;
    @Column(columnDefinition = "CHAR(11)",unique = true)
    private String phone;

}
