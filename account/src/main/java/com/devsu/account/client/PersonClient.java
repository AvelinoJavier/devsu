package com.devsu.account.client;

import com.devsu.account.dto.ClientDTO;
import com.devsu.account.exception.PersonNotFoundException;

public interface PersonClient {
    ClientDTO getClientById(Long id) throws PersonNotFoundException;
}
