package com.putri.genbe.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.putri.genbe.dto.PendidikanDto;
import com.putri.genbe.dto.Response;
import com.putri.genbe.entity.Pendidikan;
import com.putri.genbe.entity.PendidikanWrapper;
import com.putri.genbe.entity.Person;
import com.putri.genbe.repository.PersonRepository;
import com.putri.genbe.service.PendidikanService;

@RestController
@RequestMapping("/pendidikan")
public class ApiPendidikan {

	@Autowired
	private PersonRepository personRepository;

	@Autowired
	private PendidikanService pendidikanService;

//	@PostMapping("/{idPerson}")
//	public Response insert(@PathVariable Integer idPerson,  @RequestBody PendidikanWrapper pendidikanWrapper) {
//		if (personRepository.findById(idPerson).isPresent()) {
//			for (PendidikanDto pendidikan1 : pendidikanWrapper.getPendidikan()) {
//				Pendidikan entity = convertToEntity(pendidikan1, idPerson);
//				pendidikanService.savePendidikan(entity);
//			}
//				return status(true, "data berhasil masuk");
//			}
//		return status(false, "data gagal masuk");
//	}

	@PostMapping
	public Response insert(@RequestBody List<PendidikanDto> pendidikanDto, @RequestParam Integer idPerson) {
		try {
			pendidikanService.savePendidikan(pendidikanDto, idPerson);
			return status(true, "data berhasil masuk");
		} catch (Exception e) {
			return status(false, "data gagal masuk");
		}
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

}
