package test.java.test;

import model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Salad Tests")
public class SaladTest {

    private Salad salad;

    @BeforeEach
    void setUp() {
        salad = new Salad(1, "Грецький");
    }

    @Test
    @DisplayName("Salad creation")
    void testSaladCreation() {
        assertEquals(1, salad.getId());
        assertEquals("Грецький", salad.getName());
        assertEquals(0, salad.getVegetableCount());
    }

    @Test
    @DisplayName("Add vegetable to salad")
    void testAddVegetable() {
        salad.addVegetable(new FruitVegetable(1, "Помідор", 100, 18));
        assertEquals(1, salad.getVegetableCount());
    }

    @Test
    @DisplayName("Add multiple vegetables")
    void testAddMultipleVegetables() {
        salad.addVegetable(new FruitVegetable(1, "Помідор", 100, 18));
        salad.addVegetable(new FruitVegetable(2, "Огірок", 150, 15));
        salad.addVegetable(new RootVegetable(3, "Цибуля", 50, 40));
        assertEquals(3, salad.getVegetableCount());
    }

    @Test
    @DisplayName("Remove vegetable from salad")
    void testRemoveVegetable() {
        salad.addVegetable(new FruitVegetable(1, "Помідор", 100, 18));
        salad.addVegetable(new FruitVegetable(2, "Огірок", 150, 15));
        salad.removeVegetable(0);
        assertEquals(1, salad.getVegetableCount());
    }

    @Test
    @DisplayName("Remove vegetable with invalid index")
    void testRemoveVegetableInvalidIndex() {
        salad.addVegetable(new FruitVegetable(1, "Помідор", 100, 18));
        salad.removeVegetable(5);
        assertEquals(1, salad.getVegetableCount());
    }

    @Test
    @DisplayName("Remove all vegetables")
    void testRemoveAllVegetables() {
        salad.addVegetable(new FruitVegetable(1, "Помідор", 100, 18));
        salad.addVegetable(new FruitVegetable(2, "Огірок", 150, 15));
        salad.removeAllVegetables();
        assertEquals(0, salad.getVegetableCount());
    }

    @Test
    @DisplayName("Calculate total calories")
    void testCalculateTotalCalories() {
        salad.addVegetable(new FruitVegetable(1, "Помідор", 100, 18));
        salad.addVegetable(new FruitVegetable(2, "Огірок", 200, 15));
        assertEquals(48.0, salad.calculateTotalCalories(), 0.01);
    }

    @Test
    @DisplayName("Calculate total calories for empty salad")
    void testCalculateTotalCaloriesEmpty() {
        assertEquals(0.0, salad.calculateTotalCalories());
    }

    @Test
    @DisplayName("Sort vegetables by weight")
    void testSortVegetablesByWeight() {
        salad.addVegetable(new FruitVegetable(1, "Помідор", 200, 18));
        salad.addVegetable(new FruitVegetable(2, "Огірок", 100, 15));
        salad.addVegetable(new RootVegetable(3, "Цибуля", 50, 40));
        salad.sortVegetablesByWeight();

        ArrayList<Vegetable> vegetables = salad.getVegetables();
        assertEquals(50, vegetables.get(0).getWeight());
        assertEquals(100, vegetables.get(1).getWeight());
        assertEquals(200, vegetables.get(2).getWeight());
    }

    @Test
    @DisplayName("Sort vegetables by calories")
    void testSortVegetablesByCalories() {
        salad.addVegetable(new FruitVegetable(1, "Помідор", 100, 18));
        salad.addVegetable(new FruitVegetable(2, "Огірок", 100, 15));
        salad.addVegetable(new RootVegetable(3, "Морква", 100, 41));
        salad.sortVegetablesByCalories();

        ArrayList<Vegetable> vegetables = salad.getVegetables();
        assertEquals(15, vegetables.get(0).getCaloriesPer100g());
        assertEquals(18, vegetables.get(1).getCaloriesPer100g());
        assertEquals(41, vegetables.get(2).getCaloriesPer100g());
    }

    @Test
    @DisplayName("Find vegetables by calorie range")
    void testFindVegetablesByCalorieRange() {
        salad.addVegetable(new FruitVegetable(1, "Помідор", 100, 18));
        salad.addVegetable(new FruitVegetable(2, "Огірок", 100, 15));
        salad.addVegetable(new RootVegetable(3, "Морква", 100, 41));

        ArrayList<Vegetable> result = salad.findVegetablesByCalorieRange(15, 20);
        assertEquals(2, result.size());
    }

    @Test
    @DisplayName("Find vegetables by calorie range - no results")
    void testFindVegetablesByCalorieRangeNoResults() {
        salad.addVegetable(new FruitVegetable(1, "Помідор", 100, 18));
        ArrayList<Vegetable> result = salad.findVegetablesByCalorieRange(50, 100);
        assertEquals(0, result.size());
    }

    @Test
    @DisplayName("Get vegetables list")
    void testGetVegetables() {
        salad.addVegetable(new FruitVegetable(1, "Помідор", 100, 18));
        ArrayList<Vegetable> vegetables = salad.getVegetables();
        assertNotNull(vegetables);
        assertEquals(1, vegetables.size());
    }

    @Test
    @DisplayName("Setters work correctly")
    void testSetters() {
        salad.setId(10);
        salad.setName("Цезар");
        assertEquals(10, salad.getId());
        assertEquals("Цезар", salad.getName());
    }

    @Test
    @DisplayName("toString contains required information")
    void testToString() {
        salad.addVegetable(new FruitVegetable(1, "Помідор", 100, 18));
        String str = salad.toString();
        assertTrue(str.contains("Грецький"));
        assertTrue(str.contains("1"));
    }
}