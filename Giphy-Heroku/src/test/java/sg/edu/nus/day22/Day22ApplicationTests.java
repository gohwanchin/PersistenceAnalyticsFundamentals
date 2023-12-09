package sg.edu.nus.day22;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import sg.edu.nus.day22.service.GiphyService;

@SpringBootTest
class Day22ApplicationTests {

	@Autowired
	private GiphyService giphySvc;

	@Test
	void shouldLoad10Images() {
		List<String> gifs = giphySvc.searchGif("arcane");
		assertEquals(10, gifs.size(), "Gifs size: " + gifs.size());
	}
}
