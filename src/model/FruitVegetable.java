package model;

public class FruitVegetable extends Vegetable {

    public FruitVegetable(int id, String name, double weight, double caloriesPer100g) {
        super(id, name, weight, caloriesPer100g);
    }

    @Override
    public String getVegetableType() {
        return "Плодовий";
    }
}