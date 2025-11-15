package test.java.test;

import model.*;
import util.FileManager;
import org.junit.jupiter.api.*;
import java.io.File;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("FileManager Tests")
public class FileManagerTest {

    private static final String VEG_FILE = "test_veg.txt";
    private static final String SALAD_FILE = "test_salad.txt";
    private FileManager fileManager;

    @BeforeEach
    void setUp() {
        fileManager = new FileManager();
    }

    @AfterEach
    void tearDown() {
        new File(VEG_FILE).delete();
        new File(SALAD_FILE).delete();
    }

    @Test
    @DisplayName("Save vegetables to file")
    void testSaveVegetablesToFile() {
        ArrayList<Vegetable> veggies = new ArrayList<>();
        veggies.add(new FruitVegetable(1, "Помідор", 100, 18));
        veggies.add(new RootVegetable(2, "Морква", 150, 41));

        fileManager.saveVegetablesToFile(veggies, VEG_FILE);
        assertTrue(new File(VEG_FILE).exists());
    }

    @Test
    @DisplayName("Load vegetables from file")
    void testLoadVegetablesFromFile() {
        ArrayList<Vegetable> veggies = new ArrayList<>();
        veggies.add(new FruitVegetable(1, "Помідор", 100, 18));
        veggies.add(new RootVegetable(2, "Морква", 150, 41));
        veggies.add(new LeafyVegetable(3, "Салат", 50, 14));

        fileManager.saveVegetablesToFile(veggies, VEG_FILE);
        ArrayList<Vegetable> loaded = fileManager.loadVegetablesFromFile(VEG_FILE);

        assertEquals(3, loaded.size());
        assertEquals("Помідор", loaded.get(0).getName());
        assertEquals("Морква", loaded.get(1).getName());
        assertEquals("Салат", loaded.get(2).getName());
    }

    @Test
    @DisplayName("Load vegetables from file - check types")
    void testLoadVegetablesFromFileCheckTypes() {
        ArrayList<Vegetable> veggies = new ArrayList<>();
        veggies.add(new FruitVegetable(1, "Помідор", 100, 18));
        veggies.add(new RootVegetable(2, "Морква", 150, 41));
        veggies.add(new LeafyVegetable(3, "Салат", 50, 14));

        fileManager.saveVegetablesToFile(veggies, VEG_FILE);
        ArrayList<Vegetable> loaded = fileManager.loadVegetablesFromFile(VEG_FILE);

        assertInstanceOf(FruitVegetable.class, loaded.get(0));
        assertInstanceOf(RootVegetable.class, loaded.get(1));
        assertInstanceOf(LeafyVegetable.class, loaded.get(2));
    }

    @Test
    @DisplayName("Load vegetables from non-existent file")
    void testLoadVegetablesFromFileNonExistent() {
        ArrayList<Vegetable> loaded = fileManager.loadVegetablesFromFile("nonexistent.txt");
        assertEquals(0, loaded.size());
    }

    @Test
    @DisplayName("Save salad to file")
    void testSaveSaladToFile() {
        Salad salad = new Salad(1, "Грецький");
        salad.addVegetable(new FruitVegetable(1, "Помідор", 100, 18));
        salad.addVegetable(new FruitVegetable(2, "Огірок", 150, 15));

        fileManager.saveSaladToFile(salad, SALAD_FILE);
        assertTrue(new File(SALAD_FILE).exists());
    }

    @Test
    @DisplayName("Load salad from file")
    void testLoadSaladFromFile() {
        Salad salad = new Salad(1, "Грецький");
        salad.addVegetable(new FruitVegetable(1, "Помідор", 100, 18));
        salad.addVegetable(new RootVegetable(2, "Морква", 150, 41));

        fileManager.saveSaladToFile(salad, SALAD_FILE);
        Salad loaded = fileManager.loadSaladFromFile(SALAD_FILE);

        assertNotNull(loaded);
        assertEquals("Грецький", loaded.getName());
        assertEquals(1, loaded.getId());
        assertEquals(2, loaded.getVegetableCount());
    }

    @Test
    @DisplayName("Load salad from file - check vegetables")
    void testLoadSaladFromFileCheckVegetables() {
        Salad salad = new Salad(1, "Грецький");
        salad.addVegetable(new FruitVegetable(1, "Помідор", 100, 18));
        salad.addVegetable(new RootVegetable(2, "Морква", 150, 41));

        fileManager.saveSaladToFile(salad, SALAD_FILE);
        Salad loaded = fileManager.loadSaladFromFile(SALAD_FILE);
        ArrayList<Vegetable> vegetables = loaded.getVegetables();

        assertEquals("Помідор", vegetables.get(0).getName());
        assertEquals(100, vegetables.get(0).getWeight());
        assertEquals("Морква", vegetables.get(1).getName());
        assertEquals(150, vegetables.get(1).getWeight());
    }

    @Test
    @DisplayName("Load salad from file - check types")
    void testLoadSaladFromFileCheckTypes() {
        Salad salad = new Salad(1, "Грецький");
        salad.addVegetable(new FruitVegetable(1, "Помідор", 100, 18));
        salad.addVegetable(new RootVegetable(2, "Морква", 150, 41));
        salad.addVegetable(new LeafyVegetable(3, "Салат", 50, 14));

        fileManager.saveSaladToFile(salad, SALAD_FILE);
        Salad loaded = fileManager.loadSaladFromFile(SALAD_FILE);
        ArrayList<Vegetable> vegetables = loaded.getVegetables();

        assertInstanceOf(FruitVegetable.class, vegetables.get(0));
        assertInstanceOf(RootVegetable.class, vegetables.get(1));
        assertInstanceOf(LeafyVegetable.class, vegetables.get(2));
    }

    @Test
    @DisplayName("Load salad from non-existent file")
    void testLoadSaladFromFileNonExistent() {
        Salad loaded = fileManager.loadSaladFromFile("nonexistent.txt");
        assertNull(loaded);
    }

    @Test
    @DisplayName("Save and load empty salad")
    void testSaveAndLoadEmptySalad() {
        Salad salad = new Salad(5, "Порожній");
        fileManager.saveSaladToFile(salad, SALAD_FILE);
        Salad loaded = fileManager.loadSaladFromFile(SALAD_FILE);

        assertNotNull(loaded);
        assertEquals("Порожній", loaded.getName());
        assertEquals(0, loaded.getVegetableCount());
    }

    @Test
    @DisplayName("Save empty vegetable list")
    void testSaveEmptyVegetableList() {
        ArrayList<Vegetable> veggies = new ArrayList<>();
        fileManager.saveVegetablesToFile(veggies, VEG_FILE);
        ArrayList<Vegetable> loaded = fileManager.loadVegetablesFromFile(VEG_FILE);
        assertEquals(0, loaded.size());
    }
}