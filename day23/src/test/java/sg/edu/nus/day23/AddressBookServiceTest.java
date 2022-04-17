package sg.edu.nus.day23;

import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import sg.edu.nus.day23.repository.AddressBookRepository;
import sg.edu.nus.day23.service.AddressBookService;

@SpringBootTest
public class AddressBookServiceTest {

    @Autowired
    private AddressBookService addSvc;

    @MockBean
    private AddressBookRepository addRepo;

    @Test
    public void shouldReturn() {
        String email = "test";
        //Mocks the game repository method
        Mockito.when(addRepo.checkExists(email)).thenReturn(false);

        //Calls the mock method
        Boolean exists = addSvc.checkEmail(email);
        assertFalse(exists);
    }
}
