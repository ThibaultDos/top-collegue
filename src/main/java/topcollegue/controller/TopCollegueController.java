package topcollegue.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import topcollegue.TopCollegue;
import topcollegue.repos.VotesRepository;
import topcollegue.service.TopCollegueService;
import topcollegue.vote.VoteType;
import topcollegue.vote.Votes;

@RestController
@RequestMapping("/coollegues")
public class TopCollegueController {

	@Autowired
	private TopCollegueService topCoolService;

	@Autowired
	private VotesRepository votesRepo;

	@GetMapping("/classement")
	public List<TopCollegue> classementTopCoolegues() {
		return topCoolService.listerTousLesColleguesParCoolitude();
	}

	@GetMapping("/votes")
	public List<Votes> listeDesVotes() {
		return topCoolService.listerLesVotes();
	}

	@PostMapping("/votes")
	public ResponseEntity<Votes> voter(@RequestBody String mailDestinataire, VoteType vote) {

		TopCollegue destinataire = topCoolService.trouverParEmail(mailDestinataire);
		String mailAuteur = SecurityContextHolder.getContext().getAuthentication().getName();
		TopCollegue auteur = topCoolService.trouverParEmail(mailAuteur);
		Votes monVote = new Votes(destinataire, auteur, vote);
		votesRepo.save(monVote);
		return ResponseEntity.status(HttpStatus.OK).body(monVote);
	}

}
