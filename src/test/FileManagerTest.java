package test;

import model.*;
import util.FileManager;
import java.io.File;
import java.util.ArrayList;

public class FileManagerTest {

    public static void main(String[] args) {
        int passed = 0;
        int failed = 0;
        String vegFile = "test_veg.txt";
        String saladFile = "test_salad.txt";

        System.out.println("=== FileManagerTest ===");

        try {
            FileManager fm = new FileManager();
            ArrayList<Vegetable> veggies = new ArrayList<>();
            veggies.add(new FruitVegetable(1, "Помідор", 100, 18));
            veggies.add(new RootVegetable(2, "Морква", 150, 41));
            fm.saveVegetablesToFile(veggies, vegFile);
            assert new File(vegFile).exists();
            System.out.println("[PASS] saveVegetablesToFile");
            passed++;
        } catch (AssertionError e) {
            System.out.println("[FAIL] saveVegetablesToFile");
            failed++;
        }

        try {
            FileManager fm = new FileManager();
            ArrayList<Vegetable> veggies = new ArrayList<>();
            veggies.add(new FruitVegetable(1, "Помідор", 100, 18));
            veggies.add(new RootVegetable(2, "Морква", 150, 41));
            veggies.add(new LeafyVegetable(3, "Салат", 50, 14));
            fm.saveVegetablesToFile(veggies, vegFile);
            ArrayList<Vegetable> loaded = fm.loadVegetablesFromFile(vegFile);
            assert loaded.size() == 3 && loaded.get(0).getName().equals("Помідор")
                    && loaded.get(1).getName().equals("Морква") && loaded.get(2).getName().equals("Салат");
            System.out.println("[PASS] loadVegetablesFromFile");
            passed++;
        } catch (AssertionError e) {
            System.out.println("[FAIL] loadVegetablesFromFile");
            failed++;
        }

        try {
            FileManager fm = new FileManager();
            ArrayList<Vegetable> veggies = new ArrayList<>();
            veggies.add(new FruitVegetable(1, "Помідор", 100, 18));
            veggies.add(new RootVegetable(2, "Морква", 150, 41));
            veggies.add(new LeafyVegetable(3, "Салат", 50, 14));
            fm.saveVegetablesToFile(veggies, vegFile);
            ArrayList<Vegetable> loaded = fm.loadVegetablesFromFile(vegFile);
            assert loaded.get(0) instanceof FruitVegetable && loaded.get(1) instanceof RootVegetable
                    && loaded.get(2) instanceof LeafyVegetable;
            System.out.println("[PASS] loadVegetablesFromFile check types");
            passed++;
        } catch (AssertionError e) {
            System.out.println("[FAIL] loadVegetablesFromFile check types");
            failed++;
        }

        try {
            FileManager fm = new FileManager();
            ArrayList<Vegetable> loaded = fm.loadVegetablesFromFile("nonexistent.txt");
            assert loaded.size() == 0;
            System.out.println("[PASS] loadVegetablesFromFile non-existent");
            passed++;
        } catch (AssertionError e) {
            System.out.println("[FAIL] loadVegetablesFromFile non-existent");
            failed++;
        }

        try {
            FileManager fm = new FileManager();
            Salad salad = new Salad(1, "Грецький");
            salad.addVegetable(new FruitVegetable(1, "Помідор", 100, 18));
            salad.addVegetable(new FruitVegetable(2, "Огірок", 150, 15));
            fm.saveSaladToFile(salad, saladFile);
            assert new File(saladFile).exists();
            System.out.println("[PASS] saveSaladToFile");
            passed++;
        } catch (AssertionError e) {
            System.out.println("[FAIL] saveSaladToFile");
            failed++;
        }

        try {
            FileManager fm = new FileManager();
            Salad salad = new Salad(1, "Грецький");
            salad.addVegetable(new FruitVegetable(1, "Помідор", 100, 18));
            salad.addVegetable(new RootVegetable(2, "Морква", 150, 41));
            fm.saveSaladToFile(salad, saladFile);
            Salad loaded = fm.loadSaladFromFile(saladFile);
            assert loaded != null && loaded.getName().equals("Грецький") && loaded.getId() == 1
                    && loaded.getVegetableCount() == 2;
            System.out.println("[PASS] loadSaladFromFile");
            passed++;
        } catch (AssertionError e) {
            System.out.println("[FAIL] loadSaladFromFile");
            failed++;
        }

        try {
            FileManager fm = new FileManager();
            Salad salad = new Salad(1, "Грецький");
            salad.addVegetable(new FruitVegetable(1, "Помідор", 100, 18));
            salad.addVegetable(new RootVegetable(2, "Морква", 150, 41));
            fm.saveSaladToFile(salad, saladFile);
            Salad loaded = fm.loadSaladFromFile(saladFile);
            ArrayList<Vegetable> v = loaded.getVegetables();
            assert v.get(0).getName().equals("Помідор") && v.get(0).getWeight() == 100
                    && v.get(1).getName().equals("Морква") && v.get(1).getWeight() == 150;
            System.out.println("[PASS] loadSaladFromFile check vegetables");
            passed++;
        } catch (AssertionError e) {
            System.out.println("[FAIL] loadSaladFromFile check vegetables");
            failed++;
        }

        try {
            FileManager fm = new FileManager();
            Salad salad = new Salad(1, "Грецький");
            salad.addVegetable(new FruitVegetable(1, "Помідор", 100, 18));
            salad.addVegetable(new RootVegetable(2, "Морква", 150, 41));
            salad.addVegetable(new LeafyVegetable(3, "Салат", 50, 14));
            fm.saveSaladToFile(salad, saladFile);
            Salad loaded = fm.loadSaladFromFile(saladFile);
            ArrayList<Vegetable> v = loaded.getVegetables();
            assert v.get(0) instanceof FruitVegetable && v.get(1) instanceof RootVegetable
                    && v.get(2) instanceof LeafyVegetable;
            System.out.println("[PASS] loadSaladFromFile check types");
            passed++;
        } catch (AssertionError e) {
            System.out.println("[FAIL] loadSaladFromFile check types");
            failed++;
        }

        try {
            FileManager fm = new FileManager();
            Salad loaded = fm.loadSaladFromFile("nonexistent.txt");
            assert loaded == null;
            System.out.println("[PASS] loadSaladFromFile non-existent");
            passed++;
        } catch (AssertionError e) {
            System.out.println("[FAIL] loadSaladFromFile non-existent");
            failed++;
        }

        try {
            FileManager fm = new FileManager();
            Salad salad = new Salad(5, "Порожній");
            fm.saveSaladToFile(salad, saladFile);
            Salad loaded = fm.loadSaladFromFile(saladFile);
            assert loaded != null && loaded.getName().equals("Порожній") && loaded.getVegetableCount() == 0;
            System.out.println("[PASS] saveAndLoadEmptySalad");
            passed++;
        } catch (AssertionError e) {
            System.out.println("[FAIL] saveAndLoadEmptySalad");
            failed++;
        }

        try {
            FileManager fm = new FileManager();
            ArrayList<Vegetable> veggies = new ArrayList<>();
            fm.saveVegetablesToFile(veggies, vegFile);
            ArrayList<Vegetable> loaded = fm.loadVegetablesFromFile(vegFile);
            assert loaded.size() == 0;
            System.out.println("[PASS] saveEmptyVegetableList");
            passed++;
        } catch (AssertionError e) {
            System.out.println("[FAIL] saveEmptyVegetableList");
            failed++;
        }

        new File(vegFile).delete();
        new File(saladFile).delete();

        System.out.println("Result: " + passed + "/" + (passed + failed) + " passed\n");
    }
}