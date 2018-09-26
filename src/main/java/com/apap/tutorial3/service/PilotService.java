package com.apap.tutorial3.service;

import java.util.List;

import com.apap.tutorial3.model.PilotModel;

public interface PilotService {
	void addPilot(PilotModel pilot);
	PilotModel removePilot(String idPilot); 
	List<PilotModel> getPilotList();
	PilotModel getPilotDetailByLicenseNumber(String licenseNumber);
	
}
