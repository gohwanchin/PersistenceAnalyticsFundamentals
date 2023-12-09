package sg.edu.nus.mini_project;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import sg.edu.nus.mini_project.model.User;
import sg.edu.nus.mini_project.repository.UserRepository;

@SpringBootTest
@AutoConfigureMockMvc
class MiniProjectApplicationTests {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private UserRepository userRepo;

	@Test
	void shouldLogin() throws Exception {
		Mockito.when(userRepo.userLogin(any())).thenReturn(true);
		RequestBuilder req = MockMvcRequestBuilders.post("/authenticate").param("username", "lucy")
				.param("password", "tara");
		mvc.perform(req).andDo(print()).andExpect(view().name("redirect:/t/searchPage"));
	}

	@Test
	void shouldThrowLoginError() throws Exception {
		Mockito.when(userRepo.userLogin(any())).thenReturn(false);
		RequestBuilder req = MockMvcRequestBuilders.post("/authenticate").param("username", "lucy")
				.param("password", "kate");
		mvc.perform(req).andDo(print())
				.andExpect(result -> assertTrue(
						result.getResponse().getContentAsString().contains("Wrong username or password")));
	}

	@Test
	void shouldSignup() throws Exception {
		Mockito.when(userRepo.addUser(any())).thenReturn(true);
		RequestBuilder req = MockMvcRequestBuilders.post("/signup").param("username", "test")
				.param("password", "password");
		mvc.perform(req).andDo(print()).andExpect(view().name("redirect:/t/searchPage"));
	}

	@Test
	void shouldThrowSignupError() throws Exception {
		Mockito.when(userRepo.addUser(any())).thenReturn(false);
		RequestBuilder req = MockMvcRequestBuilders.post("/signup").param("username", "test")
				.param("password", "password");
		mvc.perform(req).andDo(print()).andExpect(result -> 
				assertTrue(result.getResponse().getContentAsString()
						.contains("User unable to be created. Username already exists")));
	}

	@Test
	void getLogin() throws Exception {
		RequestBuilder req = MockMvcRequestBuilders.get("/");
		mvc.perform(req).andDo(print()).andExpect(view().name("index"));
	}

	@Test
	void shouldRedirectLogin() throws Exception {
		MockHttpSession sess = new MockHttpSession();
		User u = new User();
		u.setUsername("lucy");
		sess.setAttribute("username", "lucy");
		sess.setAttribute("user", u);
		RequestBuilder req = MockMvcRequestBuilders.get("/").session(sess);
		mvc.perform(req).andDo(print()).andExpect(view().name("redirect:/t/searchPage"));
	}

	@Test
	void getSignup() throws Exception {
		RequestBuilder req = MockMvcRequestBuilders.get("/signup");
		mvc.perform(req).andDo(print()).andExpect(view().name("signup"));
	}

	@Test
	void getSearch() throws Exception {
		MockHttpSession sess = new MockHttpSession();
		User u = new User();
		u.setUsername("lucy");
		sess.setAttribute("username", "lucy");
		sess.setAttribute("user", u);
		RequestBuilder req = MockMvcRequestBuilders.get("/t/searchPage").session(sess);
		mvc.perform(req).andDo(print()).andExpect(view().name("search"));
	}

	@Test
	void shouldReturnSearchResults() throws Exception {
		MockHttpSession sess = new MockHttpSession();
		sess.setAttribute("username", "lucy");
		RequestBuilder req = MockMvcRequestBuilders.get("/t/search").queryParam("query",
				"thor").session(sess);
		mvc.perform(req).andDo(print()).andExpect(view().name("results"))
				.andExpect(result -> assertTrue(result.getResponse().getContentAsString().contains("Search Results")));
	}

	@Test
	void shouldReturnWatchlist() throws Exception {
		MockHttpSession sess = new MockHttpSession();
		User u = new User();
		u.setUsername("lucy");
		sess.setAttribute("username", "lucy");
		sess.setAttribute("user", u);
		List<String> list = Arrays.asList("tt0401855");
		Mockito.when(userRepo.getWatchlist("lucy")).thenReturn(list);
		RequestBuilder req = MockMvcRequestBuilders.get("/t/watchlist").session(sess);
		mvc.perform(req).andDo(print()).andExpect(view().name("watchlist"))
				.andExpect(result -> assertTrue(result.getResponse().getContentAsString().contains("tt0401855")));
	}

	@Test
	void shouldGetTitle() throws Exception {
		MockHttpSession sess = new MockHttpSession();
		User u = new User();
		u.setUsername("lucy");
		sess.setAttribute("username", "lucy");
		sess.setAttribute("user", u);
		Mockito.when(userRepo.checkTitleExistsInWatchlist(anyString(),
				anyString())).thenReturn(false);
		RequestBuilder req = MockMvcRequestBuilders.get("/t/title/tt0320691").session(sess);
		mvc.perform(req).andDo(print())
				.andExpect(result -> assertTrue(result.getResponse().getContentAsString().contains("Underworld")));
	}

	@Test
	void shouldNotGetTitle() throws Exception {
		MockHttpSession sess = new MockHttpSession();
		sess.setAttribute("username", "lucy");
		RequestBuilder req = MockMvcRequestBuilders.get("/t/title/tt1").session(sess);
		mvc.perform(req).andDo(print()).andExpect(view().name("error"));
	}

	@Test
	void shouldAddToWatchlist() throws Exception {
		MockHttpSession sess = new MockHttpSession();
		User u = new User();
		u.setUsername("lucy");
		sess.setAttribute("username", "lucy");
		sess.setAttribute("user", u);
		Mockito.when(userRepo.addTitleToWatchlist(anyString(),
				anyString())).thenReturn(true);
		RequestBuilder req = MockMvcRequestBuilders.post("/t/title/tt0458352/add").session(sess);
		mvc.perform(req).andDo(print()).andExpect(view().name(
				"redirect:/t/title/tt0458352"));
	}

	@Test
	void shouldRemoveFromWatchlist() throws Exception {
		MockHttpSession sess = new MockHttpSession();
		User u = new User();
		u.setUsername("lucy");
		sess.setAttribute("username", "lucy");
		sess.setAttribute("user", u);
		Mockito.when(userRepo.removeTitleFromWatchlist(anyString(),
				anyString())).thenReturn(true);
		RequestBuilder req = MockMvcRequestBuilders.post("/t/title/tt0458352/remove").session(sess);
		mvc.perform(req).andDo(print()).andExpect(view().name(
				"redirect:/t/title/tt0458352"));
	}
}