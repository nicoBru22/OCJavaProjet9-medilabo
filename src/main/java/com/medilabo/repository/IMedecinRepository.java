package com.medilabo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.medilabo.model.Medecin;

@Repository
public interface IMedecinRepository extends MongoRepository<Medecin, String> {

}
