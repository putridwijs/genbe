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
import com.putri.genbe.entity.Pendidikan;
import com.putri.genbe.entity.Person;
import com.putri.genbe.repository.BiodataRepository;
import com.putri.genbe.repository.PendidikanRepository;
import com.putri.genbe.repository.PersonRepository;
import com.putri.genbe.service.PersonBiodataService;

@RestController
@RequestMapping("/person")
public class ApiPersonBiodata {

	@Autowired
	private PersonBiodataService personBiodataService;

	@Autowired
	private PersonRepository personRepository;

	@Autowired
	private BiodataRepository biodataRepository;

	@Autowired
	private PendidikanRepository pendidikanRepository;

	@PostMapping
	public Response saveBiodata(@RequestBody PersonBiodataDto dto) {
		Integer panjangNik = dto.getNik().length();
		if (panjangNik != 16 && calculateAge(dto) < 30) {
			return status(false,
					"data gagal masuk, jumlah digit nik tidak sama dengan 16 dan umur kurang dari 30 tahun");
		} else if (panjangNik != 16) {
			return status(false, "data gagal masuk, jumlah digit nik tidak sama dengan 16");
		} else if (calculateAge(dto) < 30) {
			return status(false, "data gagal masuk, umur kurang dari 30 tahun");
		} else {
			personBiodataService.saveBiodataToPerson(dto);
			return status(true, "data berhasil masuk");
		}
	}

//	@GetMapping("/{nik}")
//	public PersonBioPendidikanDto dataPerson(@PathVariable String nik) {
//		Person person = personRepository.findByNik(nik);
//		Biodata biodata = (Biodata) biodataRepository.findAllByPerson(person);
//		Pendidikan pendidikan = pendidikanRepository.findAllByPerson(person);
//		PersonBioPendidikanDto dto = new PersonBioPendidikanDto();
//		Response status1 = status(true, "succes");
//		dto.setStatus(status1);
//		dto.setNik(biodata.getPerson().getNik());
//		dto.setName(biodata.getPerson().getName());
//		dto.setAddress(biodata.getPerson().getAddress());
//		dto.setHp(biodata.getHp());
//		dto.setTgl(biodata.getTgl());
//		dto.setTempatLahir(biodata.getTempatLahir());
////		Integer umur = calculateAge(biodata);
////		dto.setUmur(umur);
//		String pendidikan_terakhir = pendidikan.getLulus();
//		dto.setPendidikan_terakhir(pendidikan_terakhir);
//		return dto;
//	}

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

	private Integer calculateAge(PersonBiodataDto dto) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(dto.getTgl());
		Integer age = Year.now().getValue() - calendar.get(Calendar.YEAR);
		return age;
	}

}
