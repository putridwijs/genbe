package com.putri.genbe.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.putri.genbe.dto.PendidikanDto;
//import com.putri.genbe.dto.PendidikanDto;
import com.putri.genbe.entity.Pendidikan;
import com.putri.genbe.entity.PendidikanWrapper;
import com.putri.genbe.entity.Person;
import com.putri.genbe.repository.PendidikanRepository;
import com.putri.genbe.repository.PersonRepository;

@Service
public class PendidikanServiceImpl implements PendidikanService {

	@Autowired
	private PendidikanRepository pendidikanRepository;

	@Autowired
	private PersonRepository personRepository;

//	@Autowired
//	private ModelMapper modelMapper;

	@Override
	public List<PendidikanDto> savePendidikan(List<PendidikanDto> pendidikanDto, Integer idPerson) {
		for (PendidikanDto dto : pendidikanDto) {
			Pendidikan pendidikan = convertToEntity(dto);
			if (personRepository.findById(idPerson).isPresent()) {
				Person person = personRepository.findById(idPerson).get();
				pendidikan.setPerson(person);
			}
			pendidikanRepository.save(pendidikan);
		}
		return pendidikanDto;
	}

	private Pendidikan convertToEntity(PendidikanDto dto) {
		Pendidikan pendidikan = new Pendidikan();
		pendidikan.setJenjang(dto.getJenjang());
		pendidikan.setInstitusi(dto.getInstitusi());
		pendidikan.setMasuk(dto.getMasuk());
		pendidikan.setLulus(dto.getLulus());
		return pendidikan;
	}

//	@Autowired
//	PendidikanWrapper pendidikanWrapper;

//	@Override
//	public Pendidikan savePendidikan(List<PendidikanDto> pen) {
//		
////		for (PendidikanDto pendidikan1 : pendidikanWrapper.getPendidikan()) {
////			Pendidikan entity = convertToEntity(pendidikan, idPerson);
////			repository.save(pendidikan1);;
////		}
//		repository.save(pendidikan);
//		return pendidikan;
//	}

//	@Override
//	public Pendidikan insertDataPendidikan(Pendidikan pendidikan) {
//		Pendidikan entity= repository.save(pendidikan);
//		entity.getPerson()
//		return null;
//	}

}
