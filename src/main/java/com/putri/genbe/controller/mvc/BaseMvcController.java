package com.putri.genbe.controller.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "/")
public class BaseMvcController {
//	@GetMapping("dashboard")
//	public String dashboard() {
//		return "dashboard/dataperson";
//	}
//	
	@GetMapping("datapendidikan")
	public String person() {
		return "pendidikan/datapendidikan";
	}
	
	@GetMapping("person")
	public String dataperson() {
		return "person/person";
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
