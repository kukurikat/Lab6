package repository;

import model.FruitVegetable;
import model.LeafyVegetable;
import model.RootVegetable;
import model.Vegetable;

import java.util.ArrayList;

public class VegetableRepository {
    private ArrayList<Vegetable> vegetables;

    public VegetableRepository() {
        this.vegetables = new ArrayList<>();
        initializeDefaultVegetables();
    }

    private void initializeDefaultVegetables() {
        vegetables.add(new FruitVegetable(1, "Помідор", 0, 18));
        vegetables.add(new FruitVegetable(2, "Огірок", 0, 15));
        vegetables.add(new RootVegetable(3, "Морква", 0, 41));
        vegetables.add(new LeafyVegetable(4, "Капуста", 0, 25));
        vegetables.add(new RootVegetable(5, "Цибуля", 0, 40));
        vegetables.add(new FruitVegetable(6, "Перець", 0, 27));
        vegetables.add(new LeafyVegetable(7, "Салат", 0, 14));
    }

    public void addVegetable(Vegetable vegetable) {
        vegetables.add(vegetable);
    }

    public boolean removeVegetable(int id) {
        return vegetables.removeIf(v -> v.getId() == id);
    }

    public Vegetable getVegetableById(int id) {
        for (Vegetable veg : vegetables) {
            if (veg.getId() == id) {
                return veg;
            }
        }
        return null;
    }

    public boolean updateVegetable(int id, Vegetable updatedVegetable) {
        for (int i = 0; i < vegetables.size(); i++) {
            if (vegetables.get(i).getId() == id) {
                vegetables.set(i, updatedVegetable);
                return true;
            }
        }
        return false;
    }

    public ArrayList<Vegetable> getAllVegetables() {
        return new ArrayList<>(vegetables);
    }

    public Vegetable findVegetableByName(String name) {
        for (Vegetable veg : vegetables) {
            if (veg.getName().equalsIgnoreCase(name)) {
                return veg;
            }
        }
        return null;
    }

    public int getVegetableCount() {
        return vegetables.size();
    }

    public void clear() {
        vegetables.clear();
    }
}