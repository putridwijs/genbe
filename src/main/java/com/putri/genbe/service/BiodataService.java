package com.putri.genbe.service;

import java.time.LocalDate;

import javax.xml.ws.Response;

import com.putri.genbe.entity.Biodata;

public interface BiodataService {
	Biodata saveBiodataToPerson(Biodata biodata) throws Exception;
	
	Biodata createResponse(Biodata biodata);
	
	Integer calculateAge(LocalDate birthDate, LocalDate currentDate);
}
