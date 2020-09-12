package com.putri.genbe.controller.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/")
public class BaseMvcController {
	@GetMapping("dashboard")
	public String dashboard() {
		return "dashboard/dashboard";
	}

	@GetMapping("datapendidikan")
	public String person() {
		return "pendidikan/datapendidikan";
	}
	
	@GetMapping("person")
	public String dataperson() {
		return "person/person";
	}
	
	@GetMapping("pendidikan/{idPerson}")
	public String pendidikanById(Model model, @PathVariable Integer idPerson) {
		model.addAttribute("idPerson", idPerson);
		return "pendidikan/pendidikan";
	}

	@GetMapping("pendidikan")
	public String pendidikan() {
		return "pendidikan/pendidikan";
	}

	@GetMapping("getdata")
	public String get() {
		return "person/get";
	}


}
