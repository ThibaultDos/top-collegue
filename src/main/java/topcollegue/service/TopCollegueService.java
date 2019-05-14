package topcollegue.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import topcollegue.TopCollegue;
import topcollegue.repos.TopCollegueRepository;
import topcollegue.repos.VotesRepository;
import topcollegue.vote.VoteType;
import topcollegue.vote.Votes;
import topcollegue.vote.exceptions.VoteException;

@Service
public class TopCollegueService {

	@Autowired
	TopCollegueRepository topCoolRepo;

	@Autowired
	private VotesRepository votesRepo;

	public TopCollegue trouverParEmail(String email) {
		return this.topCoolRepo.findByEmail(email)
				.orElseThrow(() -> new UsernameNotFoundException("Aucun collègue trouvé"));
	}

	public List<Votes> listerLesVotes() {
		return this.votesRepo.findAll();
	}

	public List<TopCollegue> listerTopCollegues() {
		return this.topCoolRepo.findAll();
	}

	public List<TopCollegue> listerTousLesColleguesParCoolitude() {
		return this.topCoolRepo.findAll(Sort.by("coolitudeMeter"));
	}

	public void updateCoolMeter(String auteur, String destinataire, VoteType choix) throws VoteException {

		if (auteur.equals(destinataire)) {
			throw new VoteException();
		} else {
			listerLesVotes().forEach(vote -> {
				TopCollegue collegueCool = trouverParEmail(destinataire);
				if (choix == VoteType.AIMER) {
					collegueCool.setCoolitudeMeter(collegueCool.getCoolitudeMeter() + 111);
					this.topCoolRepo.save(collegueCool);
				} else if (choix == VoteType.DETESTER) {
					collegueCool.setCoolitudeMeter(collegueCool.getCoolitudeMeter() - 11);
					this.topCoolRepo.save(collegueCool);
				}
			});
		}
	}
}
