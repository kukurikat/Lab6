package model;

import java.util.ArrayList;

public class Salad {
    private int id;
    private String name;
    private ArrayList<Vegetable> vegetables;

    public Salad(int id, String name) {
        this.id = id;
        this.name = name;
        this.vegetables = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Vegetable> getVegetables() {
        return vegetables;
    }

    public void addVegetable(Vegetable vegetable) {
        vegetables.add(vegetable);
    }

    public void removeVegetable(int index) {
        if (index >= 0 && index < vegetables.size()) {
            vegetables.remove(index);
        }
    }

    public void removeAllVegetables() {
        vegetables.clear();
    }

    public double calculateTotalCalories() {
        double total = 0;
        for (Vegetable veg : vegetables) {
            total += veg.calculateTotalCalories();
        }
        return total;
    }

    public void sortVegetablesByWeight() {
        vegetables.sort((v1, v2) -> Double.compare(v1.getWeight(), v2.getWeight()));
    }

    public void sortVegetablesByCalories() {
        vegetables.sort((v1, v2) -> Double.compare(v1.calculateTotalCalories(), v2.calculateTotalCalories()));
    }

    public ArrayList<Vegetable> findVegetablesByCalorieRange(double min, double max) {
        ArrayList<Vegetable> result = new ArrayList<>();
        for (Vegetable veg : vegetables) {
            double calories = veg.getCaloriesPer100g();
            if (calories >= min && calories <= max) {
                result.add(veg);
            }
        }
        return result;
    }

    public int getVegetableCount() {
        return vegetables.size();
    }

    @Override
    public String toString() {
        return String.format("ID: %d, Назва: %s, Кількість овочів: %d, Загальні калорії: %.2f",
                id, name, vegetables.size(), calculateTotalCalories());
    }
}