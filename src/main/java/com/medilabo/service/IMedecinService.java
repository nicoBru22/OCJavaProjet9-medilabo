package com.medilabo.service;

import java.util.List;

import com.medilabo.model.Medecin;

public interface IMedecinService {
	List<Medecin> getAllMedecin();
	Medecin getMedecinById(Long id);
	Medecin addMedecin(Medecin newMedecin);
	void deleteMedecin(Long id);
	Medecin updateMedecin(Long id, Medecin updateMedecin);
}
