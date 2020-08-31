package com.putri.genbe.service;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.putri.genbe.dto.PersonBiodataDto;
import com.putri.genbe.entity.Biodata;
import com.putri.genbe.entity.Person;
import com.putri.genbe.repository.BiodataRepository;
import com.putri.genbe.repository.PersonRepository;

@Service
@Transactional
public class PersonBiodataServiceImpl implements PersonBiodataService {
	@Autowired
	private PersonRepository personRepository;

	@Autowired
	private BiodataRepository biodataRepository;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public Biodata saveBiodataToPerson(PersonBiodataDto dto) {
//		mapping data PersonBiodata ke Biodata
		Biodata biodata = modelMapper.map(dto, Biodata.class);
//		mapping data PersonBiodataDto ke Person
		Person person = modelMapper.map(dto, Person.class);
//		set person
		biodata.setPerson(person);
		personRepository.save(biodata.getPerson());
		biodataRepository.save(biodata);
		return null;
	}

}
