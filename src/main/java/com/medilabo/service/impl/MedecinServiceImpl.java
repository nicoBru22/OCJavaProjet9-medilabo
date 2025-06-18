package com.medilabo.service.impl;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.medilabo.model.Medecin;
import com.medilabo.repository.IMedecinRepository;
import com.medilabo.service.IMedecinService;

@Service
public class MedecinServiceImpl implements IMedecinService {
	
	private final Logger logger = LogManager.getLogger();
	
	@Autowired
	private IMedecinRepository medecinRepository;
	
	public List<Medecin> getAllMedecin() {
		logger.info("Entrée dans le service pour récupérer la liste des médecins.");
		List<Medecin> medecinList = medecinRepository.findAll();
		logger.info("La liste des médecins : {}", medecinList);
		return medecinList;
	}
	
	public Medecin getMedecinById(String id) {
		logger.info("Entrée dans le service pour récupérer un médecin.");
		Optional<Medecin> medecin = medecinRepository.findById(id);
		logger.info("Le medecin trouvé : {}", medecin);
		return medecin.get();
	}
	
	public Medecin addMedecin(Medecin newMedecin) {
		logger.info("Entrée dans le service pour ajouter le medecin : {}", newMedecin);	
		
		Medecin medecin = new Medecin();
		medecin.setNom(newMedecin.getNom());
		medecin.setPrenom(newMedecin.getPrenom());
		medecin.setPassword(newMedecin.getPassword());
		medecinRepository.save(medecin);
		
		logger.info("Le médecin {} a été ajouté.", medecin);
		return medecin;
	}
	
	public void deleteMedecin(String id) {
		logger.info("Entrée dans le service pour supprimer le médecin avec l'id : {}", id);
		
		medecinRepository.deleteById(id);
		
		logger.info("Le médecin avec l'id {} a été supprimé.", id);
	}
	
	public Medecin updateMedecin(String id, Medecin updateMedecin) {
		logger.info("Entrée dans le service pour supprimer le médecin avec l'id : {}", id);	
	
		Optional<Medecin> medecinToUpdate = medecinRepository.findById(id);
		
		logger.info("Le medecin à modifier : {}", medecinToUpdate);
		
		Medecin medecinUpdated = medecinToUpdate.get();
		
		medecinUpdated.setNom(updateMedecin.getNom());
		medecinUpdated.setPrenom(updateMedecin.getPrenom());
		medecinUpdated.setPatientIds(updateMedecin.getPatientIds());
		
		medecinRepository.save(medecinUpdated);
		
		logger.info("Modification du médecin réussie : {}", medecinUpdated);
		
		return medecinUpdated;
	}
	
	

}
