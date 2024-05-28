/*package com.example.demo;
import org.springframework.web.server.ResponseStatusException;
import static org.mockito.ArgumentMatchers.anyString;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;



public class CowControllerTests {

    @Mock
    private CowService cowService;

    @InjectMocks
    private CowController cowController;

    private List<Cow> testCows;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        testCows = new ArrayList<>();
        testCows.add(new Cow(1, "Bessie", "Holstein", "123 Farm Road"));
        testCows.add(new Cow(2, "Moo", "Jersey", "456 Ranch Avenue"));
    }

    @Test
    public void testGetAllCows() {
        when(cowService.getAllCows()).thenReturn(testCows);

        ResponseEntity<List<Cow>> response = cowController.getAllCows();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(testCows, response.getBody());
        verify(cowService, times(1)).getAllCows();
    }

    @Test
    public void testGetCowById() {
        int id = 1;
        Cow expectedCow = testCows.get(0);
        when(cowService.getCowById(id)).thenReturn(expectedCow);

        ResponseEntity<Cow> response = cowController.getCowById(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedCow, response.getBody());
        verify(cowService, times(1)).getCowById(id);
    }

    @Test
    public void testGetCowById_NotFound() {
        int id = 100;
        when(cowService.getCowById(id)).thenReturn(null);

        ResponseEntity<Cow> response = cowController.getCowById(id);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
        verify(cowService, times(1)).getCowById(id);
    }

    @Test
    public void testCreateCow() {
        Cow newCow = new Cow(3, "Daisy", "Hereford", "789 Pasture Lane");
        when(cowService.createCow(any(Cow.class))).thenReturn(newCow);

        ResponseEntity<Cow> response = cowController.createCow(newCow);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(newCow, response.getBody());
        verify(cowService, times(1)).createCow(newCow);
    }

    @Test
    public void testUpdateCow() {
        int id = 1;
        Cow updatedCow = new Cow(1, "Bessie", "Holstein", "123 Farm Road (Updated)");
        when(cowService.updateCow(eq(id), any(Cow.class))).thenReturn(updatedCow);

        ResponseEntity<Cow> response = cowController.updateCow(id, updatedCow);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedCow, response.getBody());
        verify(cowService, times(1)).updateCow(id, updatedCow);
    }

    @Test
    public void testDeleteCow() {
        int id = 1;
        doNothing().when(cowService).deleteCow(id);

        ResponseEntity<Void> response = cowController.deleteCow(id);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(cowService, times(1)).deleteCow(id);
    }



    
    @Test
public void testCreateCow_Success() {
    Cow newCow = new Cow(3, "Daisy", "Hereford", "789 Pasture Lane");
    when(cowService.createCow(any(Cow.class))).thenReturn(newCow);

    ResponseEntity<Cow> response = cowController.createCow(newCow);

    assertEquals(HttpStatus.CREATED, response.getStatusCode());
    assertEquals(newCow, response.getBody());
    verify(cowService, times(1)).createCow(newCow);
}

@Test
public void testGetAllCows_EmptyList() {
    // Set up the mock to return an empty list
    when(cowService.getAllCows()).thenReturn(new ArrayList<>());

    // Call the controller method
    ResponseEntity<List<Cow>> response = cowController.getAllCows();

    // Verify the response
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(0, response.getBody().size()); // Check that the list is empty
    verify(cowService, times(1)).getAllCows();
}

    
@Test
public void testGetCowById_Success() {
    int id = 1;
    Cow expectedCow = new Cow(id, "Bessie", "Holstein", "123 Farm Road");
    when(cowService.getCowById(id)).thenReturn(expectedCow);

    ResponseEntity<Cow> response = cowController.getCowById(id);

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(expectedCow, response.getBody());
    verify(cowService, times(1)).getCowById(id);
}


@Test
public void testUpdateCow_Success() {
    int id = 1;
    Cow updatedCow = new Cow(id, "Bessie", "Holstein", "123 Farm Road (Updated)");
    when(cowService.updateCow(eq(id), any(Cow.class))).thenReturn(updatedCow);

    ResponseEntity<Cow> response = cowController.updateCow(id, updatedCow);

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(updatedCow, response.getBody());
    verify(cowService, times(1)).updateCow(id, updatedCow);
}

@Test
public void testDeleteCow_Success() {
    int id = 1;
    doNothing().when(cowService).deleteCow(id);

    ResponseEntity<Void> response = cowController.deleteCow(id);

    assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    verify(cowService, times(1)).deleteCow(id);
}




// Existing test methods are here...

@Test
public void testGetCowById_MinId() {
    int id = Integer.MIN_VALUE;
    when(cowService.getCowById(id)).thenReturn(null);

    ResponseEntity<Cow> response = cowController.getCowById(id);

    assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    assertNull(response.getBody());
    verify(cowService, times(1)).getCowById(id);
}

@Test
public void testGetCowById_MaxId() {
    int id = Integer.MAX_VALUE;
    Cow expectedCow = new Cow(id, "Max", "Breed", "Location");
    when(cowService.getCowById(id)).thenReturn(expectedCow);

    ResponseEntity<Cow> response = cowController.getCowById(id);

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(expectedCow, response.getBody());
    verify(cowService, times(1)).getCowById(id);
}

@Test
public void testGetCowById_IdNotFound() {
    int id = 100;
    when(cowService.getCowById(id)).thenReturn(null);

    ResponseEntity<Cow> response = cowController.getCowById(id);

    assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    assertNull(response.getBody());
    verify(cowService, times(1)).getCowById(id);
}


@Test
public void testUpdateCow_CowNotFound() {
    int id = 100;
    Cow updatedCow = new Cow(id, "Not Found", "Breed", "Location");
    when(cowService.updateCow(eq(id), any(Cow.class))).thenReturn(null);

    ResponseEntity<Cow> response = cowController.updateCow(id, updatedCow);

    assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    assertNull(response.getBody());
    verify(cowService, times(1)).updateCow(id, updatedCow);
}


@Test
public void testGetCowById_NullResponse() {
    int id = 1;
    when(cowService.getCowById(id)).thenReturn(null);

    ResponseEntity<Cow> response = cowController.getCowById(id);

    assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    assertNull(response.getBody());
    verify(cowService, times(1)).getCowById(id);
}



}
*/