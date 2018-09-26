package com.apap.tutorial3.controller;

import java.util.List;

import com.apap.tutorial3.model.PilotModel;
import com.apap.tutorial3.service.PilotService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PilotController {
	@Autowired
	private PilotService pilotService;
	
	@RequestMapping("/pilot/add")
	public String add(@RequestParam(value = "id", required = true) String id,
					  @RequestParam(value = "licenseNumber", required = true) String licenseNumber,
					  @RequestParam(value = "name", required = true) String name,
					  @RequestParam(value = "flyHour", required = true) int flyHour) {
		PilotModel pilot = new PilotModel(id, licenseNumber, name, flyHour);
		pilotService.addPilot(pilot);
		return "add";
	}
	
	@RequestMapping("/pilot/view")
	public String view(@RequestParam("licenseNumber") String licenseNumber, Model model) {
		PilotModel archive = pilotService.getPilotDetailByLicenseNumber(licenseNumber);
		
		model.addAttribute("pilot", archive);
		return "view-pilot";
	}
	
	@RequestMapping("/pilot/viewall")
	public String viewAll(Model model) {
		List<PilotModel> archive = pilotService.getPilotList();
		model.addAttribute("pilotList", archive);
		return "viewall-pilot";
	}
	
	@RequestMapping("/pilot/view/license-number/{licenseNumber}")
	public String viewLicenseNumber(@PathVariable String licenseNumber, Model model) {
		PilotModel archive = pilotService.getPilotDetailByLicenseNumber(licenseNumber); 

		if(archive == null) {
			return "view-errorLicenseNumber";
		}
		model.addAttribute("pilot", archive);
		return "view-pilot";
	}
	
	@RequestMapping("/pilot/update/license-number/{licenseNumber}/fly-hour/{newflyHour}")
	public String update(@PathVariable String licenseNumber, @PathVariable Integer newflyHour, Model model) {
		PilotModel archive = pilotService.updateFlyHour(licenseNumber, newflyHour);

		if(archive == null) {
			return "error-flyHour";
		}
		model.addAttribute("licenseNumber", licenseNumber);
		return "update-flyhour";
	}
	
	@RequestMapping("/pilot/delete/id/{id}") 
	public String delete(@PathVariable String id, Model model) {
		PilotModel archive = pilotService.deletePilotById(id);
		
		if(archive == null) {
			return "error-delete";
		}
		model.addAttribute("pilot", archive);
		return "delete";
	}
	
}

