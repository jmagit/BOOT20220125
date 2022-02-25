package com.example.infraestructure.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.domains.entities.Contacto;

public interface ContactoRepository extends MongoRepository<Contacto, Integer> {

}
