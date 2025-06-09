package com.medilabo.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.medilabo.model.Patient;
import com.medilabo.repository.IPatientRepository;
import com.medilabo.service.IPatientService;

@Service
public class PatientServiceImpl implements IPatientService{
	
	@Autowired
	private IPatientRepository patientRepository;

	public List<Patient> getAllPatient() {
		List<Patient> listePatient = patientRepository.findAll();
		return listePatient;
	}
	
	public Optional<Patient> getPatientById(Long id) {
		Optional<Patient> patient = patientRepository.findById(id);
		return patient;
	}
	
	public void deletePatient(Patient patient) {
		patientRepository.delete(patient);
	}
	
	public Patient addPatient(Patient patient) {
		patientRepository.save(patient);
		return patient;
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
