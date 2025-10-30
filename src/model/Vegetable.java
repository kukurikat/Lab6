package model;

public abstract class Vegetable {
    private int id;
    private String name;
    private double weight;
    private double caloriesPer100g;

    public Vegetable(int id, String name, double weight, double caloriesPer100g) {
        this.id = id;
        this.name = name;
        this.weight = weight;
        this.caloriesPer100g = caloriesPer100g;
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

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getCaloriesPer100g() {
        return caloriesPer100g;
    }

    public void setCaloriesPer100g(double caloriesPer100g) {
        this.caloriesPer100g = caloriesPer100g;
    }

    public double calculateTotalCalories() {
        return (weight / 100.0) * caloriesPer100g;
    }

    public abstract String getVegetableType();

    @Override
    public String toString() {
        return String.format("ID: %d, Назва: %s [%s], Вага: %.2f г, Калорії/100г: %.2f, Загальні калорії: %.2f",
                id, name, getVegetableType(), weight, caloriesPer100g, calculateTotalCalories());
    }
}