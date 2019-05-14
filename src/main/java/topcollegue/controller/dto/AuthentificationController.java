package topcollegue.controller.dto;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import topcollegue.CollegueFromApi;
import topcollegue.TopCollegue;
import topcollegue.repos.TopCollegueRepository;
import topcollegue.repos.exception.RepositoryException;

@RestController
public class AuthentificationController {

	@Value("${jwt.expires_in}")
	private Integer EXPIRES_IN;

	@Value("${jwt.cookie}")
	private String TOKEN_COOKIE;

	@Value("${jwt.secret}")
	private String SECRET;

	@Autowired
	private TopCollegueRepository topCoolRepo;

	@PostMapping(value = "/auth")
	public ResponseEntity<?> authenticate(@RequestBody @Valid InfosAuthentification authenticationRequest,
			HttpServletResponse response) throws URISyntaxException, RepositoryException {

		RestTemplate rt = new RestTemplate();

		Map<String, String> infos = new HashMap<>();
		infos.put("identifiant", authenticationRequest.getEmail());
		infos.put("motDePasse", authenticationRequest.getMotDePasse());

		ResponseEntity<String> result = rt.postForEntity("https://dosanjos-collegues-api.herokuapp.com/identification",
				infos, String.class);

		RequestEntity<?> requestEntity = RequestEntity
				.get(new URI("https://dosanjos-collegues-api.herokuapp.com/collegues/me"))
				.header(HttpHeaders.COOKIE, result.getHeaders().getFirst(HttpHeaders.SET_COOKIE)).build();
		ResponseEntity<CollegueFromApi> rep2 = rt.exchange(requestEntity, CollegueFromApi.class);

		CollegueFromApi collegueFromApi = rep2.getBody();

		TopCollegue topCollegue = new TopCollegue(collegueFromApi.getNom(), collegueFromApi.getPrenoms());
		topCollegue.setEmail(authenticationRequest.getEmail());
		if (authenticationRequest.getPhoto() == null) {
			topCollegue.setPhotoUrl(collegueFromApi.getPhotoUrl());
		} else {
			topCollegue.setPhotoUrl(authenticationRequest.getPhoto());
		}

//provoque nullpointexception
//		if (topCoolRepo.existsById(topCollegue.getId().toString())) {
//			throw new RepositoryException("ce collègue est déjà enregistré");
//		} else {
//			topCoolRepo.save(topCollegue);
//			return result;
//		}
//provoque des doublons en base de donnée
		topCoolRepo.save(topCollegue);
		return result;

	}

	@ExceptionHandler(BadCredentialsException.class)
	public ResponseEntity<String> mauvaiseInfosConnexion(BadCredentialsException e) {
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
	}
}