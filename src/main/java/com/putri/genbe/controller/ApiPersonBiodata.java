package com.putri.genbe.controller;

import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;
import java.time.Year;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

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
import com.putri.genbe.dto.ResponseLengkap;
import com.putri.genbe.entity.Pendidikan;
import com.putri.genbe.entity.Person;
import com.putri.genbe.repository.PendidikanRepository;
import com.putri.genbe.repository.PersonRepository;
import com.putri.genbe.service.PersonBiodataService;

@RestController
@RequestMapping("/api/person")
public class ApiPersonBiodata {

	@Autowired
	private PersonBiodataService personBiodataService;

	@Autowired
	private PersonRepository personRepository;

	@Autowired
	private PendidikanRepository pendidikanRepository;
	
	@Autowired
	private ModelMapper modelMapper;

	@GetMapping
	public List<PersonBiodataDto> getListPB(){
		List<Person> list = personRepository.findAll();
		List<PersonBiodataDto> listDto = list.stream().map(person -> mapPBtoPBDto(person)).collect(Collectors.toList());
		return listDto;
	}
	
	@GetMapping("/data/{idPerson}")
	public PersonBiodataDto getPB(@PathVariable Integer idPerson) {
		Person person = personRepository.findById(idPerson).get();
		PersonBiodataDto dto = new PersonBiodataDto();
		dto.setIdPerson(person.getIdPerson());
		dto.setIdBio(person.getBiodata().getIdBio());
		dto.setNik(person.getNik());
		dto.setNama(person.getNama());
		dto.setAlamat(person.getAlamat());
		dto.setNoHp(person.getBiodata().getNoHp());
		dto.setTanggalLahir(person.getBiodata().getTanggalLahir());
		dto.setTempatLahir(person.getBiodata().getTempatLahir());
		return dto;
	}
	
	@GetMapping("/semuadata")
	public List<PersonBioPendidikanDto> get(){
		List<Person> list = personRepository.findAll();
		List<PersonBioPendidikanDto> dtos = list.stream().map(person -> mapPBPtoPBP(person)).collect(Collectors.toList());
		return dtos;
	}
	
	@GetMapping("/{nik}")
	public List<Object> get(@PathVariable String nik) {
		List<Object> object = new ArrayList<>();
		ResponseLengkap lengkap = new ResponseLengkap();
		Response respon = new Response();
		if (nik.length() == 16) {
			if (personRepository.findByNikLike(nik).isEmpty() == false) {
				Person person = personRepository.findByNikLike(nik).get(0);
				PersonBioPendidikanDto dto = convertToDTo(person);
				lengkap.setStatus("true");
				lengkap.setMessage("success");
				lengkap.setData(dto);
				object.add(lengkap);
			} else {
				respon.setStatus("false");
				respon.setMessage("data gagal masuk, nik " + nik + " tidak ditemukan");
				object.add(respon);
			}
		} else {
			respon.setStatus("false");
			respon.setMessage("data gagal masuk, jumlah digit nik tidak sama dengan 16");
			object.add(respon);
		}
		return object;
	}
	
//	@GetMapping("/data")
//	public List<Object> getData() {
//		List<Object> object = new ArrayList<>();
//////		List<PersonBioPendidikanDto> bioPendidikanDtos = pendidikanRepository.findAll();
////		List<Pendidikan> list = pendidikanRepository.findAll();
////		List<Person> person = personRepository.findAll();
////		List<Object> objects = convertToDToList(list);
//////		objects.add(list);
////		object.add(objects);
//////		Date dob = person.getBiodata().getTanggalLahir();
////		LocalDate today = LocalDate.now();
////		LocalDate birthDate = dob.toLocalDate();
////		Period umur = Period.between(birthDate, today);
////		PersonBioPendidikanDto dto = new PersonBioPendidikanDto();
////		List<PersonBioPendidikanDto> list2 = list.stream().map(pendidikan -> mapPBPtoPBP(pendidikan)).collect(Collectors.toList());
////		object.add(list2);
////		return object;
//		List<Pendidikan> list = pendidikanRepository.findAll();
//		
////		List<Person> person = personRepository.findAll();
//		PersonBioPendidikanDto dto = (PersonBioPendidikanDto) convertToDTo(list);
//		object.add(dto);
//		return object;
////		List<PersonBioPendidikanDto> dtos = list.stream().map(pendidikan -> mapPBPtoPBP(pendidikan)).collect(Collectors.toList());
////		return dtos;
//	}

