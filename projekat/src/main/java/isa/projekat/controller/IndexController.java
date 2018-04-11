package isa.projekat.controller;

import javax.servlet.http.HttpSession;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@EnableAutoConfiguration
public class IndexController {

	@RequestMapping("/")
	public String index(HttpSession session) {
		return "index";
	}
	
	@RequestMapping("/index")
	public String indexLogOut(HttpSession session) {
		if(session != null) {
			session.invalidate();
			System.out.println("Sesija nukovana.");
		}

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
	
	@GetMapping("/AdminPozoristaBioskopi/cinemasPage")
	public String AdminCinema() {
		return "AdminPozoristaBioskopi/cinemasPage";
	}
	
	@GetMapping("/AdminPozoristaBioskopi/cinemaDetails")
	public String AdminCinemaDetails() {
		return "AdminPozoristaBioskopi/cinemaDetails";
	}
	
	@GetMapping("/AdminPozoristaBioskopi/cinemaRepertoar")
	public String AdminCinemaRepertoar() {
		return "AdminPozoristaBioskopi/cinemaRepertoar";
	}
	
	@GetMapping("/AdminPozoristaBioskopi/theatresPage")
	public String AdminTheater() {
		return "AdminPozoristaBioskopi/theatresPage";
	}
	
	@GetMapping("/AdminPozoristaBioskopi/theaterDetails")
	public String AdminTheaterDetails() {
		return "AdminPozoristaBioskopi/theaterDetails";
	}
	
	@GetMapping("/AdminPozoristaBioskopi/theaterRepertoar")
	public String AdminTheaterRepertoar() {
		return "AdminPozoristaBioskopi/theaterRepertoar";
	}
	
	@GetMapping("/AdminPozoristaBioskopi/homePage")
	public String PozBioHomePage() {
		return "AdminPozoristaBioskopi/homePage";
	}
	
	@GetMapping("/NeregistrovaniKorisnici/cinemaDetails")
	public String cinemaDetails() {
		return "NeregistrovaniKorisnici/cinemaDetails";
	}
	
	@GetMapping("/NeregistrovaniKorisnici/theaterDetails")
	public String theaterDetails() {
		return "NeregistrovaniKorisnici/theaterDetails";
	}
	
	@GetMapping("/AdminFanZone/createNewOfficialAd")
	public String createNewOfficialAd() {
		return "AdminFanZone/createNewOfficialAd";
	}
}
