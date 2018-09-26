package com.apap.tutorial3.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.apap.tutorial3.model.PilotModel;

@Service
public class PilotMemoryService implements PilotService {
	private List<PilotModel> archivePilot;
	
	public PilotMemoryService() {
		archivePilot = new ArrayList<>();
	}
	
	@Override
	public PilotModel removePilot(String idPilot) {
		for (int i = 0; i < archivePilot.size(); i++) {
			if(archivePilot.get(i).getId().equals(idPilot)) {
				return archivePilot.remove(i);
			}
		}
		return null;
	}
	
	@Override
	public void addPilot(PilotModel pilot) {
		archivePilot.add(pilot);
	}
	
	@Override
	public List<PilotModel> getPilotList() {
		return archivePilot;
	}
	
	@Override
	public PilotModel getPilotDetailByLicenseNumber(String licenseNumber) {
		for (PilotModel p : archivePilot) {
			if(p.getLicenseNumber().equals(licenseNumber)) {
				return p;
			}
		}
		return null;
	}

}
