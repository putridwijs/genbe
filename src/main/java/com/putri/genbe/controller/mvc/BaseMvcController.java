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
		return "dashboard/index";
	}

	// dashboard
	@GetMapping("biodata")
	public String biodata() {
		return "biodata/index";
	}
}
