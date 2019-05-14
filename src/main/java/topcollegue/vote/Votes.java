package topcollegue.vote;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;

import topcollegue.TopCollegue;

@Entity
public class Votes {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "ID_auteur")
	private TopCollegue auteur;

	@ManyToOne
	@JoinColumn(name = "ID_destinataire")
	private TopCollegue destinataire;

	@Column
	private VoteType vote;

	@Column
	private LocalDateTime voteDate;

	public Votes(TopCollegue auteur, TopCollegue destinataire, VoteType vote) {
		this.auteur = auteur;
		this.destinataire = destinataire;
		this.vote = vote;
	}

	public Votes() {
	}
	
	@PrePersist
	public void avantInsertion() {
		this.voteDate = LocalDateTime.now();
	}

	public TopCollegue getAuteur() {
		return auteur;
	}

	public void setAuteur(TopCollegue auteur) {
		this.auteur = auteur;
	}

	public TopCollegue getDestinataire() {
		return destinataire;
	}

	public void setDestinataire(TopCollegue destinataire) {
		this.destinataire = destinataire;
	}

	public VoteType getVote() {
		return vote;
	}

	public void setVote(VoteType vote) {
		this.vote = vote;
	}

	public LocalDateTime getVoteDate() {
		return voteDate;
	}
}
