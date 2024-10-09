package com.desafio.desafio3.repository;

import com.desafio.desafio3.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRespository extends JpaRepository<Client, Long> {
}
