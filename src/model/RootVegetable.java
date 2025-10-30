package model;

public class RootVegetable extends Vegetable {

    public RootVegetable(int id, String name, double weight, double caloriesPer100g) {
        super(id, name, weight, caloriesPer100g);
    }

    @Override
    public String getVegetableType() {
        return "Кореневий";
    }
}