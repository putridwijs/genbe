package com.putri.genbe.service;

import javax.transaction.Transactional;

import com.putri.genbe.dto.Response;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.putri.genbe.dto.PersonBiodataDto;
import com.putri.genbe.entity.Biodata;
import com.putri.genbe.entity.Person;
import com.putri.genbe.repository.BiodataRepository;
import com.putri.genbe.repository.PersonRepository;

import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;

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
//		mapping data PersonBiodataDto ke DataPerson
		Person person = modelMapper.map(dto, Person.class);
//		set person
		biodata.setPerson(person);
		personRepository.save(biodata.getPerson());
		biodataRepository.save(biodata);
		return null;
	}

	@Override
	public Response insertDataPerson(PersonBiodataDto personBiodataDto, Person person, Biodata biodata, Response status) {
		Date dob = personBiodataDto.getTanggalLahir();
		LocalDate today = LocalDate.now();
		LocalDate birthDate = dob.toLocalDate();
		Period p = Period.between(birthDate, today);
		String nik = personBiodataDto.getNik();
		Integer panjangNik = personBiodataDto.getNik().length();
		if (panjangNik != 16 && (p.getYears() < 30)) {
			status.setStatus("false");
			status.setMessage("data gagal masuk, jumlah digit nik tidak sama dengan 16 dan umur kurang dari 30 tahun");
			return status;
		} else if (panjangNik != 16) {
			status.setStatus("false");
			status.setMessage("data gagal masuk, jumlah digit nik tidak sama dengan 16");
			return status;
		} else if (p.getYears() < 30) {
			status.setStatus("false");
			status.setMessage("data gagal masuk, jumlah digit nik tidak sama dengan 16");
			return status;
		} else {
			personRepository.save(person);
			biodataRepository.save(biodata);
			status.setStatus("true");
			status.setMessage("data berhasil masuk");
			return status;
		}
	}

}
