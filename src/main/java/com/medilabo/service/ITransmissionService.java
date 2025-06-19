package com.medilabo.service;

import java.util.List;

import com.medilabo.model.Transmission;

public interface ITransmissionService {
	List<Transmission> getAllTransmissions();
	Transmission getTransmission(String id);
	Transmission addTransmission(Transmission newTransmission);
	public void deleteTransmission(String id);
	Transmission updateTransmission(String id, Transmission updatedTransmission);
	List<Transmission> getAllTransmissionsByMedecinId(String medecinId);
	List<Transmission> getAllTransmissionsByPatientId(String patientId);


}
