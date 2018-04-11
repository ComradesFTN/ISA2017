package isa.projekat.controller;

import javax.servlet.http.HttpSession;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import isa.projekat.domain.Test;
import isa.projekat.domain.Tester;

@Controller
@EnableAutoConfiguration
public class IndexController {

	@RequestMapping("/")
	public String index(HttpSession session) {
		return "index";
	}

	@RequestMapping("/NeregistrovaniKorisnici/login")
	public String login() {
		return "NeregistrovaniKorisnici/login";
	}

	@GetMapping("/NeregistrovaniKorisnici/cinemasPage")
	public String cinema() {
		return "NeregistrovaniKorisnici/cinemasPage";
	}

	@GetMapping("/Posetioci/fanZonaPage")
	public String fanZonaPage() {
		return "Posetioci/fanZonaPage";
	}

	@GetMapping("/AdminFanZone/adminFanZona")
	public String adminFanZona() {
		return "AdminFanZone/adminFanZona";
	}

	@GetMapping("/NeregistrovaniKorisnici/theatresPage")
	public String theatres() {
		return "NeregistrovaniKorisnici/theatresPage";
	}
	
	@GetMapping("Posetioci/homePage")
	public String homePage() {
		return "Posetioci/homePage";
	}

}
