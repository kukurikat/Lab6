package test.java.test;

import model.*;
import repository.VegetableRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("VegetableRepository Tests")
public class VegetableRepositoryTest {

    private VegetableRepository repo;

    @BeforeEach
    void setUp() {
        repo = new VegetableRepository();
    }

    @Test
    @DisplayName("Repository has initial vegetables")
    void testInitialVegetables() {
        assertEquals(7, repo.getVegetableCount());
    }

    @Test
    @DisplayName("Add vegetable to repository")
    void testAddVegetable() {
        repo.addVegetable(new FruitVegetable(100, "Баклажан", 0, 25));
        assertEquals(8, repo.getVegetableCount());
    }

    @Test
    @DisplayName("Remove vegetable from repository")
    void testRemoveVegetable() {
        boolean removed = repo.removeVegetable(1);
        assertTrue(removed);
        assertEquals(6, repo.getVegetableCount());
    }

    @Test
    @DisplayName("Remove non-existent vegetable")
    void testRemoveVegetableNonExistent() {
        boolean removed = repo.removeVegetable(999);
        assertFalse(removed);
        assertEquals(7, repo.getVegetableCount());
    }

    @Test
    @DisplayName("Get vegetable by ID")
    void testGetVegetableById() {
        Vegetable veg = repo.getVegetableById(1);
        assertNotNull(veg);
        assertEquals("Помідор", veg.getName());
    }

    @Test
    @DisplayName("Get vegetable by ID - not found")
    void testGetVegetableByIdNotFound() {
        Vegetable veg = repo.getVegetableById(999);
        assertNull(veg);
    }

    @Test
    @DisplayName("Update vegetable")
    void testUpdateVegetable() {
        boolean result = repo.updateVegetable(1, new FruitVegetable(1, "Червоний помідор", 0, 20));
        assertTrue(result);

        Vegetable veg = repo.getVegetableById(1);
        assertEquals("Червоний помідор", veg.getName());
    }

    @Test
    @DisplayName("Update non-existent vegetable")
    void testUpdateVegetableNonExistent() {
        boolean result = repo.updateVegetable(999, new FruitVegetable(999, "Тест", 0, 20));
        assertFalse(result);
    }

    @Test
    @DisplayName("Get all vegetables")
    void testGetAllVegetables() {
        ArrayList<Vegetable> veggies = repo.getAllVegetables();
        assertEquals(7, veggies.size());
    }

    @Test
    @DisplayName("Find vegetable by name")
    void testFindVegetableByName() {
        Vegetable veg = repo.findVegetableByName("Помідор");
        assertNotNull(veg);
        assertEquals(1, veg.getId());
    }

    @Test
    @DisplayName("Find vegetable by name - case insensitive")
    void testFindVegetableByNameCaseInsensitive() {
        Vegetable veg = repo.findVegetableByName("помідор");
        assertNotNull(veg);
        assertEquals(1, veg.getId());
    }

    @Test
    @DisplayName("Find vegetable by name - not found")
    void testFindVegetableByNameNotFound() {
        Vegetable veg = repo.findVegetableByName("Банан");
        assertNull(veg);
    }

    @Test
    @DisplayName("Clear repository")
    void testClear() {
        repo.clear();
        assertEquals(0, repo.getVegetableCount());
    }

    @Test
    @DisplayName("Get vegetable count after operations")
    void testGetVegetableCountAfterOperations() {
        int initial = repo.getVegetableCount();
        repo.addVegetable(new FruitVegetable(100, "Тест", 0, 20));
        repo.removeVegetable(100);
        assertEquals(initial, repo.getVegetableCount());
    }

    @Test
    @DisplayName("Repository contains different types of vegetables")
    void testContainsDifferentTypes() {
        ArrayList<Vegetable> veggies = repo.getAllVegetables();
        boolean hasFruit = false, hasRoot = false, hasLeafy = false;

        for (Vegetable veg : veggies) {
            if (veg instanceof FruitVegetable) hasFruit = true;
            if (veg instanceof RootVegetable) hasRoot = true;
            if (veg instanceof LeafyVegetable) hasLeafy = true;
        }

        assertTrue(hasFruit);
        assertTrue(hasRoot);
        assertTrue(hasLeafy);
    }
}