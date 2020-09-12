package com.putri.genbe.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.putri.genbe.dto.DataLengkapDto;
import org.modelmapper.ModelMapper;
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
import com.putri.genbe.entity.Pendidikan;
import com.putri.genbe.repository.PendidikanRepository;
import com.putri.genbe.repository.PersonRepository;
import com.putri.genbe.service.PendidikanService;

@RestController
@RequestMapping("/api/pendidikan")
public class ApiPendidikan {

	@Autowired
	private PendidikanService pendidikanService;
	
	@Autowired
	private PersonRepository personRepository;
	
	@Autowired
	private PendidikanRepository pendidikanRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@GetMapping
	public List<PendidikanDto> get(){
		List<Pendidikan> list = pendidikanRepository.findAll();
		List<PendidikanDto> dtos = list.stream().map(pendidikan -> mapPendidikanToDTO(pendidikan)).collect(Collectors.toList());
		return dtos;
	}

	@GetMapping ("/data")
	public List<DataLengkapDto> getData(){
		List<Pendidikan> list = pendidikanRepository.findAll();
		List<DataLengkapDto> dtoList = list.stream().map(pendidikan -> mapPendidikanToDataDto(pendidikan)).collect(Collectors.toList());
		return  dtoList;
	}

//	@GetMapping("/{idPerson}")
//	public List<DataLengkapDto> getById(@PathVariable Integer idPerson){
//		List<Pendidikan> list = pendidikanRepository.findAllByPerson(idPerson);
//		List<DataLengkapDto> dtos = list.stream().map(pendidikan -> mapPendidikanToDTO(pendidikan)).collect(Collectors.toList());
//		return dtos;
//	}


//	@GetMapping("/{idPerson}")
//	public List<Object> getById(@PathVariable Integer idPerson){
//		List<Object> objects = new ArrayList<>();
//		
//		List<Pendidikan> list = pendidikanRepository.findAll();
//		List<PendidikanDto> dtos = list.stream().map(pendidikan -> mapPendidikanToDTO(pendidikan)).collect(Collectors.toList());
//		return dtos;
//	}
	
	@PostMapping
	public Response insert(@RequestBody List<PendidikanDto> pendidikanDto, @RequestParam Integer idPerson) {
		if (personRepository.findById(idPerson).isPresent()) {
			pendidikanService.savePendidikan(pendidikanDto, idPerson);
			return status(true, "data berhasil masuk");
		} else {
			return status(false, "data gagal masuk");
		}
	}
	
	private PendidikanDto mapPendidikanToDTO(Pendidikan pendidikan) {
		PendidikanDto dto = modelMapper.map(pendidikan, PendidikanDto.class);
		dto.setIdPerson(pendidikan.getPerson().getIdPerson());
		return dto;
	}

	private DataLengkapDto mapPendidikanToDataDto (Pendidikan pendidikan) {
		DataLengkapDto dto = modelMapper.map(pendidikan, DataLengkapDto.class);
		dto.setIdPerson(pendidikan.getPerson().getIdPerson());
		dto.setNama(pendidikan.getPerson().getNama());
		return dto;
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
