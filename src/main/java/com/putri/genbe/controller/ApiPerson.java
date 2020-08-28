package com.putri.genbe.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.Response;

import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jackson.JsonObjectDeserializer;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.io.JsonEOFException;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.google.common.net.HttpHeaders;
import com.putri.genbe.dto.PersonDto;
import com.putri.genbe.entity.Biodata;
import com.putri.genbe.entity.Person;
import com.putri.genbe.repository.BiodataRepository;
import com.putri.genbe.repository.PersonRepository;
import com.putri.genbe.service.BiodataService;

import springfox.documentation.spring.web.json.Json;

@RestController
//@RequestMapping()
public class ApiPerson {
	@Autowired
	private PersonRepository personRepository;

	@Autowired
	private BiodataRepository biodataRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private BiodataService biodataService;

//	@GetMapping("/{nik}")
//	public PersonDto getDataPerson(@PathVariable String nik) {
//		Biodata biodata = biodataRepository.cariNik(nik);
//		PersonDto personDto = new PersonDto();
//		personDto.setNik(biodata.getPerson().getNik());
//		personDto.setName(biodata.getPerson().getName());
//		personDto.setAddress(biodata.getPerson().getAddress());
//		personDto.setHp(biodata.getHp());
//		personDto.setTgl(biodata.getTgl());
//		personDto.setTempatLahir(biodata.getTempatLahir());
//		
//		return personDto;
//		
//		
//	}

//	

//	@PostMapping
//	public ResponseEntity<Void> saveBiodata(@RequestBody PersonDto personDto) {
//		Biodata biodata = modelMapper.map(personDto, Biodata.class);
//		Person person = modelMapper.map(personDto, Person.class);
//		biodata.setPerson(person);
//		biodataService.saveBiodataMaterPerson(biodata);
//		Integer panjangNik = personDto.getNik().length();
//		PersonDto personDtoDB = mapBiodataToPersonDto(biodata);
//		if (panjangNik == 16) {
//			String msg = "data berhasil masuk";
//			org.springframework.http.HttpHeaders header = new org.springframework.http.HttpHeaders();
//			header.add("desc", "true");
//			return new ResponseEntity<Void>(header, HttpStatus.OK);
//		} else {
//			String msg = "data tidak masuk";
//			org.springframework.http.HttpHeaders header = new org.springframework.http.HttpHeaders();
//			header.add("desc", "false");
//			return new ResponseEntity<Void>(header, HttpStatus.BAD_REQUEST);
//		}
////		return personDtoDB;
//	}

	@PostMapping // pake transactional
	public PersonDto saveBiodata(@RequestBody PersonDto personDto) {
		try {
			Biodata biodata = modelMapper.map(personDto, Biodata.class);
			Person person = modelMapper.map(personDto, Person.class);
			biodata.setPerson(person);
			biodataService.saveBiodataToPerson(biodata);
			PersonDto personDtoDB = mapBiodataToPersonDto(biodata);
			return personDtoDB;
		} catch (Exception e) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("status", false);
			map.put("message", e.getMessage());
			return null;
		}
	}

	private PersonDto mapBiodataToPersonDto(Biodata biodata) {
		PersonDto personDto = modelMapper.map(biodata, PersonDto.class);

		modelMapper.map(biodata.getPerson(), personDto);

		return personDto;
	}

}
