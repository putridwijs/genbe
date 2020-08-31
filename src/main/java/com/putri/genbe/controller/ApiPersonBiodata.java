package com.putri.genbe.controller;

import java.time.Year;
import java.util.Calendar;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.putri.genbe.dto.PersonBioPendidikanDto;
import com.putri.genbe.dto.PersonBiodataDto;
import com.putri.genbe.dto.Response;
import com.putri.genbe.entity.Biodata;
import com.putri.genbe.entity.Person;
import com.putri.genbe.repository.BiodataRepository;
import com.putri.genbe.repository.PersonRepository;
import com.putri.genbe.service.PersonBiodataService;

@RestController
@RequestMapping("/person")
public class ApiPersonBiodata {

	@Autowired
	private PersonBiodataService personBiodataService;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private PersonRepository personRepository;

	@Autowired
	private BiodataRepository biodataRepository;

	@PostMapping
	public Response saveBiodata(@RequestBody PersonBiodataDto dto) {
//		mapping data PersonBiodata ke Biodata
		Biodata biodata = modelMapper.map(dto, Biodata.class);
//		mapping data PersonBiodataDto ke Person
		Person person = modelMapper.map(dto, Person.class);
//		set person
		biodata.setPerson(person);
		Integer panjangNik = dto.getNik().length();
		if (panjangNik != 16) {
			return status(false, "data gagal masuk, jumlah digit nik tidak sama dengan 16");
		} else if (calculateAge(biodata) < 30) {
			return status(false, "data gagal masuk, umur kurang dari 30 tahun");
		} else if (panjangNik != 16 && calculateAge(biodata) < 30) {
			return status(false,
					"data gagal masuk, jumlah digit nik tidak sama dengan 16 dan umur kurang dari 30 tahun");
		} else {
			personBiodataService.saveBiodataToPerson(biodata);
			return status(true, "data berhasil masuk");
		}
	}

	@GetMapping("/{nik}")
	public PersonBioPendidikanDto dataPerson(@PathVariable String nik) {
		Person person = personRepository.findByNik(nik);
		Biodata biodata = (Biodata) biodataRepository.findAllByPerson(person);
		PersonBioPendidikanDto dto = new PersonBioPendidikanDto();
		dto.setNik(biodata.getPerson().getNik());
		dto.setName(biodata.getPerson().getName());
		dto.setAddress(biodata.getPerson().getAddress());
		dto.setHp(biodata.getHp());
		dto.setTgl(biodata.getTgl());
		dto.setTempatLahir(biodata.getTempatLahir());
		Integer umur = calculateAge(biodata);
		dto.setUmur(umur);
//		String pendidikan_terakhir = 
//		dto.setPendidikan_terakhir(pendidikan_terakhir);
		return dto;
	}

	private Response status(Boolean status, String message) {
		Response response = new Response();
		if (status == false) {
			response.setStatus("false");
			response.setMessage(message);
		} else {
			response.setStatus("true");
			response.setMessage(message);
		}
		return response;
	}

	private Integer calculateAge(Biodata biodata) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(biodata.getTgl());
		Integer age = Year.now().getValue() - calendar.get(Calendar.YEAR);
		return age;
	}

//	private PersonBiodataDto mapBiodataToPersonDto(Biodata biodata) {
//		PersonBiodataDto personDto = modelMapper.map(biodata, PersonBiodataDto.class);
//
//		modelMapper.map(biodata.getPerson(), personDto);
//
//		return personDto;
//	}
//
//	private PersonBiodataDto convertToDto(Biodata biodata) {
//		PersonBiodataDto dto = new PersonBiodataDto();
//		dto.setHp(biodata.getHp());
//		dto.setName(biodata.getPerson().getName());
//		dto.setNik(biodata.getPerson().getNik());
//		dto.setAddress(biodata.getPerson().getAddress());
//		dto.setTgl(biodata.getTgl());
//		dto.setTempatLahir(biodata.getTempatLahir());
//		return dto;
//	}

}
