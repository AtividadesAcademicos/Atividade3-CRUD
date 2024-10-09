package com.desafio.desafio3.service;

import com.desafio.desafio3.dto.ClientDto;
import com.desafio.desafio3.entity.Client;
import com.desafio.desafio3.exceptions.ResourceNotFoundException;
import com.desafio.desafio3.mapper.ClientMapper;
import com.desafio.desafio3.repository.ClientRespository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClientService {

    @Autowired
    private ClientRespository repository;

    @Transactional(readOnly = true)
    public ClientDto findById(Long id) {

        Client client = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Recurso não encontrado"));

        return ClientMapper.toDto(client);
    }

    @Transactional(readOnly = true)
    public Page<ClientDto> findAll(Pageable pageable) {

        Page<Client> result = repository.findAll(pageable);

        return result.map(ClientMapper::toDto);
    }

    @Transactional
    public ClientDto insert(@Valid ClientDto dto) {

        Client client = ClientMapper.toEntity(dto);

        client = repository.save(client);

        return ClientMapper.toDto(client);
    }

    @Transactional
    public ClientDto update(Long id, @Valid ClientDto dto) {

        Client client = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Recurso não encontrado"));
        ClientMapper.updateEntityFromDto(dto, client);

        client = repository.save(client);

        return ClientMapper.toDto(client);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Long id) {

        repository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Recurso não encontrado com ID: " + id));
        repository.deleteById(id);
    }
}