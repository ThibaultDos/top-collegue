package topcollegue.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import topcollegue.vote.Votes;

public interface VotesRepository extends JpaRepository<Votes, String> {

}
