package com.medilabo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.medilabo.model.Transmission;

@Repository
public interface ITransmissionRepository extends MongoRepository<Transmission, Long> {

}
