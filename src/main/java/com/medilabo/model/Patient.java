package com.medilabo.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "patient")
public class Patient {
	
	@Id
	Long id;
	
	String prenom;
	String nom;
	Date dateNaissance;
	String genre;
	String adresse;
	String telephone;

}
