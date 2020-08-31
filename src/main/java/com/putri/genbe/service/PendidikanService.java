package com.putri.genbe.service;

import java.util.List;

import com.putri.genbe.dto.PendidikanDto;

//import org.springframework.http.ResponseEntity;

//import com.putri.genbe.dto.PendidikanDto;
import com.putri.genbe.entity.Pendidikan;

public interface PendidikanService {
//	Pendidikan insertDataPendidikan(Pendidikan pendidikan);
	List<PendidikanDto> savePendidikan(List<PendidikanDto> pendidikanDto, Integer idPerson);
//	ResponseEntity<String> postMapping(){
		
//	}
}
