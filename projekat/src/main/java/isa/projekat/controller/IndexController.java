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
		}

		return "index";
	}

	@RequestMapping("/NeregistrovaniKorisnici/login")
	public String login() {
		return "NeregistrovaniKorisnici/login";
	}

	@GetMapping("/Neregistro	vaniKorisnici/cinemasPage")
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
	
	@GetMapping("Posetioci/settings")
	public String settingsPage() {
		return "Posetioci/settings";
	}
	
	@GetMapping("NeregistrovaniKorisnici/registration")
	public String registration() {
		return "NeregistrovaniKorisnici/registration";
	}
	
	@GetMapping("/AdminFanZone/adminUsedAd")
	public String adminUsedAd() {
		return "AdminFanZone/adminUsedAd";
	}
	
	@GetMapping("/AdminFanZone/updateOfficialAd")
	public String updateOfficialAd() {
		return "AdminFanZone/updateOfficialAd";
	}
	
	@GetMapping("/Posetioci/polovniOglasi")
	public String polovniOglasi() {
		return "Posetioci/polovniOglasi";
	}
	
	@GetMapping("/Posetioci/createNewAd")
	public String createNewAd() {
		return "Posetioci/createNewAd";
	}
	
	@GetMapping("/Posetioci/myReservations")
	public String myReservations() {
		return "Posetioci/myReservations";
	}
	
	@GetMapping("/AdminPozoristaBioskopi/createNewMovie")
	public String createNewMovie() {
		return "AdminPozoristaBioskopi/createNewMovie";
	}
	
	@GetMapping("NeregistrovaniKorisnici/emailConfirm")
	public String emailConfirm() {
		return "NeregistrovaniKorisnici/emailConfirm";
	}
	
	@GetMapping("Posetioci/friends")
	public String friends() {
		return "Posetioci/friends";
	}
	
	@GetMapping("Posetioci/makeBid")
	public String makeBid() {
		return "Posetioci/makeBid";
	}
	
	@GetMapping("Posetioci/dodajPrijatelje")
	public String addFriends() {
		return "Posetioci/dodajPrijatelje";
	}
	
	@GetMapping("Posetioci/chooseBid")
	public String chooseBid() {
		return "Posetioci/chooseBid";
	}
	
	@GetMapping("/AdminPozoristaBioskopi/updateMovie")
	public String updateMovie() {
		return "AdminPozoristaBioskopi/updateMovie";
	}
	
	@GetMapping("Posetioci/cinemaDetails")
	public String posetilacCinemaDetails() {
		return "Posetioci/cinemaDetails";
	}
	
	@GetMapping("Posetioci/cinemasPage")
	public String posetilaccinemasPage() {
		return "Posetioci/cinemasPage";
	}
	
	@GetMapping("Posetioci/cinemaRepertoar")
	public String posetilacCinemaRepertoar() {
		return "Posetioci/cinemaRepertoar";
	}
	
	@GetMapping("AdminPozoristaBioskopi/updateCinema")
	public String updateCinema() {
		return "AdminPozoristaBioskopi/updateCinema";
	}
	
	@GetMapping("/AdminPozoristaBioskopi/updateAuditorium")
	public String updateAuditorium() {
		return "AdminPozoristaBioskopi/updateAuditorium";
	}
	
	@GetMapping("/Posetioci/changePassword")
	public String changePassword() {
		return "Posetioci/changePassword";
	}
	
	@GetMapping("/Posetioci/reserveMovie")
	public String reserveMovie() {
		return "Posetioci/reserveMovie";
	}
	
	@GetMapping("Posetioci/theatresPage")
	public String theatresPage() {
		return "Posetioci/theatresPage";
	}
	
	@GetMapping("AdminFanZone/adminSettings")
	public String adminSettings() {
		return "AdminFanZone/adminSettings";
	}
	
	@GetMapping("AdminSistema/createNewCinema")
	public String createNewCinema() {
		return "AdminSistema/createNewCinema";
	}
	
	@GetMapping("AdminSistema/cinemasAdmin")
	public String cinemasAdmin() {
		return "AdminSistema/cinemasAdmin";
	}
	
	@GetMapping("AdminSistema/cinemasAdminView")
	public String cinemasAdminView() {
		return "AdminSistema/cinemasAdminView";
	}
}
