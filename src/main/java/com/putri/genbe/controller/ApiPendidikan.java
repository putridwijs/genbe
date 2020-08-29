package com.putri.genbe.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
	
	@PostMapping("/{idPerson}")
	public Response insert(@PathVariable Integer idPerson,  @RequestBody PendidikanWrapper pendidikanWrapper) {
		if (personRepository.findById(idPerson).isPresent()) {
			for (PendidikanDto pendidikan : pendidikanWrapper.getPendidikan()) {
				Pendidikan entity = convertToEntity(pendidikan, idPerson);
				pendidikanService.savePendidikan(entity);
			}
				return status(true, "data berhasil masuk");
			}
		return status(false, "data gagal masuk");
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

	private Pendidikan convertToEntity(PendidikanDto dto, Integer idPerson) {
		Pendidikan pendidikan = new Pendidikan();
		pendidikan.setJenjang(dto.getJenjang());
		pendidikan.setInstitusi(dto.getInstitusi());
		pendidikan.setMasuk(dto.getMasuk());
		pendidikan.setLulus(dto.getLulus());

		if (personRepository.findById(idPerson).isPresent()) {
			Person person = personRepository.findById(idPerson).get();
			pendidikan.setPerson(person);
		}
		return pendidikan;
	}


}
