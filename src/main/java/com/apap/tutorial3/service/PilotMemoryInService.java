package com.apap.tutorial3.service;

import java.util.ArrayList;
import java.util.List;

import com.apap.tutorial3.model.PilotModel;
import org.springframework.stereotype.Service;

/**
 * PilotMemoryInService
 * @author Ifdhal Suharmitan
 */

@Service
public class PilotMemoryInService implements PilotService {
	private List<PilotModel> archivePilot;
	
	public PilotMemoryInService() {
		archivePilot = new ArrayList<>();
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
		for(int i = 0; i < archivePilot.size(); i++) {
			if(archivePilot.get(i).getLicenseNumber().equals(licenseNumber)) {
				return archivePilot.get(i);
			}
		}
		return null;
		
	}
	
	@Override
	public PilotModel updateFlyHour(String licenseNumber, Integer newflyHour) { 
		PilotModel pilot = this.getPilotDetailByLicenseNumber(licenseNumber);
		pilot.setFlyHour(newflyHour);
		return pilot;
	}
	
	@Override
	public PilotModel deletePilotById(String id) {
		for(int i = 0; i < archivePilot.size(); i++) {
			if(archivePilot.get(i).getId().equals(id)) {
				return archivePilot.remove(i);
			}
		}
		return null;
	}

}
