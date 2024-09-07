package com.guardians_of_the_code;

import com.guardians_of_the_code.dtos.ClientRequestDTO;
import com.guardians_of_the_code.exceptions.HandleBadRequestException;
import com.guardians_of_the_code.exceptions.HandleNotFoundException;
import com.guardians_of_the_code.use_cases.CreateClientUseCase;
import com.guardians_of_the_code.use_cases.DeleteClientUseCase;
import com.guardians_of_the_code.use_cases.FindClientByUUIDUseCase;
import com.guardians_of_the_code.use_cases.UpdateClientUseCase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ClientTest {
  /*  @Autowired
    private CreateClientUseCase createClientUseCase;

    @Autowired
    private FindClientByUUIDUseCase findClientByUUIDUseCase;

    @Autowired
    private DeleteClientUseCase deleteClientUseCase;

    @Autowired
    private UpdateClientUseCase updateClientUseCase;


    @Test
    void createClientWithNameIsEmptyOrBlank() {
        ClientRequestDTO request = new ClientRequestDTO("", "joca@gmail.com", "44095400", "75990000000","12345678");

        HandleBadRequestException thrown = assertThrows(HandleBadRequestException.class, () -> {
            createClientUseCase.execute(request);
        });

        assertEquals("Nome não pode ser vazio ou nulo", thrown.getMessage());
    }

    @Test
    void createClientWithNameIsLessThirtyLength() {
        ClientRequestDTO request = new ClientRequestDTO("Jocaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", "joca@gmail.com", "440954000", "75990000000","12345678");

        int valueOfRequest = request.getName().length();
        boolean conditionRequest = valueOfRequest > 30;

        assertTrue(conditionRequest);
    }

    @Test
    void createEmailWithEmailIsLessHundredTwentyLength() {
        String longEmail = "jocaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa@gmail.com";
        ClientRequestDTO request = new ClientRequestDTO("Joca", longEmail, "440954000", "75990000000","12345678");

        int valueOfRequest = request.getEmail().length();
        boolean conditionRequest = valueOfRequest > 120;

        assertTrue(conditionRequest);
    }

    @Test
    void createClientWithCepIsEmptyOrBlank() {
        ClientRequestDTO request = new ClientRequestDTO("Joca", "joca@gmail.com", "", "75990000000","12345678");

        HandleBadRequestException thrown = assertThrows(HandleBadRequestException.class, () -> {
            createClientUseCase.execute(request);
        });

        assertEquals("Cep não pode ser vazio ou nulo", thrown.getMessage());
    }

    @Test
    void createClientWithCepIsMoreEigthLength() {
        ClientRequestDTO request = new ClientRequestDTO("Joca", "joca@gmail.com", "440954000", "75990000000","12345678");

        assertNotEquals(request.getCep().length(),8);
    }

    @Test
    void createClientWithCepIsLessEigthLength() {
        ClientRequestDTO request = new ClientRequestDTO("Joca", "joca@gmail.com", "440954", "75990000000","12345678");

        assertNotEquals(request.getCep().length(),8);
    }

    @Test
    void createClientWithEmailIsEmptyOrBlank() {
        ClientRequestDTO request = new ClientRequestDTO("Joca", "", "44095400", "75990000000","12345678");

        HandleBadRequestException thrown = assertThrows(HandleBadRequestException.class, () -> {
            createClientUseCase.execute(request);
        });

        assertEquals("Email não pode ser vazio ou nulo", thrown.getMessage());
    }

    @Test
    void createClientWithPhoneIsEmptyOrBlank() {
        ClientRequestDTO request = new ClientRequestDTO("Joca", "joca@gmail.com", "44095400", "","12345678");

        HandleBadRequestException thrown = assertThrows(HandleBadRequestException.class, () -> {
            createClientUseCase.execute(request);
        });

        assertEquals("Telefone não pode ser vazio ou nulo", thrown.getMessage());
    }

    @Test
    void createClientWithPhoneIsMoreElevenLength() {
        ClientRequestDTO request = new ClientRequestDTO("Joca", "joca@gmail.com", "440954000", "759900000000","12345678");

        assertNotEquals(request.getCep().length(),11);
    }

    @Test
    void createClientWithPhoneIsLessElevenLength() {
        ClientRequestDTO request = new ClientRequestDTO("Joca", "joca@gmail.com", "440954000", "7599000","12345678");

        assertNotEquals(request.getCep().length(),11);
    }

    @Test
    void verifyThrowIfNotFoundWhenFindClient() {
        UUID uuid = UUID.randomUUID();

        HandleNotFoundException thrown = assertThrows(HandleNotFoundException.class, () -> {
            findClientByUUIDUseCase.execute(uuid);
        });

        assertEquals("Cliente não encontrado", thrown.getMessage());
    }
*/

}

