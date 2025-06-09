package com.medilabo.service;

import java.util.List;
import java.util.Optional;

import com.medilabo.model.Patient;

public interface IPatientService {
	List<Patient> getAllPatient();
	Optional<Patient> getPatientById(Long id);
	void deletePatient(Patient patient);
	Patient addPatient(Patient patient);
	Optional<Patient> updatePatient(Patient patient);
}
