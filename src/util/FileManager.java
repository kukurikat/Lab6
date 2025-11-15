package util;

import model.*;

import java.io.*;
import java.util.ArrayList;

public class FileManager {

    public void saveVegetablesToFile(ArrayList<Vegetable> vegetables, String filename) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            for (Vegetable veg : vegetables) {
                writer.println(veg.getId() + "," + veg.getName() + "," +
                        veg.getWeight() + "," + veg.getCaloriesPer100g() + "," +
                        veg.getVegetableType());
            }
            System.out.println("Овочі успішно збережено у файл: " + filename);
        } catch (IOException e) {
            System.out.println("Помилка при збереженні овочів: " + e.getMessage());
        }
    }

    public ArrayList<Vegetable> loadVegetablesFromFile(String filename) {
        ArrayList<Vegetable> vegetables = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 5) {
                    int id = Integer.parseInt(parts[0].trim());
                    String name = parts[1].trim();
                    double weight = Double.parseDouble(parts[2].trim());
                    double calories = Double.parseDouble(parts[3].trim());
                    String type = parts[4].trim();

                    Vegetable veg;
                    if (type.equals("Плодовий")) {
                        veg = new FruitVegetable(id, name, weight, calories);
                    } else if (type.equals("Кореневий")) {
                        veg = new RootVegetable(id, name, weight, calories);
                    } else {
                        veg = new LeafyVegetable(id, name, weight, calories);
                    }
                    vegetables.add(veg);
                }
            }
            System.out.println("Овочі успішно завантажено з файлу: " + filename);
        } catch (FileNotFoundException e) {
            System.out.println("Файл не знайдено: " + filename);
        } catch (IOException e) {
            System.out.println("Помилка при завантаженні овочів: " + e.getMessage());
        }
        return vegetables;
    }

    public void saveSaladToFile(Salad salad, String filename) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            writer.println(salad.getId() + "," + salad.getName());
            for (Vegetable veg : salad.getVegetables()) {
                writer.println(veg.getId() + "," + veg.getName() + "," +
                        veg.getWeight() + "," + veg.getCaloriesPer100g() + "," +
                        veg.getVegetableType());
            }
            System.out.println("Салат успішно збережено у файл: " + filename);
        } catch (IOException e) {
            System.out.println("Помилка при збереженні салату: " + e.getMessage());
        }
    }

    public Salad loadSaladFromFile(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line = reader.readLine();
            if (line != null) {
                String[] parts = line.split(",");
                int id = Integer.parseInt(parts[0].trim());
                String name = parts[1].trim();
                Salad salad = new Salad(id, name);

                while ((line = reader.readLine()) != null) {
                    parts = line.split(",");
                    if (parts.length == 5) {
                        int vegId = Integer.parseInt(parts[0].trim());
                        String vegName = parts[1].trim();
                        double weight = Double.parseDouble(parts[2].trim());
                        double calories = Double.parseDouble(parts[3].trim());
                        String type = parts[4].trim();

                        Vegetable veg;
                        if (type.equals("Плодовий")) {
                            veg = new FruitVegetable(vegId, vegName, weight, calories);
                        } else if (type.equals("Кореневий")) {
                            veg = new RootVegetable(vegId, vegName, weight, calories);
                        } else {
                            veg = new LeafyVegetable(vegId, vegName, weight, calories);
                        }
                        salad.addVegetable(veg);
                    }
                }
                System.out.println("Салат успішно завантажено з файлу: " + filename);
                return salad;
            }
        } catch (FileNotFoundException e) {
            System.out.println("Файл не знайдено: " + filename);
        } catch (IOException e) {
            System.out.println("Помилка при завантаженні салату: " + e.getMessage());
        }
        return null;
    }
}