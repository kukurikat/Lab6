package test;

import model.Salad;
import repository.SaladRepository;
import java.util.ArrayList;

public class SaladRepositoryTest {

    public static void main(String[] args) {
        int passed = 0;
        int failed = 0;

        System.out.println("=== SaladRepositoryTest ===");

        try {
            SaladRepository repo = new SaladRepository();
            assert repo.getSaladCount() == 0;
            System.out.println("[PASS] Initially empty");
            passed++;
        } catch (AssertionError e) {
            System.out.println("[FAIL] Initially empty");
            failed++;
        }

        try {
            SaladRepository repo = new SaladRepository();
            repo.addSalad(new Salad(1, "Грецький"));
            assert repo.getSaladCount() == 1;
            System.out.println("[PASS] addSalad");
            passed++;
        } catch (AssertionError e) {
            System.out.println("[FAIL] addSalad");
            failed++;
        }

        try {
            SaladRepository repo = new SaladRepository();
            repo.addSalad(new Salad(1, "Грецький"));
            repo.addSalad(new Salad(2, "Цезар"));
            repo.addSalad(new Salad(3, "Олів'є"));
            assert repo.getSaladCount() == 3;
            System.out.println("[PASS] addMultipleSalads");
            passed++;
        } catch (AssertionError e) {
            System.out.println("[FAIL] addMultipleSalads");
            failed++;
        }

        try {
            SaladRepository repo = new SaladRepository();
            repo.addSalad(new Salad(1, "Грецький"));
            boolean removed = repo.removeSalad(1);
            assert removed == true && repo.getSaladCount() == 0;
            System.out.println("[PASS] removeSalad");
            passed++;
        } catch (AssertionError e) {
            System.out.println("[FAIL] removeSalad");
            failed++;
        }

        try {
            SaladRepository repo = new SaladRepository();
            repo.addSalad(new Salad(1, "Грецький"));
            boolean removed = repo.removeSalad(999);
            assert removed == false && repo.getSaladCount() == 1;
            System.out.println("[PASS] removeSalad non-existent");
            passed++;
        } catch (AssertionError e) {
            System.out.println("[FAIL] removeSalad non-existent");
            failed++;
        }

        try {
            SaladRepository repo = new SaladRepository();
            repo.addSalad(new Salad(1, "Грецький"));
            Salad found = repo.getSaladById(1);
            assert found != null && found.getName().equals("Грецький");
            System.out.println("[PASS] getSaladById");
            passed++;
        } catch (AssertionError e) {
            System.out.println("[FAIL] getSaladById");
            failed++;
        }

        try {
            SaladRepository repo = new SaladRepository();
            Salad found = repo.getSaladById(999);
            assert found == null;
            System.out.println("[PASS] getSaladById not found");
            passed++;
        } catch (AssertionError e) {
            System.out.println("[FAIL] getSaladById not found");
            failed++;
        }

        try {
            SaladRepository repo = new SaladRepository();
            repo.addSalad(new Salad(1, "Грецький"));
            boolean result = repo.updateSalad(1, new Salad(1, "Грецький оновлений"));
            Salad found = repo.getSaladById(1);
            assert result == true && found.getName().equals("Грецький оновлений");
            System.out.println("[PASS] updateSalad");
            passed++;
        } catch (AssertionError e) {
            System.out.println("[FAIL] updateSalad");
            failed++;
        }

        try {
            SaladRepository repo = new SaladRepository();
            boolean result = repo.updateSalad(999, new Salad(999, "Тест"));
            assert result == false;
            System.out.println("[PASS] updateSalad non-existent");
            passed++;
        } catch (AssertionError e) {
            System.out.println("[FAIL] updateSalad non-existent");
            failed++;
        }

        try {
            SaladRepository repo = new SaladRepository();
            repo.addSalad(new Salad(1, "Грецький"));
            repo.addSalad(new Salad(2, "Цезар"));
            ArrayList<Salad> salads = repo.getAllSalads();
            assert salads.size() == 2;
            System.out.println("[PASS] getAllSalads");
            passed++;
        } catch (AssertionError e) {
            System.out.println("[FAIL] getAllSalads");
            failed++;
        }

        try {
            SaladRepository repo = new SaladRepository();
            ArrayList<Salad> salads = repo.getAllSalads();
            assert salads.size() == 0;
            System.out.println("[PASS] getAllSalads empty");
            passed++;
        } catch (AssertionError e) {
            System.out.println("[FAIL] getAllSalads empty");
            failed++;
        }

        try {
            SaladRepository repo = new SaladRepository();
            repo.addSalad(new Salad(1, "Грецький"));
            Salad found = repo.findSaladByName("Грецький");
            assert found != null && found.getId() == 1;
            System.out.println("[PASS] findSaladByName");
            passed++;
        } catch (AssertionError e) {
            System.out.println("[FAIL] findSaladByName");
            failed++;
        }

        try {
            SaladRepository repo = new SaladRepository();
            repo.addSalad(new Salad(1, "Грецький"));
            Salad found = repo.findSaladByName("грецький");
            assert found != null && found.getId() == 1;
            System.out.println("[PASS] findSaladByName case insensitive");
            passed++;
        } catch (AssertionError e) {
            System.out.println("[FAIL] findSaladByName case insensitive");
            failed++;
        }

        try {
            SaladRepository repo = new SaladRepository();
            Salad found = repo.findSaladByName("Неіснуючий");
            assert found == null;
            System.out.println("[PASS] findSaladByName not found");
            passed++;
        } catch (AssertionError e) {
            System.out.println("[FAIL] findSaladByName not found");
            failed++;
        }

        try {
            SaladRepository repo = new SaladRepository();
            repo.addSalad(new Salad(1, "Грецький"));
            repo.addSalad(new Salad(2, "Цезар"));
            repo.clear();
            assert repo.getSaladCount() == 0;
            System.out.println("[PASS] clear");
            passed++;
        } catch (AssertionError e) {
            System.out.println("[FAIL] clear");
            failed++;
        }

        try {
            SaladRepository repo = new SaladRepository();
            repo.addSalad(new Salad(1, "Грецький"));
            repo.addSalad(new Salad(2, "Цезар"));
            assert repo.getSaladCount() == 2;
            System.out.println("[PASS] getSaladCount");
            passed++;
        } catch (AssertionError e) {
            System.out.println("[FAIL] getSaladCount");
            failed++;
        }

        System.out.println("Result: " + passed + "/" + (passed + failed) + " passed\n");
    }
}