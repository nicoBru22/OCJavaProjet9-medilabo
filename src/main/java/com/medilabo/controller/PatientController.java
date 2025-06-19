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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.medilabo.model.Medecin;
import com.medilabo.model.Patient;
import com.medilabo.service.IPatientService;

import jakarta.validation.Valid;

@RequestMapping("/patient")
@RestController
public class PatientController {

	private Logger logger = LogManager.getLogger();
	
	@Autowired
	private IPatientService patientService;
	
    @GetMapping("/list")
    public List<Patient> getAllPatients() {
        List<Patient> patientList = patientService.getAllPatient();
        return patientList;
    }
	
	@GetMapping("/infos/{id}")
	public Patient getPatientById(@PathVariable String id) {
	    Patient patient = patientService.getPatientById(id);
        return patient;
	}
	
	@PostMapping("/add")
	public ResponseEntity<?> ajouterPatientApi(@RequestBody @Valid Patient patient, BindingResult result) {
	    logger.info("Entrée dans POST patient/add");
	    logger.info("Patient reçu : {}", patient);

	    if (result.hasErrors()) {
	    	logger.error("une erreur lors de l ajout du patient.");
	        result.getAllErrors().forEach(error -> logger.error(error.toString()));
	        return ResponseEntity.badRequest().body(result.getAllErrors());
	    }

	    Patient savedPatient = patientService.addPatient(patient);
	    return ResponseEntity.status(HttpStatus.CREATED).body(savedPatient);
	}

	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Void> deletePatient(@PathVariable("id") String id) {
	    logger.info("Entrée dans DELETE /delete/{} ", id);
	    patientService.deletePatient(id);
	    return ResponseEntity.noContent().build(); // 204 No Content
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<?> updatePatient(
	        @PathVariable("id") String id,
	        @Valid @RequestBody Patient patient,
	        BindingResult result) {

	    if (result.hasErrors()) {
	        logger.error("Erreur lors de la mise à jour du patient : {}", result.getAllErrors());
	        return ResponseEntity.badRequest().body(result.getAllErrors());
	    }

	    Patient updatedPatient = patientService.updatePatient(patient).get();

	    logger.info("Mise à jour réussie du patient avec id {}", id);

	    return ResponseEntity.ok(updatedPatient); // Renvoie le patient mis à jour en JSON
	}
	
	@GetMapping("/medecinByPatient")
	public ResponseEntity<List<Medecin>> getMedecinsByPatient(String id) {
		List<Medecin> medecinList = patientService.getMedecinsByPatient(id);
		return ResponseEntity.status(HttpStatus.OK).body(medecinList) ;
	}


}
