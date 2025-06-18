package com.medilabo.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

import com.medilabo.model.Medecin;
import com.medilabo.service.IMedecinService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;

@RequestMapping("/medecin")
@RestController
public class MedecinControler {
	
	private Logger logger = LogManager.getLogger();
	
	@Autowired
	private IMedecinService medecinService;
	
	@GetMapping("/list")
	public ResponseEntity<List<Medecin>> getListMedecin() {
		logger.info("ENtrée dans le controller pour récupérer la liste des médecins.");
		List<Medecin> medecinListe = medecinService.getAllMedecin();
		return ResponseEntity.ok(medecinListe);
	}
	
	@PostMapping("/add")
	public ResponseEntity<Medecin> addMedecin(@Valid @RequestBody Medecin medecin) {
		logger.info("Entrée dans le controller pour ajouter un nouveau médecins.");
		Medecin newMedecin = medecinService.addMedecin(medecin);
		return ResponseEntity.status(HttpStatus.CREATED).body(newMedecin);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteMedecinById(@PathVariable("id") String id) {
		logger.info("Entrée dans le controller pour supprimer un médecins par son Id.");
		medecinService.deleteMedecin(id);
	    return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<Medecin> updateMedecin(
			@PathVariable("id") String id, 
			@Valid @RequestBody Medecin medecin,
			BindingResult result) {
		Medecin medecinUpdated = medecinService.updateMedecin(id, medecin);
		return ResponseEntity.ok(medecinUpdated);
	}
	
	

}
