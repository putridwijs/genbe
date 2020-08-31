package com.putri.genbe.service;

import com.putri.genbe.dto.PersonBiodataDto;
import com.putri.genbe.entity.Biodata;

public interface PersonBiodataService {
	Biodata saveBiodataToPerson(PersonBiodataDto dto);
	
}
