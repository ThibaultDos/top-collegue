package topcollegue;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
public class TopCollegue {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column
	@NotEmpty
	@Size(min = 1)
	private String nom;
	@Column
	@NotEmpty
	@Size(min = 1)
	private String prenoms;
	@Column
	@Email
	private String email;
	@Column
	private String photoUrl;
	@Column
	private Integer coolitudeMeter;

	public String getCoolitude() {
		String inconnu = "Vous n'avez pas encore été évalué ; allez voir vos coolègues et faîtes-en des zamis.";
		String badBoy = "Votre coolitude file un mauvais coton ; seriez-vous vilain ?";
		String osef = "Votre coolitude flirte avec le néant ; vous devriez quémander ou acheter des 'likes' à vos coolègues.";
		String asCoolasCucumber = "Vous êtes aussi cool qu'un concombre ! Vous apporterez bien le petit déj' demain matin pour fêter ça ?!";
		if (coolitudeMeter == null) {
			return inconnu;
		} else if (coolitudeMeter < 0) {
			return badBoy;
		} else if (coolitudeMeter == 0) {
			return osef;
		} else if (coolitudeMeter > 0) {
			return asCoolasCucumber;
		} else {
			return null;
		}
	}

	// constructeur vide obligatoire pour la classe entité
	public TopCollegue() {
	}

	public TopCollegue(@NotEmpty @Size(min = 1) String nom, @NotEmpty @Size(min = 1) String prenoms) {
		this.nom = nom;
		this.prenoms = prenoms;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenoms() {
		return prenoms;
	}

	public void setPrenoms(String prenoms) {
		this.prenoms = prenoms;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhotoUrl() {
		return photoUrl;
	}

	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}

	public Integer getCoolitudeMeter() {
		if (this.coolitudeMeter == null) {
			this.coolitudeMeter = 0;
			return this.coolitudeMeter;
		} else {
			return this.coolitudeMeter;
		}
	}

	public void setCoolitudeMeter(Integer coolitudeMeter) {
		if (this.coolitudeMeter == null) {
			this.coolitudeMeter = 0;
			this.coolitudeMeter = coolitudeMeter;
		} else {
			this.coolitudeMeter = coolitudeMeter;
		}
	}
}