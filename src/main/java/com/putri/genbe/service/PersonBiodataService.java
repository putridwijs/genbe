package com.putri.genbe.service;

import com.putri.genbe.dto.PersonBiodataDto;
import com.putri.genbe.dto.Response;
import com.putri.genbe.entity.Biodata;
import com.putri.genbe.entity.Person;

public interface PersonBiodataService {
	Biodata saveBiodataToPerson(PersonBiodataDto dto);
	Response insertDataPerson(PersonBiodataDto personBiodataDto, Person person, Biodata biodata, Response response);
	
}
