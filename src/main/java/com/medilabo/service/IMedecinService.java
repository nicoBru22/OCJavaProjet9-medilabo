package com.medilabo.service;

import java.util.List;

import com.medilabo.model.Medecin;

public interface IMedecinService {
	List<Medecin> getAllMedecin();
	Medecin getMedecinById(String id);
	Medecin addMedecin(Medecin newMedecin);
	void deleteMedecin(String id);
	Medecin updateMedecin(String id, Medecin updateMedecin);
}
