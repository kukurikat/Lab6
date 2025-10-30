package model;

public class LeafyVegetable extends Vegetable {

    public LeafyVegetable(int id, String name, double weight, double caloriesPer100g) {
        super(id, name, weight, caloriesPer100g);
    }

    @Override
    public String getVegetableType() {
        return "Листяний";
    }
}