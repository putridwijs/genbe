package com.putri.genbe.controller.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/")
public class BaseMvcController {
//  dashboard
	@GetMapping("dashboard")
	public String dashboard() {
		return "dashboard/dataperson";
	}
	
	@GetMapping("person")
	public String person() {
		return "dashboard/dataperson";
	}
	
	@GetMapping("pendidikan")
	public String pendidikan() {
		return "dashboard/datapendidikan";
	}
	
	@GetMapping("person1")
	public String get() {
		return "person";
	}
}
