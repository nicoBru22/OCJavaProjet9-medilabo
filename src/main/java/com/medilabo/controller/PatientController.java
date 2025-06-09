package com.medilabo.controller;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.medilabo.model.Patient;
import com.medilabo.service.IPatientService;

import jakarta.validation.Valid;

@RequestMapping("/patient")
@Controller
public class PatientController {

	private Logger logger = LogManager.getLogger();
	
	@Autowired
	private IPatientService patientService;
	
	@GetMapping("/liste")
	public String getListePatient(Model model) {
		logger.info("Entrée dans controller /patient/list");
		List<Patient> patientList = patientService.getAllPatient();
		logger.info("liste des patients : {}", patientList);
		model.addAttribute("patients", patientList);
		return "patientListPage";
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Patient> getPatientById(@PathVariable Long id) {
	    Optional<Patient> patientOptional = patientService.getPatientById(id);

	    if (patientOptional.isPresent()) {
	        return ResponseEntity.ok(patientOptional.get());
	    } else {
	        return ResponseEntity.notFound().build();
	    }
	}
	
	@PostMapping("/add/validate")
	public ResponseEntity<Patient> ajouterPatient(@Valid @RequestBody Patient patient) {
		Patient patientAdded = patientService.addPatient(patient);
		return ResponseEntity.ok().body(patient);
	}
	
	@DeleteMapping("/delete/validate")
	public void deletePatient(@Valid @RequestBody Patient patient) {
		patientService.deletePatient(patient);
	}
	
	@PutMapping("/update/validate")
	public Patient updatePatient(@Valid @RequestBody Patient patient) {
		patientService.updatePatient(patient);
		return patient;
	}
}
