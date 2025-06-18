package com.medilabo.model;

import java.util.List;

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
@Document(collection = "medecin")
public class Medecin {
	
	@Id
	String id;
	
	@NotNull
	String nom;
	
	@NotNull
	String prenom;
	
	@NotNull
	String password;
	
	List<Long> patientIds;

}
