package com.putri.genbe.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.putri.genbe.entity.Biodata;
import com.putri.genbe.repository.BiodataRepository;
import com.putri.genbe.repository.PersonRepository;

@Service
public class PersonBiodataServiceImpl implements PersonBiodataService {
	@Autowired
	PersonRepository personRepository;

	@Autowired
	BiodataRepository biodataRepository;

	@Override
	public Biodata saveBiodataToPerson(Biodata biodata) {
		personRepository.save(biodata.getPerson());
		biodataRepository.save(biodata);
		return null;
	}

}
