package test;

import model.*;

public class VegetableTest {

    public static void main(String[] args) {
        int passed = 0;
        int failed = 0;

        System.out.println("=== VegetableTest ===");

        try {
            FruitVegetable tomato = new FruitVegetable(1, "Помідор", 100, 18);
            assert tomato.getId() == 1 && tomato.getName().equals("Помідор") && tomato.getWeight() == 100
                    && tomato.getCaloriesPer100g() == 18 && tomato.getVegetableType().equals("Плодовий");
            System.out.println("[PASS] FruitVegetable creation");
            passed++;
        } catch (AssertionError e) {
            System.out.println("[FAIL] FruitVegetable creation");
            failed++;
        }

        try {
            RootVegetable carrot = new RootVegetable(2, "Морква", 150, 41);
            assert carrot.getVegetableType().equals("Кореневий");
            System.out.println("[PASS] RootVegetable creation");
            passed++;
        } catch (AssertionError e) {
            System.out.println("[FAIL] RootVegetable creation");
            failed++;
        }

        try {
            LeafyVegetable lettuce = new LeafyVegetable(3, "Салат", 50, 14);
            assert lettuce.getVegetableType().equals("Листяний");
            System.out.println("[PASS] LeafyVegetable creation");
            passed++;
        } catch (AssertionError e) {
            System.out.println("[FAIL] LeafyVegetable creation");
            failed++;
        }

        try {
            FruitVegetable tomato = new FruitVegetable(1, "Помідор", 200, 18);
            assert Math.abs(tomato.calculateTotalCalories() - 36.0) < 0.01;
            System.out.println("[PASS] calculateTotalCalories");
            passed++;
        } catch (AssertionError e) {
            System.out.println("[FAIL] calculateTotalCalories");
            failed++;
        }

        try {
            FruitVegetable tomato = new FruitVegetable(1, "Помідор", 0, 18);
            assert tomato.calculateTotalCalories() == 0.0;
            System.out.println("[PASS] calculateTotalCalories zero weight");
            passed++;
        } catch (AssertionError e) {
            System.out.println("[FAIL] calculateTotalCalories zero weight");
            failed++;
        }

        try {
            FruitVegetable veg = new FruitVegetable(1, "Помідор", 100, 18);
            veg.setId(5);
            veg.setName("Огірок");
            veg.setWeight(150);
            veg.setCaloriesPer100g(15);
            assert veg.getId() == 5 && veg.getName().equals("Огірок") && veg.getWeight() == 150 && veg.getCaloriesPer100g() == 15;
            System.out.println("[PASS] Setters");
            passed++;
        } catch (AssertionError e) {
            System.out.println("[FAIL] Setters");
            failed++;
        }

        try {
            FruitVegetable tomato = new FruitVegetable(1, "Помідор", 100, 18);
            String str = tomato.toString();
            assert str.contains("Помідор") && str.contains("Плодовий");
            System.out.println("[PASS] toString");
            passed++;
        } catch (AssertionError e) {
            System.out.println("[FAIL] toString");
            failed++;
        }

        try {
            Vegetable fruit = new FruitVegetable(1, "Помідор", 100, 18);
            Vegetable root = new RootVegetable(2, "Морква", 150, 41);
            Vegetable leafy = new LeafyVegetable(3, "Салат", 50, 14);
            assert fruit.getVegetableType().equals("Плодовий") && root.getVegetableType().equals("Кореневий")
                    && leafy.getVegetableType().equals("Листяний");
            System.out.println("[PASS] Polymorphism");
            passed++;
        } catch (AssertionError e) {
            System.out.println("[FAIL] Polymorphism");
            failed++;
        }

        System.out.println("Result: " + passed + "/" + (passed + failed) + " passed\n");
    }
}