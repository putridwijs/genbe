package com.putri.genbe.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.putri.genbe.dto.PendidikanDto;
import com.putri.genbe.dto.PendidikanDto1;
import com.putri.genbe.dto.PersonDto;
import com.putri.genbe.entity.Pendidikan;
import com.putri.genbe.entity.PendidikanWrapper;
import com.putri.genbe.entity.Person;
import com.putri.genbe.repository.PendidikanRepository;
import com.putri.genbe.repository.PersonRepository;
import com.putri.genbe.service.PendidikanService;

@RestController
@RequestMapping("/pendidikan")
public class ApiPendidikan {
	@Autowired
	private PendidikanRepository repository;

	@Autowired
	private PersonRepository personRepository;

	@Autowired
	private PendidikanService pendidikanService;

//	@PostMapping ("/{id}")
//	public List<String> insert(@PathVariable Integer id ,@RequestBody PendidikanWrapper pendidikanWrapper, PendidikanDto1 pendidikanDto1) {
//		PendidikanDto1 dto = convertToDto1(repository.findById(id).get());
//		dto.setIdPerson(pendidikanDto1.getIdPerson());
//		if (personRepository.findById(id).isPresent()) {
//			List<String> response = new ArrayList<String>();
//			for (PendidikanDto pendidikan : pendidikanWrapper.getPendidikan()) {
//				Pendidikan entity = convertToEntity(pendidikan);
//				pendidikanService.savePendidikan(entity);
//			}
//			return response;
//		}
////		if (personRepository.findById(id).isPresent()) {
//		List<String> response = new ArrayList<String>();
//		for (PendidikanDto pendidikan : pendidikanWrapper.getPendidikan()) {
//			Pendidikan entity = convertToEntity(pendidikan);
//			pendidikanService.savePendidikan(entity);
//			
//		}
//		
//		return response;
////		}
////		return null;
//	}
//	@PostMapping("/{idPerson}")
//	public List<Pendidikan> insert(@PathVariable Integer idPerson, @RequestBody List<PendidikanDto> pdto) {
//		List<Pendidikan> pdtList = pdto.stream().map(a -> convertToEntity(a, idPerson)).collect(Collectors.toList());
//		pdtList.stream().forEach(b -> repository.save(b));
//		return pdtList;
//	}

	@PostMapping("/{idPerson}")
	public List<String> insert(@PathVariable Integer idPerson, @RequestBody PendidikanWrapper pendidikanWrapper) {
		if (personRepository.findById(idPerson).isPresent()) {
			List<String> response = new ArrayList<String>();
//			Pendidikan p = convertIdToEntity(idPerson);
//			repository.save(p);
			for (PendidikanDto pendidikan : pendidikanWrapper.getPendidikan()) {
				Pendidikan entity = convertToEntity(pendidikan, idPerson);
				pendidikanService.savePendidikan(entity);
			}
			return response;
		}
		return null;
	}

//	@PostMapping ()
//	public List<String> insert(@RequestBody PendidikanWrapper pendidikanWrapper) {
////		if (personRepository.findById(id).isPresent()) {
//		List<String> response = new ArrayList<String>();
//		for (PendidikanDto pendidikan : pendidikanWrapper.getPendidikan()) {
//			Pendidikan entity = convertToEntity(pendidikan);
//			pendidikanService.savePendidikan(entity);
//			
//		}
//		
//		return response;
////		}
////		return null;
//	}

	private Pendidikan convertToEntity(PendidikanDto dto, Integer idPerson) {
		Pendidikan pendidikan = new Pendidikan();
		pendidikan.setJenjang(dto.getJenjang());
		pendidikan.setInstitusi(dto.getInstitusi());
		pendidikan.setMasuk(dto.getMasuk());
		pendidikan.setLulus(dto.getLulus());

		if (personRepository.findById(idPerson).isPresent()) {
			Person person = personRepository.findById(idPerson).get();
			pendidikan.setPerson(person);
		}
		return pendidikan;
	}

//	private Pendidikan convertIdToEntity(Integer idPerson) {
//		Pendidikan pendidikan = new Pendidikan();
//		if (personRepository.findById(idPerson).isPresent()) {
//			Person person = personRepository.findById(idPerson).get();
//			pendidikan.setPerson(person);
//		}
//		return pendidikan;
//	}

	private PendidikanDto convertToDto(Pendidikan pendidikan) {
		PendidikanDto dto = new PendidikanDto();
		dto.setJenjang(pendidikan.getJenjang());
		dto.setInstitusi(pendidikan.getInstitusi());
		dto.setMasuk(pendidikan.getMasuk());
		dto.setLulus(pendidikan.getLulus());
//		dto.setIdPerson(pendidikan.getPerson().getIdPerson());
		return dto;
	}

	private PendidikanDto1 convertToDto1(Pendidikan pendidikan) {
		PendidikanDto1 dto = new PendidikanDto1();
		dto.setIdPerson(pendidikan.getPerson().getIdPerson());
		return dto;
	}

}