	@PostMapping
	public Response saveBiodata(@RequestBody PersonBiodataDto dto) {
		Date dob = dto.getTanggalLahir();
		LocalDate today = LocalDate.now();
		LocalDate birthDate = dob.toLocalDate();
		Period p = Period.between(birthDate, today);
		Integer panjangNik = dto.getNik().length();
		if (panjangNik != 16 && (p.getYears() < 30)) {
			return status(false,
					"data gagal masuk, jumlah digit nik tidak sama dengan 16 dan umur kurang dari 30 tahun");
		} else if (panjangNik != 16) {
			return status(false, "data gagal masuk, jumlah digit nik tidak sama dengan 16");
		} else if (p.getYears() < 30) {
			return status(false, "data gagal masuk, umur kurang dari 30 tahun");
		} else {
			personBiodataService.saveBiodataToPerson(dto);
			return status(true, "data berhasil masuk");
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
	
	private PersonBioPendidikanDto mapPBPtoPBP(Person person) {
		PersonBioPendidikanDto dto = modelMapper.map(person, PersonBioPendidikanDto.class);
		modelMapper.map(person.getBiodata(), dto);
		Date dob = (person.getBiodata().getTanggalLahir());
		LocalDate today = LocalDate.now();
		LocalDate birthDate = dob.toLocalDate();
		Period umur = Period.between(birthDate, today);
		dto.setUmur(Integer.toString(umur.getYears()));
		dto.setPendidikanTerakhir(pendidikanRepository.cariJenjangPendidikan(person.getIdPerson()));
		return dto;
	}
	
	private PersonBiodataDto mapPBtoPBDto(Person person) {
		PersonBiodataDto dto = modelMapper.map(person, PersonBiodataDto.class);
		modelMapper.map(person.getBiodata(), dto);
		return dto;
	}

//	private String calculateAge(per date) {
//		Date dob = dto.getTanggalLahir();
//		LocalDate today = LocalDate.now();
//		LocalDate birthDate = dob.toLocalDate();
//		Period p = Period.between(birthDate, today);
//		Calendar calendar = Calendar.getInstance();
//		calendar.setTime(date);
//		Integer age = Year.now().getValue() - calendar.get(Calendar.YEAR);
//		String age1 = Integer.toString(age);
//		return age1;
//	}
	
	private List<Object> convertToDToList(List<Pendidikan> list) {
		List<Object> object = new ArrayList<>();
		Date dob = ((Person) list).getBiodata().getTanggalLahir();
		LocalDate today = LocalDate.now();
		LocalDate birthDate = dob.toLocalDate();
		Period umur = Period.between(birthDate, today);
		PersonBioPendidikanDto dto = new PersonBioPendidikanDto();
		dto.setNik(((PersonBioPendidikanDto) list).getNik());
		dto.setNama(((PersonBioPendidikanDto) list).getNama());
		dto.setAlamat(((PersonBioPendidikanDto) list).getAlamat());
		dto.setNoHp(((Person) list).getBiodata().getNoHp());
		dto.setTanggalLahir(((Person) list).getBiodata().getTanggalLahir());
		dto.setTempatLahir(((Person) list).getBiodata().getTempatLahir());
		dto.setUmur(Integer.toString(umur.getYears()));
		dto.setPendidikanTerakhir(pendidikanRepository.cariJenjangPendidikan(((Person) list).getIdPerson()));
		object.add(dto);
		return object;
	}
	
	private PersonBioPendidikanDto convertToDTo(Person person) {
		Date dob = person.getBiodata().getTanggalLahir();
		LocalDate today = LocalDate.now();
		LocalDate birthDate = dob.toLocalDate();
		Period umur = Period.between(birthDate, today);
		PersonBioPendidikanDto dto = new PersonBioPendidikanDto();
		dto.setNik(person.getNik());
		dto.setNama(person.getNama());
		dto.setAlamat(person.getAlamat());
		dto.setNoHp(person.getBiodata().getNoHp());
		dto.setTanggalLahir(person.getBiodata().getTanggalLahir());
		dto.setTempatLahir(person.getBiodata().getTempatLahir());
		dto.setUmur(Integer.toString(umur.getYears()));
		dto.setPendidikanTerakhir(pendidikanRepository.cariJenjangPendidikan(person.getIdPerson()));
		return dto;
	}

}
