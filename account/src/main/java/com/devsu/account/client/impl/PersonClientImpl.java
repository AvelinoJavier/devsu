package com.devsu.account.client.impl;

import com.devsu.account.client.PersonClient;
import com.devsu.account.dto.ClientDTO;
import com.devsu.account.exception.PersonNotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Component
public class PersonClientImpl implements PersonClient {
    private final RestTemplate restTemplate;
    private final String personServiceUrl;

    public PersonClientImpl(RestTemplate restTemplate, @Value("${person.service.url}") String personServiceUrl) {
        this.restTemplate = restTemplate;
        this.personServiceUrl = personServiceUrl;
    }

    @Override
    public ClientDTO getClientById(Long id) throws PersonNotFoundException {
        String url = personServiceUrl + "clientes/" + id;
        try {
            ResponseEntity<ClientDTO> response = restTemplate.getForEntity(url, ClientDTO.class);
            return response.getBody();
        } catch (HttpClientErrorException.NotFound e) {
            throw new PersonNotFoundException("Person with id " + id + " not found");
        }
    }
}
