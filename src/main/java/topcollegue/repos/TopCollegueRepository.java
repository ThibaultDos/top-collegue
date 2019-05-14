package topcollegue.repos;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import topcollegue.TopCollegue;

public interface TopCollegueRepository extends JpaRepository<TopCollegue, String> {
	
	Optional<TopCollegue> findByEmail(String email);
	
}