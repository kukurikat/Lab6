package repository;

import model.Salad;
import java.util.ArrayList;

public class SaladRepository {
    private ArrayList<Salad> salads;

    public SaladRepository() {
        this.salads = new ArrayList<>();
    }

    public void addSalad(Salad salad) {
        salads.add(salad);
    }

    public boolean removeSalad(int id) {
        return salads.removeIf(s -> s.getId() == id);
    }

    public Salad getSaladById(int id) {
        for (Salad salad : salads) {
            if (salad.getId() == id) {
                return salad;
            }
        }
        return null;
    }

    public boolean updateSalad(int id, Salad updatedSalad) {
        for (int i = 0; i < salads.size(); i++) {
            if (salads.get(i).getId() == id) {
                salads.set(i, updatedSalad);
                return true;
            }
        }
        return false;
    }

    public ArrayList<Salad> getAllSalads() {
        return new ArrayList<>(salads);
    }

    public Salad findSaladByName(String name) {
        for (Salad salad : salads) {
            if (salad.getName().equalsIgnoreCase(name)) {
                return salad;
            }
        }
        return null;
    }

    public int getSaladCount() {
        return salads.size();
    }

    public void clear() {
        salads.clear();
    }
}