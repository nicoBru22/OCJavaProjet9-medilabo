package com.medilabo.controller;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.medilabo.model.Patient;
import com.medilabo.service.IPatientService;

import jakarta.validation.Valid;

@RequestMapping("/patient")
@Controller
public class PatientController {

	private Logger logger = LogManager.getLogger();
	
	@Autowired
	private IPatientService patientService;
	
	@GetMapping("/liste")
	public String getListePatient(Model model) {
		logger.info("Entrée dans controller /patient/list");
		List<Patient> patientList = patientService.getAllPatient();
		logger.info("liste des patients : {}", patientList);
		model.addAttribute("patients", patientList);
		return "patient/patientListPage";
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Patient> getPatientById(@PathVariable String id) {
	    Optional<Patient> patientOptional = patientService.getPatientById(id);

	    if (patientOptional.isPresent()) {
	        return ResponseEntity.ok(patientOptional.get());
	    } else {
	        return ResponseEntity.notFound().build();
	    }
	}
	
	@GetMapping("/add")
	public String getAddPatientPage(Model model) {
		logger.info("Entrée dans controller /patient/add pour ajouter un patient (addPatientPage.html).");
		model.addAttribute("patient", new Patient());
		return "patient/addPatientPage";
	}
	
	@PostMapping("/add/validate")
	public String ajouterPatient(@Valid Patient patient, BindingResult result, Model model) {
	    logger.info("Entrée dans POST /add/validate");
	    logger.info("Patient reçu : {}", patient);

	    if (result.hasErrors()) {
	        logger.info("Erreur lors de la validation : {}", result.getAllErrors());
	        return "patient/addPatientPage";
	    }

	    patientService.addPatient(patient);
	    return "redirect:/patient/liste";
	}
	
    @PostMapping("/delete/{id}")
    public String deletePatient(@PathVariable("id") String id, RedirectAttributes redirectAttributes) {
        logger.info("Entrée dans POST /delete/{id} avec id={}", id);
        patientService.deletePatient(id);
        redirectAttributes.addFlashAttribute("info", "Suppression réussie !");
        return "redirect:/patient/liste";
    }

    @GetMapping("/update/{id}")
    public String getUpdatePatientPage(@PathVariable("id") String id, Model model) {
        Patient patient = patientService.getPatientById(id).get();
        model.addAttribute("patient", patient);
        return "patient/updatePatientPage";
    }

	
    @PostMapping("/update/validate")
    public String updatePatient(@Valid @ModelAttribute("patient") Patient patient,
                                BindingResult result,
                                RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("erreur", "Erreur lors de la mise à jour.");
            return "patient/updatePatientPage";
        }
        patientService.updatePatient(patient);
        redirectAttributes.addFlashAttribute("message", "Patient mis à jour avec succès.");
        return "redirect:/patient/liste";
    }

}
