package topcollegue.controller.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

public class InfosAuthentification {
	
	
	@Email
	@NotEmpty
	private String email;
	
	@NotEmpty
	private String motDePasse;
	private String photo;
	
	public InfosAuthentification(String email, String motDePasse, String photo) {
		this.email = email;
		this.motDePasse = motDePasse;
		this.photo = photo;
	}
	
	

	public InfosAuthentification() {
		super();
	}



	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMotDePasse() {
		return motDePasse;
	}

	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}	
}
