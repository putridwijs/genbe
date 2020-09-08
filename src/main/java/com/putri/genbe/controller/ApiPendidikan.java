package com.putri.genbe.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.putri.genbe.dto.PendidikanDto;
import com.putri.genbe.dto.Response;
import com.putri.genbe.repository.PersonRepository;
import com.putri.genbe.service.PendidikanService;

@RestController
@RequestMapping("/pendidikan")
public class ApiPendidikan {

	@Autowired
	private PendidikanService pendidikanService;
	
	@Autowired
	private PersonRepository personRepository;

	@PostMapping
	public Response insert(@RequestBody List<PendidikanDto> pendidikanDto, @RequestParam Integer idPerson) {
		if (personRepository.findById(idPerson).isPresent()) {
			pendidikanService.savePendidikan(pendidikanDto, idPerson);
			return status(true, "data berhasil masuk");
		} else {
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
