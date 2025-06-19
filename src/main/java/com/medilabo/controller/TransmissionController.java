package com.medilabo.controller;

import org.springframework.web.bind.annotation.RestController;

import com.medilabo.model.Transmission;
import com.medilabo.service.ITransmissionService;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/transmission")
public class TransmissionController {

	private static final Logger logger = LogManager.getLogger(TransmissionController.class);

	@Autowired
	private ITransmissionService transmissionService;

	@PostMapping("/add")
	public ResponseEntity<Transmission> addTransmission(@Valid @RequestBody Transmission newTransmission) {
		logger.info("Requête reçue pour ajouter une transmission : {}", newTransmission);

		try {
			Transmission transmission = transmissionService.addTransmission(newTransmission);
			logger.info("Transmission ajoutée avec succès : {}", transmission);
			return ResponseEntity.status(HttpStatus.CREATED).body(transmission);
		} catch (Exception e) {
			logger.error("Erreur lors de l'ajout de la transmission", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@GetMapping("/getTransmissionsOfPatient")
	public ResponseEntity<List<Transmission>> getAllTransmissionOfPatient(@RequestParam String patientid) {
		logger.info("Requête reçue pour récupérer les transmissions du patient avec l'ID : {}", patientid);

		try {
			List<Transmission> transmissionList = transmissionService.getAllTransmissionsByPatientId(patientid);
			if (transmissionList.isEmpty()) {
				logger.warn("Aucune transmission trouvée pour le patient avec l'ID : {}", patientid);
			} else {
				logger.info("{} transmissions trouvées pour le patient {}", transmissionList.size(), patientid);
			}
			return ResponseEntity.status(HttpStatus.OK).body(transmissionList);
		} catch (Exception e) {
			logger.error("Erreur lors de la récupération des transmissions du patient : {}", patientid, e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
}
