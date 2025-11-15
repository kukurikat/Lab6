package test.java.test;

import model.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Vegetable Tests")
public class VegetableTest {

    @Test
    @DisplayName("FruitVegetable creation")
    void testFruitVegetableCreation() {
        FruitVegetable tomato = new FruitVegetable(1, "Помідор", 100, 18);
        assertEquals(1, tomato.getId());
        assertEquals("Помідор", tomato.getName());
        assertEquals(100, tomato.getWeight());
        assertEquals(18, tomato.getCaloriesPer100g());
        assertEquals("Плодовий", tomato.getVegetableType());
    }

    @Test
    @DisplayName("RootVegetable creation")
    void testRootVegetableCreation() {
        RootVegetable carrot = new RootVegetable(2, "Морква", 150, 41);
        assertEquals("Кореневий", carrot.getVegetableType());
    }

    @Test
    @DisplayName("LeafyVegetable creation")
    void testLeafyVegetableCreation() {
        LeafyVegetable lettuce = new LeafyVegetable(3, "Салат", 50, 14);
        assertEquals("Листяний", lettuce.getVegetableType());
    }

    @Test
    @DisplayName("Calculate total calories")
    void testCalculateTotalCalories() {
        FruitVegetable tomato = new FruitVegetable(1, "Помідор", 200, 18);
        assertEquals(36.0, tomato.calculateTotalCalories(), 0.01);
    }

    @Test
    @DisplayName("Calculate total calories with zero weight")
    void testCalculateTotalCaloriesZeroWeight() {
        FruitVegetable tomato = new FruitVegetable(1, "Помідор", 0, 18);
        assertEquals(0.0, tomato.calculateTotalCalories());
    }

    @Test
    @DisplayName("Setters work correctly")
    void testSetters() {
        FruitVegetable veg = new FruitVegetable(1, "Помідор", 100, 18);
        veg.setId(5);
        veg.setName("Огірок");
        veg.setWeight(150);
        veg.setCaloriesPer100g(15);

        assertEquals(5, veg.getId());
        assertEquals("Огірок", veg.getName());
        assertEquals(150, veg.getWeight());
        assertEquals(15, veg.getCaloriesPer100g());
    }

    @Test
    @DisplayName("toString contains required information")
    void testToString() {
        FruitVegetable tomato = new FruitVegetable(1, "Помідор", 100, 18);
        String str = tomato.toString();
        assertTrue(str.contains("Помідор"));
        assertTrue(str.contains("Плодовий"));
    }

    @Test
    @DisplayName("Polymorphism works correctly")
    void testPolymorphism() {
        Vegetable fruit = new FruitVegetable(1, "Помідор", 100, 18);
        Vegetable root = new RootVegetable(2, "Морква", 150, 41);
        Vegetable leafy = new LeafyVegetable(3, "Салат", 50, 14);

        assertEquals("Плодовий", fruit.getVegetableType());
        assertEquals("Кореневий", root.getVegetableType());
        assertEquals("Листяний", leafy.getVegetableType());
    }
}