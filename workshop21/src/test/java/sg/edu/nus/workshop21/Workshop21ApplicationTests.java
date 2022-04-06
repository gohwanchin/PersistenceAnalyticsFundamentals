package sg.edu.nus.workshop21;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import sg.edu.nus.workshop21.model.Comment;
import sg.edu.nus.workshop21.model.Game;
import sg.edu.nus.workshop21.repository.GameRepository;

@SpringBootTest
class Workshop21ApplicationTests {

	@Autowired
	private GameRepository gameRepo;

	@Test
	void shouldReturnAGame() {
		Optional<Game> opt = gameRepo.getGameByGid(10);
		assertTrue(opt.isPresent(), "gid = 10");
	}

	@Test
	void shouldReturnEmpty() {
		Optional<Game> opt = gameRepo.getGameByGid(-10);
		assertTrue(opt.isEmpty(), "gid = -10");
	}

	@Test
	void shouldReturnListOfComments() {
		List<Comment> list = gameRepo.getCommentsByGid(10, 50, 0);
		assertTrue(list.size() == 42, "List size: " + list.size());
	}

	@Test
	void shouldReturnEmptyList() {
		List<Comment> list = gameRepo.getCommentsByGid(-10, 10, 10);
		assertTrue(list.isEmpty(), "List size: " + list.size());
	}
}
