package test;

import model.*;
import repository.VegetableRepository;
import java.util.ArrayList;

public class VegetableRepositoryTest {

    public static void main(String[] args) {
        int passed = 0;
        int failed = 0;

        System.out.println("=== VegetableRepositoryTest ===");

        try {
            VegetableRepository repo = new VegetableRepository();
            assert repo.getVegetableCount() == 7;
            System.out.println("[PASS] Initial vegetables");
            passed++;
        } catch (AssertionError e) {
            System.out.println("[FAIL] Initial vegetables");
            failed++;
        }

        try {
            VegetableRepository repo = new VegetableRepository();
            repo.addVegetable(new FruitVegetable(100, "Баклажан", 0, 25));
            assert repo.getVegetableCount() == 8;
            System.out.println("[PASS] addVegetable");
            passed++;
        } catch (AssertionError e) {
            System.out.println("[FAIL] addVegetable");
            failed++;
        }

        try {
            VegetableRepository repo = new VegetableRepository();
            boolean removed = repo.removeVegetable(1);
            assert removed == true && repo.getVegetableCount() == 6;
            System.out.println("[PASS] removeVegetable");
            passed++;
        } catch (AssertionError e) {
            System.out.println("[FAIL] removeVegetable");
            failed++;
        }

        try {
            VegetableRepository repo = new VegetableRepository();
            boolean removed = repo.removeVegetable(999);
            assert removed == false && repo.getVegetableCount() == 7;
            System.out.println("[PASS] removeVegetable non-existent");
            passed++;
        } catch (AssertionError e) {
            System.out.println("[FAIL] removeVegetable non-existent");
            failed++;
        }

        try {
            VegetableRepository repo = new VegetableRepository();
            Vegetable veg = repo.getVegetableById(1);
            assert veg != null && veg.getName().equals("Помідор");
            System.out.println("[PASS] getVegetableById");
            passed++;
        } catch (AssertionError e) {
            System.out.println("[FAIL] getVegetableById");
            failed++;
        }

        try {
            VegetableRepository repo = new VegetableRepository();
            Vegetable veg = repo.getVegetableById(999);
            assert veg == null;
            System.out.println("[PASS] getVegetableById not found");
            passed++;
        } catch (AssertionError e) {
            System.out.println("[FAIL] getVegetableById not found");
            failed++;
        }

        try {
            VegetableRepository repo = new VegetableRepository();
            boolean result = repo.updateVegetable(1, new FruitVegetable(1, "Червоний помідор", 0, 20));
            Vegetable veg = repo.getVegetableById(1);
            assert result == true && veg.getName().equals("Червоний помідор");
            System.out.println("[PASS] updateVegetable");
            passed++;
        } catch (AssertionError e) {
            System.out.println("[FAIL] updateVegetable");
            failed++;
        }

        try {
            VegetableRepository repo = new VegetableRepository();
            boolean result = repo.updateVegetable(999, new FruitVegetable(999, "Тест", 0, 20));
            assert result == false;
            System.out.println("[PASS] updateVegetable non-existent");
            passed++;
        } catch (AssertionError e) {
            System.out.println("[FAIL] updateVegetable non-existent");
            failed++;
        }

        try {
            VegetableRepository repo = new VegetableRepository();
            ArrayList<Vegetable> veggies = repo.getAllVegetables();
            assert veggies.size() == 7;
            System.out.println("[PASS] getAllVegetables");
            passed++;
        } catch (AssertionError e) {
            System.out.println("[FAIL] getAllVegetables");
            failed++;
        }

        try {
            VegetableRepository repo = new VegetableRepository();
            Vegetable veg = repo.findVegetableByName("Помідор");
            assert veg != null && veg.getId() == 1;
            System.out.println("[PASS] findVegetableByName");
            passed++;
        } catch (AssertionError e) {
            System.out.println("[FAIL] findVegetableByName");
            failed++;
        }

        try {
            VegetableRepository repo = new VegetableRepository();
            Vegetable veg = repo.findVegetableByName("помідор");
            assert veg != null && veg.getId() == 1;
            System.out.println("[PASS] findVegetableByName case insensitive");
            passed++;
        } catch (AssertionError e) {
            System.out.println("[FAIL] findVegetableByName case insensitive");
            failed++;
        }

        try {
            VegetableRepository repo = new VegetableRepository();
            Vegetable veg = repo.findVegetableByName("Банан");
            assert veg == null;
            System.out.println("[PASS] findVegetableByName not found");
            passed++;
        } catch (AssertionError e) {
            System.out.println("[FAIL] findVegetableByName not found");
            failed++;
        }

        try {
            VegetableRepository repo = new VegetableRepository();
            repo.clear();
            assert repo.getVegetableCount() == 0;
            System.out.println("[PASS] clear");
            passed++;
        } catch (AssertionError e) {
            System.out.println("[FAIL] clear");
            failed++;
        }

        try {
            VegetableRepository repo = new VegetableRepository();
            int initial = repo.getVegetableCount();
            repo.addVegetable(new FruitVegetable(100, "Тест", 0, 20));
            repo.removeVegetable(100);
            assert repo.getVegetableCount() == initial;
            System.out.println("[PASS] getVegetableCount after operations");
            passed++;
        } catch (AssertionError e) {
            System.out.println("[FAIL] getVegetableCount after operations");
            failed++;
        }

        try {
            VegetableRepository repo = new VegetableRepository();
            ArrayList<Vegetable> veggies = repo.getAllVegetables();
            boolean hasFruit = false, hasRoot = false, hasLeafy = false;
            for (Vegetable veg : veggies) {
                if (veg instanceof FruitVegetable) hasFruit = true;
                if (veg instanceof RootVegetable) hasRoot = true;
                if (veg instanceof LeafyVegetable) hasLeafy = true;
            }
            assert hasFruit && hasRoot && hasLeafy;
            System.out.println("[PASS] Contains different types");
            passed++;
        } catch (AssertionError e) {
            System.out.println("[FAIL] Contains different types");
            failed++;
        }

        System.out.println("Result: " + passed + "/" + (passed + failed) + " passed\n");
    }
}