package com.desafio.desafio3.controller;

import com.desafio.desafio3.dto.ClientDto;
import com.desafio.desafio3.service.ClientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/clients")
public class ClientController {


    @Autowired
    private ClientService service;

    @GetMapping("/{id}")
    public ResponseEntity<ClientDto> findById(@PathVariable Long id) {

        return ResponseEntity.ok(service.findById(id));

    }

    @GetMapping()
    public ResponseEntity<Page<ClientDto>> findAll(Pageable pageable) {

        return ResponseEntity.ok(service.findAll(pageable));
    }

    @PostMapping()
    public ResponseEntity<ClientDto> insert(@Valid @RequestBody ClientDto dto) {


        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(dto.getId())
                .toUri();

        return ResponseEntity.created(uri).body(service.insert(dto));

//        return ResponseEntity.status(HttpStatus.CREATED).body(service.insert(dto));

    }

    @PutMapping("/{id}")
    public ResponseEntity<ClientDto> update(@PathVariable Long id, @Valid @RequestBody ClientDto dto) {


        return ResponseEntity.status(HttpStatus.OK).body(service.update(id, dto));

    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        service.delete(id);
        return ResponseEntity.noContent().build();

    }



}
