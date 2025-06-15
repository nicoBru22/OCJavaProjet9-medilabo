package com.medilabo.service;

import java.util.List;

import com.medilabo.model.Transmission;

public interface ITransmissionService {
	List<Transmission> getAllTransmissions();
	Transmission getTransmission(Long id);
	Transmission addTransmission(Transmission newTransmission);
	public void deleteTransmission(Long id);
	Transmission updateTransmission(Long id, Transmission updatedTransmission);

}
