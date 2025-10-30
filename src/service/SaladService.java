package service;

import model.*;
import repository.SaladRepository;
import repository.VegetableRepository;
import java.util.ArrayList;

public class SaladService {
    private VegetableRepository vegetableRepository;
    private SaladRepository saladRepository;

    public SaladService(VegetableRepository vegetableRepository, SaladRepository saladRepository) {
        this.vegetableRepository = vegetableRepository;
        this.saladRepository = saladRepository;
    }

    public Salad createSalad(String name, int id) {
        Salad salad = new Salad(id, name);
        saladRepository.addSalad(salad);
        return salad;
    }

    public void addVegetableToSalad(Salad salad, int vegetableId, double weight) {
        Vegetable template = vegetableRepository.getVegetableById(vegetableId);
        if (template != null) {
            Vegetable vegetable;
            if (template instanceof FruitVegetable) {
                vegetable = new FruitVegetable(template.getId(), template.getName(),
                        weight, template.getCaloriesPer100g());
            } else if (template instanceof RootVegetable) {
                vegetable = new RootVegetable(template.getId(), template.getName(),
                        weight, template.getCaloriesPer100g());
            } else {
                vegetable = new LeafyVegetable(template.getId(), template.getName(),
                        weight, template.getCaloriesPer100g());
            }
            salad.addVegetable(vegetable);
        }
    }

    public void removeVegetableFromSalad(Salad salad, int index) {
        salad.removeVegetable(index);
    }

    public void displaySalad(Salad salad) {
        if (salad == null) {
            System.out.println("Салат не знайдено!");
            return;
        }
        System.out.println("\n=== Інформація про салат ===");
        System.out.println(salad);
        System.out.println("\nОвочі в салаті:");
        if (salad.getVegetables().isEmpty()) {
            System.out.println("Салат порожній");
        } else {
            for (int i = 0; i < salad.getVegetables().size(); i++) {
                System.out.println((i + 1) + ". " + salad.getVegetables().get(i));
            }
        }
    }

    public void displayAllVegetablesInSalad(Salad salad) {
        if (salad == null || salad.getVegetables().isEmpty()) {
            System.out.println("Салат порожній!");
            return;
        }
        System.out.println("\n=== Овочі в салаті '" + salad.getName() + "' ===");
        for (int i = 0; i < salad.getVegetables().size(); i++) {
            System.out.println((i + 1) + ". " + salad.getVegetables().get(i));
        }
    }

    public double calculateCalories(Salad salad) {
        return salad.calculateTotalCalories();
    }

    public void sortSaladByCalories(Salad salad, String order) {
        salad.sortVegetablesByCalories();
    }

    public void sortSaladByWeight(Salad salad) {
        salad.sortVegetablesByWeight();
    }

    public ArrayList<Vegetable> findVegetablesByCalorieRange(Salad salad, double min, double max) {
        return salad.findVegetablesByCalorieRange(min, max);
    }

    public void deleteSalad(int id) {
        saladRepository.removeSalad(id);
    }

    public Salad getSaladById(int id) {
        return saladRepository.getSaladById(id);
    }

    public ArrayList<Salad> getAllSalads() {
        return saladRepository.getAllSalads();
    }

    public boolean deleteSaladInt() {
        return false;
    }
}