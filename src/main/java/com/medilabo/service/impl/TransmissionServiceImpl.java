package com.medilabo.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.medilabo.model.Transmission;
import com.medilabo.repository.ITransmissionRepository;

@Service
public class TransmissionServiceImpl {
	
	private Logger logger = LogManager.getLogger();
	
	@Autowired
	private ITransmissionRepository transmissionRepository;
	
	public List<Transmission> getAllTransmissions() {
		logger.info("");
		List<Transmission> transmissionList = transmissionRepository.findAll();
		logger.info("");
		return transmissionList;
	}
	
	public Transmission getTransmission(Long id) {
		logger.info("");
		Optional<Transmission> transmisson = transmissionRepository.findById(id);
		logger.info("");
		return transmisson.get();
	}
	
	public Transmission addTransmission(Transmission newTransmission) {
	    logger.info("Création d'une nouvelle transmission manuellement");

	    Transmission transmission = new Transmission();
	    transmission.setDateTransmission(LocalDateTime.now());
	    transmission.setMedecinId(newTransmission.getMedecinId());
	    transmission.setNomMedecin(newTransmission.getNomMedecin());
	    transmission.setPrenomMedecin(newTransmission.getPrenomMedecin());
	    transmission.setPatientId(newTransmission.getPatientId());
	    transmission.setTransmission(newTransmission.getTransmission());

	    transmissionRepository.save(transmission);

	    logger.info("Transmission créée avec succès");

	    return transmission;
	}
	
	public void deleteTransmission(Long id) {
		transmissionRepository.deleteById(id);
	}
	
	public Transmission updateTransmission(Long id, Transmission updatedTransmission) {
	    logger.info("Mise à jour de la transmission avec l'id : {}", id);

	    Transmission existing = transmissionRepository.findById(id)
	        .orElseThrow(() -> new RuntimeException("Transmission non trouvée avec l'id : " + id));

	    // On conserve l'id et met à jour la date
	    updatedTransmission.setId(id);
	    updatedTransmission.setDateTransmission(LocalDateTime.now());

	    Transmission saved = transmissionRepository.save(updatedTransmission);

	    logger.info("Transmission mise à jour avec succès : {}", saved.getId());

	    return saved;
	}



}
