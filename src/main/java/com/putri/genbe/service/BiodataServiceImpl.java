package com.putri.genbe.service;

import java.time.LocalDate;
import java.time.Year;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.putri.genbe.entity.Biodata;
import com.putri.genbe.repository.BiodataRepository;
import com.putri.genbe.repository.PersonRepository;

@Transactional
@Service
public class BiodataServiceImpl implements BiodataService {

	@Autowired
	PersonRepository personRepository;

	@Autowired
	BiodataRepository biodataRepository;

	@Override
	public Biodata saveBiodataToPerson(Biodata biodata) throws Exception {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(biodata.getTgl());
		if(biodata.getPerson().getNik().length() != 16) {
			throw new Exception("data gagal masuk, jumlah digit nik tidak sama dengan 16");
		}
		if(Year.now().getValue() - calendar.get(Calendar.YEAR) < 30) {
			throw new Exception("data gagal masuk, umur kurang dari 30 tahun");
		}
		
		personRepository.save(biodata.getPerson());
		biodataRepository.save(biodata);
		return biodata;
	}

	@Override
	public Biodata createResponse(Biodata biodata) {
		
		
//		if (biodata) {
		return null;	
		}// TODO Auto-generated method stub
//		

	@Override
	public Integer calculateAge(LocalDate birthDate, LocalDate currentDate) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
