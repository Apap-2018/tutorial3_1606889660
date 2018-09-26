package com.apap.tutorial3.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.apap.tutorial3.model.PilotModel;
import com.apap.tutorial3.service.PilotService;

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
		model.addAttribute("listPilot", archive);
		return "viewall-pilot";
	}
	
	@RequestMapping(value = {"/pilot/view/license-number", "pilot/view/license-number/{licenseNumber}"})
	public String viewUpdatePath(@PathVariable Optional<String> licenseNumber, Model model) {
		if (licenseNumber.isPresent()) {
			model.addAttribute("pilot", pilotService.getPilotDetailByLicenseNumber(licenseNumber.get()));
		}else {
			model.addAttribute("pilot", null);
		}
		return "view-pilot";
	}
	
	@RequestMapping(value = {"/pilot/update/license-number", "pilot/update/license-number/{licenseNumber}/fly-hour/{flyHour}"})
	public String updateWithPath(@PathVariable Optional<String> licenseNumber,
								@PathVariable Optional<String> flyHour,
								Model model) {
		PilotModel curr;
		if (licenseNumber.isPresent()) {
			curr = pilotService.getPilotDetailByLicenseNumber(licenseNumber.get());
			if(curr != null) {
				curr.setFlyHour(Integer.parseInt(flyHour.get()));
				return "update-flyhour";
			}
			
		}
		model.addAttribute("msgError", "numberLicense kosong atau tidak ditemukan dan proses update dibatalkan");
		return "error";
		
	}
	
	@RequestMapping(value = {"/pilot/delete/id", "pilot/delete/{id}"})
	public String deleteWithPath(@PathVariable Optional<String> id,	Model model) {
		if(id.isPresent()) {
			PilotModel deleted = pilotService.removePilot(id.get());
			if (deleted!=null) {
				return "delete";
			}
		}
		model.addAttribute("msgError", "id kosong atau tidak ditemukan dan proses delete dibatalkan");
		return "error";
		
	}
	
}
