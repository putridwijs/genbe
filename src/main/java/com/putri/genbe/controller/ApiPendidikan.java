package com.putri.genbe.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.putri.genbe.dto.PendidikanDto;
import com.putri.genbe.dto.Response;
import com.putri.genbe.service.PendidikanService;

@RestController
@RequestMapping("/pendidikan")
public class ApiPendidikan {

	@Autowired
	private PendidikanService pendidikanService;

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
