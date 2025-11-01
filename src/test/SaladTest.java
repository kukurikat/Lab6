package test;

import model.*;
import java.util.ArrayList;

public class SaladTest {

    public static void main(String[] args) {
        int passed = 0;
        int failed = 0;

        System.out.println("=== SaladTest ===");

        try {
            Salad salad = new Salad(1, "Грецький");
            assert salad.getId() == 1 && salad.getName().equals("Грецький") && salad.getVegetableCount() == 0;
            System.out.println("[PASS] Salad creation");
            passed++;
        } catch (AssertionError e) {
            System.out.println("[FAIL] Salad creation");
            failed++;
        }

        try {
            Salad salad = new Salad(1, "Грецький");
            salad.addVegetable(new FruitVegetable(1, "Помідор", 100, 18));
            assert salad.getVegetableCount() == 1;
            System.out.println("[PASS] addVegetable");
            passed++;
        } catch (AssertionError e) {
            System.out.println("[FAIL] addVegetable");
            failed++;
        }

        try {
            Salad salad = new Salad(1, "Грецький");
            salad.addVegetable(new FruitVegetable(1, "Помідор", 100, 18));
            salad.addVegetable(new FruitVegetable(2, "Огірок", 150, 15));
            salad.addVegetable(new RootVegetable(3, "Цибуля", 50, 40));
            assert salad.getVegetableCount() == 3;
            System.out.println("[PASS] addMultipleVegetables");
            passed++;
        } catch (AssertionError e) {
            System.out.println("[FAIL] addMultipleVegetables");
            failed++;
        }

        try {
            Salad salad = new Salad(1, "Грецький");
            salad.addVegetable(new FruitVegetable(1, "Помідор", 100, 18));
            salad.addVegetable(new FruitVegetable(2, "Огірок", 150, 15));
            salad.removeVegetable(0);
            assert salad.getVegetableCount() == 1;
            System.out.println("[PASS] removeVegetable");
            passed++;
        } catch (AssertionError e) {
            System.out.println("[FAIL] removeVegetable");
            failed++;
        }

        try {
            Salad salad = new Salad(1, "Грецький");
            salad.addVegetable(new FruitVegetable(1, "Помідор", 100, 18));
            salad.removeVegetable(5);
            assert salad.getVegetableCount() == 1;
            System.out.println("[PASS] removeVegetable invalid index");
            passed++;
        } catch (AssertionError e) {
            System.out.println("[FAIL] removeVegetable invalid index");
            failed++;
        }

        try {
            Salad salad = new Salad(1, "Грецький");
            salad.addVegetable(new FruitVegetable(1, "Помідор", 100, 18));
            salad.addVegetable(new FruitVegetable(2, "Огірок", 150, 15));
            salad.removeAllVegetables();
            assert salad.getVegetableCount() == 0;
            System.out.println("[PASS] removeAllVegetables");
            passed++;
        } catch (AssertionError e) {
            System.out.println("[FAIL] removeAllVegetables");
            failed++;
        }

        try {
            Salad salad = new Salad(1, "Грецький");
            salad.addVegetable(new FruitVegetable(1, "Помідор", 100, 18));
            salad.addVegetable(new FruitVegetable(2, "Огірок", 200, 15));
            assert Math.abs(salad.calculateTotalCalories() - 48.0) < 0.01;
            System.out.println("[PASS] calculateTotalCalories");
            passed++;
        } catch (AssertionError e) {
            System.out.println("[FAIL] calculateTotalCalories");
            failed++;
        }

        try {
            Salad salad = new Salad(1, "Грецький");
            assert salad.calculateTotalCalories() == 0.0;
            System.out.println("[PASS] calculateTotalCalories empty");
            passed++;
        } catch (AssertionError e) {
            System.out.println("[FAIL] calculateTotalCalories empty");
            failed++;
        }

        try {
            Salad salad = new Salad(1, "Грецький");
            salad.addVegetable(new FruitVegetable(1, "Помідор", 200, 18));
            salad.addVegetable(new FruitVegetable(2, "Огірок", 100, 15));
            salad.addVegetable(new RootVegetable(3, "Цибуля", 50, 40));
            salad.sortVegetablesByWeight();
            ArrayList<Vegetable> v = salad.getVegetables();
            assert v.get(0).getWeight() == 50 && v.get(1).getWeight() == 100 && v.get(2).getWeight() == 200;
            System.out.println("[PASS] sortVegetablesByWeight");
            passed++;
        } catch (AssertionError e) {
            System.out.println("[FAIL] sortVegetablesByWeight");
            failed++;
        }

        try {
            Salad salad = new Salad(1, "Грецький");
            salad.addVegetable(new FruitVegetable(1, "Помідор", 100, 18));
            salad.addVegetable(new FruitVegetable(2, "Огірок", 100, 15));
            salad.addVegetable(new RootVegetable(3, "Морква", 100, 41));
            salad.sortVegetablesByCalories();
            ArrayList<Vegetable> v = salad.getVegetables();
            assert v.get(0).getCaloriesPer100g() == 15 && v.get(1).getCaloriesPer100g() == 18 && v.get(2).getCaloriesPer100g() == 41;
            System.out.println("[PASS] sortVegetablesByCalories");
            passed++;
        } catch (AssertionError e) {
            System.out.println("[FAIL] sortVegetablesByCalories");
            failed++;
        }

        try {
            Salad salad = new Salad(1, "Грецький");
            salad.addVegetable(new FruitVegetable(1, "Помідор", 100, 18));
            salad.addVegetable(new FruitVegetable(2, "Огірок", 100, 15));
            salad.addVegetable(new RootVegetable(3, "Морква", 100, 41));
            ArrayList<Vegetable> result = salad.findVegetablesByCalorieRange(15, 20);
            assert result.size() == 2;
            System.out.println("[PASS] findVegetablesByCalorieRange");
            passed++;
        } catch (AssertionError e) {
            System.out.println("[FAIL] findVegetablesByCalorieRange");
            failed++;
        }

        try {
            Salad salad = new Salad(1, "Грецький");
            salad.addVegetable(new FruitVegetable(1, "Помідор", 100, 18));
            ArrayList<Vegetable> result = salad.findVegetablesByCalorieRange(50, 100);
            assert result.size() == 0;
            System.out.println("[PASS] findVegetablesByCalorieRange no results");
            passed++;
        } catch (AssertionError e) {
            System.out.println("[FAIL] findVegetablesByCalorieRange no results");
            failed++;
        }

        try {
            Salad salad = new Salad(1, "Грецький");
            salad.addVegetable(new FruitVegetable(1, "Помідор", 100, 18));
            assert salad.getVegetables() != null && salad.getVegetables().size() == 1;
            System.out.println("[PASS] getVegetables");
            passed++;
        } catch (AssertionError e) {
            System.out.println("[FAIL] getVegetables");
            failed++;
        }

        try {
            Salad salad = new Salad(1, "Грецький");
            salad.setId(10);
            salad.setName("Цезар");
            assert salad.getId() == 10 && salad.getName().equals("Цезар");
            System.out.println("[PASS] Setters");
            passed++;
        } catch (AssertionError e) {
            System.out.println("[FAIL] Setters");
            failed++;
        }

        try {
            Salad salad = new Salad(1, "Грецький");
            salad.addVegetable(new FruitVegetable(1, "Помідор", 100, 18));
            String str = salad.toString();
            assert str.contains("Грецький") && str.contains("1");
            System.out.println("[PASS] toString");
            passed++;
        } catch (AssertionError e) {
            System.out.println("[FAIL] toString");
            failed++;
        }

        System.out.println("Result: " + passed + "/" + (passed + failed) + " passed\n");
    }
}