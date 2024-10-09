package com.desafio.desafio3.service;

import com.desafio.desafio3.dto.ClientDto;
import com.desafio.desafio3.entity.Client;
import com.desafio.desafio3.exceptions.ResourceNotFoundException;
import com.desafio.desafio3.repository.ClientRespository;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
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

    @Autowired
    private ModelMapper mapper;

    @Transactional(readOnly = true)
    public ClientDto findById(Long id) {

        Client client = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Recurso não encontrado"));

        return mapper.map(client, ClientDto.class);
    }

    @Transactional(readOnly = true)
    public Page<ClientDto> findAll(Pageable pageable) {

        Page<Client> result = repository.findAll(pageable);

        return result.map(Cliente -> mapper.map(Cliente, ClientDto.class));
    }


    @Transactional
    public ClientDto insert(@Valid ClientDto dto) {

        Client client = mapper.map(dto, Client.class);

        return mapper.map(repository.save(client), ClientDto.class);

    }

    @Transactional
    public ClientDto update(Long id, @Valid ClientDto dto) {


        Client client = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Recurso não encontrado"));

        mapper.map(dto, client);

        client = repository.save(client);

        return mapper.map(client, ClientDto.class);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Long id) {


        repository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Recurso não encontrado com ID: " + id));

        repository.deleteById(id);
    }

}
