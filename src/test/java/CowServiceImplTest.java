/*import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.domain.Cow;
import com.example.demo.service.CowService;
import com.example.demo.service.CowServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

@SpringBootTest(classes = {CowServiceImpl.class})
public class CowServiceImplTest {

    @Autowired
    private CowService cowService;

    @Test
    public void testCreateCow() {
        // Given
        Cow cow = new Cow(0, "Bessie", "Holstein", "123 Farm Road");

        // When
        Cow createdCow = cowService.createCow(cow);

        // Then
        assertNotNull(createdCow.getId());
        assertEquals(cow.getName(), createdCow.getName());
        assertEquals(cow.getBreed(), createdCow.getBreed());
        assertEquals(cow.getaddress(), createdCow.getaddress());
    }


    @Test
    public void testGetAllCows() {
        // Given
        Cow cow1 = new Cow(0, "Bessie", "Holstein", "123 Farm Road");
        Cow cow2 = new Cow(1, "Moo", "Jersey", "456 Ranch Avenue");
        cowService.createCow(cow1);
        cowService.createCow(cow2);

        // When
        List<Cow> allCows = cowService.getAllCows();

        // Then
        assertEquals(3, allCows.size());
    }

    @Test
    public void testGetCowById() {
        // Given
        Cow cow = new Cow(0, "Bessie", "Holstein", "123 Farm Road");
        Cow createdCow = cowService.createCow(cow);

        // When
        Cow retrievedCow = cowService.getCowById(createdCow.getId());

        // Then
        assertEquals(createdCow, retrievedCow);
    }

    
    @Test
    public void testUpdateCow() {
        // Given
        Cow cow = new Cow(0, "Bessie", "Holstein", "123 Farm Road");
        Cow createdCow = cowService.createCow(cow);
        createdCow.setName("Updated Name");

        // When
        Cow updatedCow = cowService.updateCow(createdCow.getId(), createdCow);

        // Then
        assertEquals(createdCow, updatedCow);
    }
}


*/
