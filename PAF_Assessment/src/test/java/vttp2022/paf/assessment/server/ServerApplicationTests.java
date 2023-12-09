package vttp2022.paf.assessment.server;

import static org.junit.jupiter.api.Assertions.assertThrowsExactly;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import vttp2022.paf.assessment.server.models.Task;
import vttp2022.paf.assessment.server.repositories.TaskRepository;
import vttp2022.paf.assessment.server.repositories.UserRepository;
import vttp2022.paf.assessment.server.services.TodoService;

@SpringBootTest
class ServerApplicationTests {
	
	@Autowired private TodoService todoSvc;
	@MockBean private TaskRepository taskRepo;
	@MockBean private UserRepository userRepo;

	@Test
	void contextLoads() {
	}

	@Test
	void upsertTaskShouldSucceed(){
		List<Task> list = new ArrayList<>();
		Task t1 = new Task();
		t1.setDescription("get coffee");
		t1.setPriority(3);
		t1.setDueDate(new Date());
		Task t2 = new Task();
		t2.setDescription("buy presents");
		t2.setPriority(3);
		t2.setDueDate(new Date());
		list.add(t1);
		list.add(t2);

		Mockito.when(taskRepo.insertTask(any())).thenReturn(true);
		Mockito.when(userRepo.getUserByUsername(anyString())).thenReturn(Optional.empty());
		Mockito.when(userRepo.insertUser(any())).thenReturn(true);

		assertTrue(todoSvc.upsertTask("jane", list));
	}

	@Test
	void upsertTaskShouldFail(){
		List<Task> list = new ArrayList<>();
		Task t1 = new Task();
		t1.setDescription("get coffee");
		t1.setPriority(3);
		t1.setDueDate(new Date());
		Task t2 = new Task();
		t2.setDescription("buy presents");
		t2.setPriority(3);
		t2.setDueDate(new Date());
		list.add(t1);
		list.add(t2);

		Mockito.when(taskRepo.insertTask(any())).thenReturn(false);
		Mockito.when(userRepo.getUserByUsername(anyString())).thenReturn(Optional.empty());
		Mockito.when(userRepo.insertUser(any())).thenReturn(true);
		assertThrowsExactly(IllegalArgumentException.class, () -> todoSvc.upsertTask("kate",list));
	}
}
