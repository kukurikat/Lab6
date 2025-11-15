package test.java.test;

import model.Salad;
import repository.SaladRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("SaladRepository Tests")
public class SaladRepositoryTest {

    private SaladRepository repo;

    @BeforeEach
    void setUp() {
        repo = new SaladRepository();
    }

    @Test
    @DisplayName("Repository is initially empty")
    void testInitiallyEmpty() {
        assertEquals(0, repo.getSaladCount());
    }

    @Test
    @DisplayName("Add salad to repository")
    void testAddSalad() {
        repo.addSalad(new Salad(1, "Грецький"));
        assertEquals(1, repo.getSaladCount());
    }

    @Test
    @DisplayName("Add multiple salads")
    void testAddMultipleSalads() {
        repo.addSalad(new Salad(1, "Грецький"));
        repo.addSalad(new Salad(2, "Цезар"));
        repo.addSalad(new Salad(3, "Олів'є"));
        assertEquals(3, repo.getSaladCount());
    }

    @Test
    @DisplayName("Remove salad from repository")
    void testRemoveSalad() {
        repo.addSalad(new Salad(1, "Грецький"));
        boolean removed = repo.removeSalad(1);
        assertTrue(removed);
        assertEquals(0, repo.getSaladCount());
    }

    @Test
    @DisplayName("Remove non-existent salad")
    void testRemoveSaladNonExistent() {
        repo.addSalad(new Salad(1, "Грецький"));
        boolean removed = repo.removeSalad(999);
        assertFalse(removed);
        assertEquals(1, repo.getSaladCount());
    }

    @Test
    @DisplayName("Get salad by ID")
    void testGetSaladById() {
        repo.addSalad(new Salad(1, "Грецький"));
        Salad found = repo.getSaladById(1);
        assertNotNull(found);
        assertEquals("Грецький", found.getName());
    }

    @Test
    @DisplayName("Get salad by ID - not found")
    void testGetSaladByIdNotFound() {
        Salad found = repo.getSaladById(999);
        assertNull(found);
    }

    @Test
    @DisplayName("Update salad")
    void testUpdateSalad() {
        repo.addSalad(new Salad(1, "Грецький"));
        boolean result = repo.updateSalad(1, new Salad(1, "Грецький оновлений"));
        assertTrue(result);

        Salad found = repo.getSaladById(1);
        assertEquals("Грецький оновлений", found.getName());
    }

    @Test
    @DisplayName("Update non-existent salad")
    void testUpdateSaladNonExistent() {
        boolean result = repo.updateSalad(999, new Salad(999, "Тест"));
        assertFalse(result);
    }

    @Test
    @DisplayName("Get all salads")
    void testGetAllSalads() {
        repo.addSalad(new Salad(1, "Грецький"));
        repo.addSalad(new Salad(2, "Цезар"));
        ArrayList<Salad> salads = repo.getAllSalads();
        assertEquals(2, salads.size());
    }

    @Test
    @DisplayName("Get all salads from empty repository")
    void testGetAllSaladsEmpty() {
        ArrayList<Salad> salads = repo.getAllSalads();
        assertEquals(0, salads.size());
    }

    @Test
    @DisplayName("Find salad by name")
    void testFindSaladByName() {
        repo.addSalad(new Salad(1, "Грецький"));
        Salad found = repo.findSaladByName("Грецький");
        assertNotNull(found);
        assertEquals(1, found.getId());
    }

    @Test
    @DisplayName("Find salad by name - case insensitive")
    void testFindSaladByNameCaseInsensitive() {
        repo.addSalad(new Salad(1, "Грецький"));
        Salad found = repo.findSaladByName("грецький");
        assertNotNull(found);
        assertEquals(1, found.getId());
    }

    @Test
    @DisplayName("Find salad by name - not found")
    void testFindSaladByNameNotFound() {
        Salad found = repo.findSaladByName("Неіснуючий");
        assertNull(found);
    }

    @Test
    @DisplayName("Clear repository")
    void testClear() {
        repo.addSalad(new Salad(1, "Грецький"));
        repo.addSalad(new Salad(2, "Цезар"));
        repo.clear();
        assertEquals(0, repo.getSaladCount());
    }

    @Test
    @DisplayName("Get salad count")
    void testGetSaladCount() {
        repo.addSalad(new Salad(1, "Грецький"));
        repo.addSalad(new Salad(2, "Цезар"));
        assertEquals(2, repo.getSaladCount());
    }
}