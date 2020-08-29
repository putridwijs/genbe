package com.putri.genbe.controller;

import java.time.Year;
import java.util.Calendar;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.putri.genbe.dto.PersonBiodataDto;
import com.putri.genbe.entity.Biodata;
import com.putri.genbe.entity.Person;
import com.putri.genbe.repository.BiodataRepository;
import com.putri.genbe.repository.PersonRepository;

@RestController
@RequestMapping("/person")
public class ApiPerson {
	@Autowired
	private PersonRepository personRepository;

	@Autowired
	private BiodataRepository biodataRepository;

	@Autowired
	private ModelMapper modelMapper;

//	@GetMapping("/{nik}")
//	public PersonBiodataDto get(@PathVariable String nik) {
//		IdPersonDto dto = convertToDto(biodataRepository.findById(nik));
//		PersonBiodataDto dto2 = convertToDto(biodataRepository.findById(nik), 
//				biodataRepository.findAllByPersonIdPerson(dto.getIdPerson()));
//		
//	}

//	@GetMapping("/{nik}")
//	public PersonBiodataDto getDataPerson(@PathVariable String nik) {
//		Biodata biodata = biodataRepository.cariNik(nik);
//		PersonBiodataDto personDto = new PersonBiodataDto();
//		personDto.setNik(biodata.getPerson().getNik());
//		personDto.setName(biodata.getPerson().getName());
//		personDto.setAddress(biodata.getPerson().getAddress());
//		personDto.setHp(biodata.getHp());
//		personDto.setTgl(biodata.getTgl());
//		personDto.setTempatLahir(biodata.getTempatLahir());	
//		return personDto;
//
//	}

//	

	@PostMapping
	public com.putri.genbe.dto.Response saveBiodata(@RequestBody PersonBiodataDto dto) {
		Biodata biodata = modelMapper.map(dto, Biodata.class);
		Person person = modelMapper.map(dto, Person.class);
		biodata.setPerson(person);
		Integer panjangNik = dto.getNik().length();

		if (panjangNik == 16 && calculateAge(biodata) > 30) {
			personRepository.save(biodata.getPerson());
			biodataRepository.save(biodata);
			return status(true, "data berhasil masuk");
		} else {
			return status(false, "data gagal masuk, jumlah digit nik tidak sama dengan 16 atau umur kurang dari 30");
		}
	}

	private com.putri.genbe.dto.Response status(Boolean status, String message) {
		com.putri.genbe.dto.Response response = new com.putri.genbe.dto.Response();
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
