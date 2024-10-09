package com.desafio.desafio3.mapper;

import com.desafio.desafio3.dto.ClientDto;
import com.desafio.desafio3.entity.Client;

public class ClientMapper {

    public static Client toEntity(ClientDto dto) {
        if (dto == null) {
            return null;
        }

        Client client = new Client();

        updateEntityFromDto(dto, client);

        return client;
    }

    public static ClientDto toDto(Client entity) {
        if (entity == null) {
            return null;
        }

        ClientDto dto = new ClientDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setCpf(entity.getCpf());
        dto.setIncome(entity.getIncome());
        dto.setBirthDate(entity.getBirthDate());
        dto.setChildren(entity.getChildren());

        return dto;
    }

    public static void updateEntityFromDto(ClientDto dto, Client entity) {
        if (dto == null || entity == null) {
            return;
        }

        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setCpf(dto.getCpf());
        entity.setIncome(dto.getIncome());
        entity.setBirthDate(dto.getBirthDate());
        entity.setChildren(dto.getChildren());
    }

}