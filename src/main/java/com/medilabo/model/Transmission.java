package com.medilabo.model;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "transmission")
public class Transmission {

	@Id
	Long id;
	
	
	Long medecinId;
	Long patientId;
	String nomMedecin;
	String prenomMedecin;
	
	@NotNull
	LocalDateTime dateTransmission;
	
	@NotNull
	String transmission;
	
}
