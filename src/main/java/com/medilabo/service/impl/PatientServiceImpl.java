package com.medilabo.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.medilabo.model.Patient;
import com.medilabo.repository.IPatientRepository;
import com.medilabo.service.IPatientService;

@Service
public class PatientServiceImpl implements IPatientService{
	
	private Logger logger = LogManager.getLogger();
	
	@Autowired
	private IPatientRepository patientRepository;

	public List<Patient> getAllPatient() {
		List<Patient> listePatient = patientRepository.findAll();
		return listePatient;
	}
	
	public Optional<Patient> getPatientById(String id) {
		Optional<Patient> patient = patientRepository.findById(id);
		return patient;
	}
	
    public void deletePatient(String id) {
        logger.info("Appel à deletePatientById avec id = {}", id);
        patientRepository.deleteById(id);
        logger.info("Patient supprimé avec succès pour id = {}", id);
    }

	
	public Patient addPatient(Patient newPatient) {
		logger.info("Ajout du nouveau patient : {}", newPatient);
		
		newPatient.setDateCreation(LocalDateTime.now());
		newPatient.setDateModification(LocalDateTime.now());
		patientRepository.save(newPatient);
		return newPatient;
	}
	
	public Optional<Patient> updatePatient(Patient patient) {
		Optional<Patient> patientExiste = patientRepository.findById(patient.getId());
		
		if(patientExiste.isPresent()) {
			Patient patientToUpdate = patientExiste.get();
			patientToUpdate.setNom(patient.getNom());
			patientToUpdate.setPrenom(patient.getPrenom());
			patientToUpdate.setAdresse(patient.getAdresse());
			patientToUpdate.setTelephone(patient.getTelephone());
			patientToUpdate.setGenre(patient.getGenre());
			patientToUpdate.setDateNaissance(patient.getDateNaissance());
			
			patientRepository.save(patientToUpdate);
			
			return Optional.of(patientToUpdate);
		} else {
			throw new RuntimeException("Le patient n'existe pas avec l'Id " + patient.getId());
		}
		
	}
}
