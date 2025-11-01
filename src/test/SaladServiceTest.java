package test;

import model.*;
import repository.*;
import service.SaladService;
import java.util.ArrayList;

public class SaladServiceTest {

    public static void main(String[] args) {
        int passed = 0;
        int failed = 0;

        System.out.println("=== SaladServiceTest ===");

        try {
            SaladService service = new SaladService(new VegetableRepository(), new SaladRepository());
            Salad salad = service.createSalad("Грецький", 1);
            assert salad != null && salad.getName().equals("Грецький") && salad.getId() == 1;
            System.out.println("[PASS] createSalad");
            passed++;
        } catch (AssertionError e) {
            System.out.println("[FAIL] createSalad");
            failed++;
        }

        try {
            SaladService service = new SaladService(new VegetableRepository(), new SaladRepository());
            Salad salad = service.createSalad("Грецький", 1);
            service.addVegetableToSalad(salad, 1, 100);
            assert salad.getVegetableCount() == 1;
            System.out.println("[PASS] addVegetableToSalad");
            passed++;
        } catch (AssertionError e) {
            System.out.println("[FAIL] addVegetableToSalad");
            failed++;
        }

        try {
            SaladService service = new SaladService(new VegetableRepository(), new SaladRepository());
            Salad salad = service.createSalad("Грецький", 1);
            service.addVegetableToSalad(salad, 1, 100);
            service.addVegetableToSalad(salad, 3, 150);
            service.addVegetableToSalad(salad, 4, 50);
            assert salad.getVegetableCount() == 3;
            System.out.println("[PASS] addVegetableToSalad different types");
            passed++;
        } catch (AssertionError e) {
            System.out.println("[FAIL] addVegetableToSalad different types");
            failed++;
        }

        try {
            SaladService service = new SaladService(new VegetableRepository(), new SaladRepository());
            Salad salad = service.createSalad("Грецький", 1);
            service.addVegetableToSalad(salad, 999, 100);
            assert salad.getVegetableCount() == 0;
            System.out.println("[PASS] addVegetableToSalad non-existent");
            passed++;
        } catch (AssertionError e) {
            System.out.println("[FAIL] addVegetableToSalad non-existent");
            failed++;
        }

        try {
            SaladService service = new SaladService(new VegetableRepository(), new SaladRepository());
            Salad salad = service.createSalad("Грецький", 1);
            service.addVegetableToSalad(salad, 1, 100);
            service.addVegetableToSalad(salad, 2, 150);
            service.removeVegetableFromSalad(salad, 0);
            assert salad.getVegetableCount() == 1;
            System.out.println("[PASS] removeVegetableFromSalad");
            passed++;
        } catch (AssertionError e) {
            System.out.println("[FAIL] removeVegetableFromSalad");
            failed++;
        }

        try {
            SaladService service = new SaladService(new VegetableRepository(), new SaladRepository());
            Salad salad = service.createSalad("Грецький", 1);
            service.addVegetableToSalad(salad, 1, 100);
            service.addVegetableToSalad(salad, 2, 200);
            double calories = service.calculateCalories(salad);
            assert Math.abs(calories - 48.0) < 0.01;
            System.out.println("[PASS] calculateCalories");
            passed++;
        } catch (AssertionError e) {
            System.out.println("[FAIL] calculateCalories");
            failed++;
        }

        try {
            SaladService service = new SaladService(new VegetableRepository(), new SaladRepository());
            Salad salad = service.createSalad("Грецький", 1);
            service.addVegetableToSalad(salad, 3, 100);
            service.addVegetableToSalad(salad, 1, 100);
            service.sortSaladByCalories(salad, "asc");
            ArrayList<Vegetable> v = salad.getVegetables();
            assert v.get(0).getCaloriesPer100g() <= v.get(1).getCaloriesPer100g();
            System.out.println("[PASS] sortSaladByCalories");
            passed++;
        } catch (AssertionError e) {
            System.out.println("[FAIL] sortSaladByCalories");
            failed++;
        }

        try {
            SaladService service = new SaladService(new VegetableRepository(), new SaladRepository());
            Salad salad = service.createSalad("Грецький", 1);
            service.addVegetableToSalad(salad, 1, 200);
            service.addVegetableToSalad(salad, 2, 100);
            service.sortSaladByWeight(salad);
            ArrayList<Vegetable> v = salad.getVegetables();
            assert v.get(0).getWeight() <= v.get(1).getWeight();
            System.out.println("[PASS] sortSaladByWeight");
            passed++;
        } catch (AssertionError e) {
            System.out.println("[FAIL] sortSaladByWeight");
            failed++;
        }

        try {
            SaladService service = new SaladService(new VegetableRepository(), new SaladRepository());
            Salad salad = service.createSalad("Грецький", 1);
            service.addVegetableToSalad(salad, 1, 100);
            service.addVegetableToSalad(salad, 2, 100);
            service.addVegetableToSalad(salad, 3, 100);
            ArrayList<Vegetable> result = service.findVegetablesByCalorieRange(salad, 15, 20);
            assert result.size() == 2;
            System.out.println("[PASS] findVegetablesByCalorieRange");
            passed++;
        } catch (AssertionError e) {
            System.out.println("[FAIL] findVegetablesByCalorieRange");
            failed++;
        }

        try {
            SaladService service = new SaladService(new VegetableRepository(), new SaladRepository());
            service.createSalad("Грецький", 1);
            service.deleteSalad(1);
            assert service.getSaladById(1) == null;
            System.out.println("[PASS] deleteSalad");
            passed++;
        } catch (AssertionError e) {
            System.out.println("[FAIL] deleteSalad");
            failed++;
        }

        try {
            SaladService service = new SaladService(new VegetableRepository(), new SaladRepository());
            service.createSalad("Грецький", 1);
            Salad found = service.getSaladById(1);
            assert found != null && found.getName().equals("Грецький");
            System.out.println("[PASS] getSaladById");
            passed++;
        } catch (AssertionError e) {
            System.out.println("[FAIL] getSaladById");
            failed++;
        }

        try {
            SaladService service = new SaladService(new VegetableRepository(), new SaladRepository());
            Salad found = service.getSaladById(999);
            assert found == null;
            System.out.println("[PASS] getSaladById not found");
            passed++;
        } catch (AssertionError e) {
            System.out.println("[FAIL] getSaladById not found");
            failed++;
        }

        try {
            SaladService service = new SaladService(new VegetableRepository(), new SaladRepository());
            service.createSalad("Грецький", 1);
            service.createSalad("Цезар", 2);
            ArrayList<Salad> salads = service.getAllSalads();
            assert salads.size() == 2;
            System.out.println("[PASS] getAllSalads");
            passed++;
        } catch (AssertionError e) {
            System.out.println("[FAIL] getAllSalads");
            failed++;
        }

        try {
            SaladService service = new SaladService(new VegetableRepository(), new SaladRepository());
            assert service.deleteSaladInt() == false;
            System.out.println("[PASS] deleteSaladInt");
            passed++;
        } catch (AssertionError e) {
            System.out.println("[FAIL] deleteSaladInt");
            failed++;
        }

        System.out.println("Result: " + passed + "/" + (passed + failed) + " passed\n");
    }
}