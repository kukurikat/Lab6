package test.java.test;

import model.*;
import repository.*;
import service.SaladService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("SaladService Tests")
public class SaladServiceTest {

    private SaladService service;

    @BeforeEach
    void setUp() {
        service = new SaladService(new VegetableRepository(), new SaladRepository());
    }

    @Test
    @DisplayName("Create salad")
    void testCreateSalad() {
        Salad salad = service.createSalad("Грецький", 1);
        assertNotNull(salad);
        assertEquals("Грецький", salad.getName());
        assertEquals(1, salad.getId());
    }

    @Test
    @DisplayName("Add vegetable to salad")
    void testAddVegetableToSalad() {
        Salad salad = service.createSalad("Грецький", 1);
        service.addVegetableToSalad(salad, 1, 100);
        assertEquals(1, salad.getVegetableCount());
    }

    @Test
    @DisplayName("Add vegetables of different types")
    void testAddVegetableToSaladDifferentTypes() {
        Salad salad = service.createSalad("Грецький", 1);
        service.addVegetableToSalad(salad, 1, 100);
        service.addVegetableToSalad(salad, 3, 150);
        service.addVegetableToSalad(salad, 4, 50);
        assertEquals(3, salad.getVegetableCount());
    }

    @Test
    @DisplayName("Add non-existent vegetable to salad")
    void testAddVegetableToSaladNonExistent() {
        Salad salad = service.createSalad("Грецький", 1);
        service.addVegetableToSalad(salad, 999, 100);
        assertEquals(0, salad.getVegetableCount());
    }

    @Test
    @DisplayName("Remove vegetable from salad")
    void testRemoveVegetableFromSalad() {
        Salad salad = service.createSalad("Грецький", 1);
        service.addVegetableToSalad(salad, 1, 100);
        service.addVegetableToSalad(salad, 2, 150);
        service.removeVegetableFromSalad(salad, 0);
        assertEquals(1, salad.getVegetableCount());
    }

    @Test
    @DisplayName("Calculate calories")
    void testCalculateCalories() {
        Salad salad = service.createSalad("Грецький", 1);
        service.addVegetableToSalad(salad, 1, 100);
        service.addVegetableToSalad(salad, 2, 200);
        double calories = service.calculateCalories(salad);
        assertEquals(48.0, calories, 0.01);
    }

    @Test
    @DisplayName("Sort salad by calories")
    void testSortSaladByCalories() {
        Salad salad = service.createSalad("Грецький", 1);
        service.addVegetableToSalad(salad, 3, 100);
        service.addVegetableToSalad(salad, 1, 100);
        service.sortSaladByCalories(salad, "asc");

        ArrayList<Vegetable> vegetables = salad.getVegetables();
        assertTrue(vegetables.get(0).getCaloriesPer100g() <= vegetables.get(1).getCaloriesPer100g());
    }

    @Test
    @DisplayName("Sort salad by weight")
    void testSortSaladByWeight() {
        Salad salad = service.createSalad("Грецький", 1);
        service.addVegetableToSalad(salad, 1, 200);
        service.addVegetableToSalad(salad, 2, 100);
        service.sortSaladByWeight(salad);

        ArrayList<Vegetable> vegetables = salad.getVegetables();
        assertTrue(vegetables.get(0).getWeight() <= vegetables.get(1).getWeight());
    }

    @Test
    @DisplayName("Find vegetables by calorie range")
    void testFindVegetablesByCalorieRange() {
        Salad salad = service.createSalad("Грецький", 1);
        service.addVegetableToSalad(salad, 1, 100);
        service.addVegetableToSalad(salad, 2, 100);
        service.addVegetableToSalad(salad, 3, 100);

        ArrayList<Vegetable> result = service.findVegetablesByCalorieRange(salad, 15, 20);
        assertEquals(2, result.size());
    }

    @Test
    @DisplayName("Delete salad")
    void testDeleteSalad() {
        service.createSalad("Грецький", 1);
        service.deleteSalad(1);
        assertNull(service.getSaladById(1));
    }

    @Test
    @DisplayName("Get salad by ID")
    void testGetSaladById() {
        service.createSalad("Грецький", 1);
        Salad found = service.getSaladById(1);
        assertNotNull(found);
        assertEquals("Грецький", found.getName());
    }

    @Test
    @DisplayName("Get salad by ID - not found")
    void testGetSaladByIdNotFound() {
        Salad found = service.getSaladById(999);
        assertNull(found);
    }

    @Test
    @DisplayName("Get all salads")
    void testGetAllSalads() {
        service.createSalad("Грецький", 1);
        service.createSalad("Цезар", 2);
        ArrayList<Salad> salads = service.getAllSalads();
        assertEquals(2, salads.size());
    }

    @Test
    @DisplayName("Delete salad int returns false")
    void testDeleteSaladInt() {
        assertFalse(service.deleteSaladInt());
    }
}