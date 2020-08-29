package com.putri.genbe.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//import com.putri.genbe.dto.PendidikanDto;
import com.putri.genbe.entity.Pendidikan;
import com.putri.genbe.repository.PendidikanRepository;
import com.putri.genbe.repository.PersonRepository;

@Service
public class PendidikanServiceImpl implements PendidikanService {

	@Autowired
	PendidikanRepository repository;
	
	@Autowired
	PersonRepository personRepository;

	

	@Override
	public Pendidikan savePendidikan(Pendidikan pendidikan) {
		repository.save(pendidikan);
		return pendidikan;
	}



//	@Override
//	public Pendidikan insertDataPendidikan(Pendidikan pendidikan) {
//		Pendidikan entity= repository.save(pendidikan);
//		entity.getPerson()
//		return null;
//	}

}
