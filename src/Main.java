import model.*;
import repository.*;
import service.*;
import util.*;
import java.util.*;

interface   ICommand {
    void execute();
    String getDescription();
}

class CreateSaladCommand implements ICommand {
    private SaladService saladService;
    private Scanner scanner;

    public CreateSaladCommand(SaladService saladService, Scanner scanner) {
        this.saladService = saladService;
        this.scanner = scanner;
    }

    @Override
    public void execute() {
        System.out.print("\nВведіть назву салату: ");
        String name = scanner.nextLine();
        System.out.print("Введіть ID салату: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Salad salad = saladService.createSalad(name, id);
        System.out.println("Салат '" + name + "' успішно створено!");
    }

    @Override
    public String getDescription() {
        return "Створити новий салат";
    }
}

class AddVegetableToSaladCommand implements ICommand {
    private SaladService saladService;
    private VegetableRepository vegetableRepository;
    private Scanner scanner;

    public AddVegetableToSaladCommand(SaladService saladService, VegetableRepository vegetableRepository, Scanner scanner) {
        this.saladService = saladService;
        this.vegetableRepository = vegetableRepository;
        this.scanner = scanner;
    }

    @Override
    public void execute() {
        System.out.print("\nВведіть ID салату: ");
        int saladId = scanner.nextInt();
        scanner.nextLine();

        Salad salad = saladService.getSaladById(saladId);
        if (salad == null) {
            System.out.println("Салат не знайдено!");
            return;
        }

        System.out.println("\nДоступні овочі:");
        for (Vegetable veg : vegetableRepository.getAllVegetables()) {
            System.out.println(veg);
        }

        System.out.print("\nВведіть ID овоча: ");
        int vegId = scanner.nextInt();
        System.out.print("Введіть вагу (г): ");
        double weight = scanner.nextDouble();
        scanner.nextLine();

        saladService.addVegetableToSalad(salad, vegId, weight);
        System.out.println("Овоч додано до салату!");
    }

    @Override
    public String getDescription() {
        return "Додати овоч до салату";
    }
}

class RemoveVegetableFromSaladCommand implements ICommand {
    private SaladService saladService;
    private Scanner scanner;

    public RemoveVegetableFromSaladCommand(SaladService saladService, Scanner scanner) {
        this.saladService = saladService;
        this.scanner = scanner;
    }

    @Override
    public void execute() {
        System.out.print("\nВведіть ID салату: ");
        int saladId = scanner.nextInt();
        scanner.nextLine();

        Salad salad = saladService.getSaladById(saladId);
        if (salad == null) {
            System.out.println("Салат не знайдено!");
            return;
        }

        saladService.displayAllVegetablesInSalad(salad);

        System.out.print("\nВведіть номер овоча для видалення: ");
        int index = scanner.nextInt() - 1;
        scanner.nextLine();

        saladService.removeVegetableFromSalad(salad, index);
        System.out.println("Овоч видалено з салату!");
    }

    @Override
    public String getDescription() {
        return "Видалити овоч із салату";
    }
}

class DisplaySaladCommand implements ICommand {
    private SaladService saladService;
    private Scanner scanner;

    public DisplaySaladCommand(SaladService saladService, Scanner scanner) {
        this.saladService = saladService;
        this.scanner = scanner;
    }

    @Override
    public void execute() {
        System.out.print("\nВведіть ID салату: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Salad salad = saladService.getSaladById(id);
        saladService.displaySalad(salad);
    }

    @Override
    public String getDescription() {
        return "Показати інформацію про салат";
    }
}

class DisplayAllVegetablesInSaladCommand implements ICommand {
    private SaladService saladService;
    private Scanner scanner;

    public DisplayAllVegetablesInSaladCommand(SaladService saladService, Scanner scanner) {
        this.saladService = saladService;
        this.scanner = scanner;
    }

    @Override
    public void execute() {
        System.out.print("\nВведіть ID салату: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Salad salad = saladService.getSaladById(id);
        saladService.displayAllVegetablesInSalad(salad);
    }

    @Override
    public String getDescription() {
        return "Вивести всі овочі салату";
    }
}

class CalculateCaloriesCommand implements ICommand {
    private SaladService saladService;
    private Scanner scanner;

    public CalculateCaloriesCommand(SaladService saladService, Scanner scanner) {
        this.saladService = saladService;
        this.scanner = scanner;
    }

    @Override
    public void execute() {
        System.out.print("\nВведіть ID салату: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Salad salad = saladService.getSaladById(id);
        if (salad != null) {
            double calories = saladService.calculateCalories(salad);
            System.out.printf("Загальна калорійність салату: %.2f ккал\n", calories);
        } else {
            System.out.println("Салат не знайдено!");
        }
    }

    @Override
    public String getDescription() {
        return "Розрахувати калорії салату";
    }
}

class SortVegetablesByCaloriesInSaladCommand implements ICommand {
    private SaladService saladService;
    private Scanner scanner;

    public SortVegetablesByCaloriesInSaladCommand(SaladService saladService, Scanner scanner) {
        this.saladService = saladService;
        this.scanner = scanner;
    }

    @Override
    public void execute() {
        System.out.print("\nВведіть ID салату: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Salad salad = saladService.getSaladById(id);
        if (salad != null) {
            saladService.sortSaladByCalories(salad, "asc");
            System.out.println("Овочі відсортовано за калоріями!");
            saladService.displayAllVegetablesInSalad(salad);
        } else {
            System.out.println("Салат не знайдено!");
        }
    }

    @Override
    public String getDescription() {
        return "Сортувати овочі за калоріями";
    }
}

class SortVegetablesByWeightInSaladCommand implements ICommand {
    private SaladService saladService;
    private Scanner scanner;

    public SortVegetablesByWeightInSaladCommand(SaladService saladService, Scanner scanner) {
        this.saladService = saladService;
        this.scanner = scanner;
    }

    @Override
    public void execute() {
        System.out.print("\nВведіть ID салату: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Salad salad = saladService.getSaladById(id);
        if (salad != null) {
            saladService.sortSaladByWeight(salad);
            System.out.println("Овочі відсортовано за вагою!");
            saladService.displayAllVegetablesInSalad(salad);
        } else {
            System.out.println("Салат не знайдено!");
        }
    }

    @Override
    public String getDescription() {
        return "Сортувати овочі за вагою";
    }
}

class DeleteSaladCommand implements ICommand {
    private SaladService saladService;
    private Scanner scanner;

    public DeleteSaladCommand(SaladService saladService, Scanner scanner) {
        this.saladService = saladService;
        this.scanner = scanner;
    }

    @Override
    public void execute() {
        System.out.print("\nВведіть ID салату для видалення: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        saladService.deleteSalad(id);
        System.out.println("Салат видалено!");
    }

    @Override
    public String getDescription() {
        return "Видалити салат";
    }
}

class DisplayAllVegetablesCommand implements ICommand {
    private VegetableRepository vegetableRepository;

    public DisplayAllVegetablesCommand(VegetableRepository vegetableRepository) {
        this.vegetableRepository = vegetableRepository;
    }

    @Override
    public void execute() {
        System.out.println("\n=== Всі доступні овочі ===");
        for (Vegetable veg : vegetableRepository.getAllVegetables()) {
            System.out.println(veg);
        }
    }

    @Override
    public String getDescription() {
        return "Показати всі доступні овочі";
    }
}

class AddVegetableToRepositoryCommand implements ICommand {
    private VegetableRepository vegetableRepository;
    private Scanner scanner;

    public AddVegetableToRepositoryCommand(VegetableRepository vegetableRepository, Scanner scanner) {
        this.vegetableRepository = vegetableRepository;
        this.scanner = scanner;
    }

    @Override
    public void execute() {
        System.out.print("\nВведіть ID овоча: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Введіть назву овоча: ");
        String name = scanner.nextLine();
        System.out.print("Введіть калорії на 100г: ");
        double calories = scanner.nextDouble();
        scanner.nextLine();
        System.out.print("Виберіть тип (1-Плодовий, 2-Кореневий, 3-Листяний): ");
        int type = scanner.nextInt();
        scanner.nextLine();

        Vegetable veg;
        if (type == 1) {
            veg = new FruitVegetable(id, name, 0, calories);
        } else if (type == 2) {
            veg = new RootVegetable(id, name, 0, calories);
        } else {
            veg = new LeafyVegetable(id, name, 0, calories);
        }

        vegetableRepository.addVegetable(veg);
        System.out.println("Овоч додано до бази!");
    }

    @Override
    public String getDescription() {
        return "Додати новий овоч до бази";
    }
}

class UpdateVegetableCommand implements ICommand {
    private VegetableRepository vegetableRepository;
    private Scanner scanner;

    public UpdateVegetableCommand(VegetableRepository vegetableRepository, Scanner scanner) {
        this.vegetableRepository = vegetableRepository;
        this.scanner = scanner;
    }

    @Override
    public void execute() {
        System.out.print("\nВведіть ID овоча для оновлення: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Vegetable existing = vegetableRepository.getVegetableById(id);
        if (existing == null) {
            System.out.println("Овоч не знайдено!");
            return;
        }

        System.out.print("Введіть нову назву овоча: ");
        String name = scanner.nextLine();
        System.out.print("Введіть нові калорії на 100г: ");
        double calories = scanner.nextDouble();
        scanner.nextLine();

        Vegetable updated;
        if (existing instanceof FruitVegetable) {
            updated = new FruitVegetable(id, name, 0, calories);
        } else if (existing instanceof RootVegetable) {
            updated = new RootVegetable(id, name, 0, calories);
        } else {
            updated = new LeafyVegetable(id, name, 0, calories);
        }

        vegetableRepository.updateVegetable(id, updated);
        System.out.println("Інформацію про овоч оновлено!");
    }

    @Override
    public String getDescription() {
        return "Змінити інформацію про овоч";
    }
}

class DeleteVegetableCommand implements ICommand {
    private VegetableRepository vegetableRepository;
    private Scanner scanner;

    public DeleteVegetableCommand(VegetableRepository vegetableRepository, Scanner scanner) {
        this.vegetableRepository = vegetableRepository;
        this.scanner = scanner;
    }

    @Override
    public void execute() {
        System.out.print("\nВведіть ID овоча для видалення: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        boolean removed = vegetableRepository.removeVegetable(id);
        if (removed) {
            System.out.println("Овоч видалено з бази!");
        } else {
            System.out.println("Овоч не знайдено!");
        }
    }

    @Override
    public String getDescription() {
        return "Видалити овоч з бази";
    }
}

class SaveVegetablesToFileCommand implements ICommand {
    private VegetableRepository vegetableRepository;
    private FileManager fileManager;
    private Scanner scanner;

    public SaveVegetablesToFileCommand(VegetableRepository vegetableRepository, FileManager fileManager, Scanner scanner) {
        this.vegetableRepository = vegetableRepository;
        this.fileManager = fileManager;
        this.scanner = scanner;
    }

    @Override
    public void execute() {
        System.out.print("\nВведіть назву файлу: ");
        String filename = scanner.nextLine();
        fileManager.saveVegetablesToFile(vegetableRepository.getAllVegetables(), filename);
    }

    @Override
    public String getDescription() {
        return "Зберегти овочі у файл";
    }
}

class LoadVegetablesFromFileCommand implements ICommand {
    private VegetableRepository vegetableRepository;
    private FileManager fileManager;
    private Scanner scanner;

    public LoadVegetablesFromFileCommand(VegetableRepository vegetableRepository, FileManager fileManager, Scanner scanner) {
        this.vegetableRepository = vegetableRepository;
        this.fileManager = fileManager;
        this.scanner = scanner;
    }

    @Override
    public void execute() {
        System.out.print("\nВведіть назву файлу: ");
        String filename = scanner.nextLine();
        ArrayList<Vegetable> vegetables = fileManager.loadVegetablesFromFile(filename);

        vegetableRepository.clear();
        for (Vegetable veg : vegetables) {
            vegetableRepository.addVegetable(veg);
        }
    }

    @Override
    public String getDescription() {
        return "Завантажити овочі з файлу";
    }
}

class SaveToFileCommand implements ICommand {
    private SaladService saladService;
    private FileManager fileManager;
    private Scanner scanner;

    public SaveToFileCommand(SaladService saladService, FileManager fileManager, Scanner scanner) {
        this.saladService = saladService;
        this.fileManager = fileManager;
        this.scanner = scanner;
    }

    @Override
    public void execute() {
        System.out.print("\nВведіть ID салату: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Salad salad = saladService.getSaladById(id);
        if (salad == null) {
            System.out.println("Салат не знайдено!");
            return;
        }

        System.out.print("Введіть назву файлу: ");
        String filename = scanner.nextLine();
        fileManager.saveSaladToFile(salad, filename);
    }

    @Override
    public String getDescription() {
        return "Зберегти салат у файл";
    }
}

class LoadFromFileCommand implements ICommand {
    private SaladService saladService;
    private FileManager fileManager;
    private Scanner scanner;

    public LoadFromFileCommand(SaladService saladService, FileManager fileManager, Scanner scanner) {
        this.saladService = saladService;
        this.fileManager = fileManager;
        this.scanner = scanner;
    }

    @Override
    public void execute() {
        System.out.print("\nВведіть назву файлу: ");
        String filename = scanner.nextLine();
        Salad salad = fileManager.loadSaladFromFile(filename);

        if (salad != null) {
            saladService.createSalad(salad.getName(), salad.getId());
            Salad newSalad = saladService.getSaladById(salad.getId());
            for (Vegetable veg : salad.getVegetables()) {
                newSalad.addVegetable(veg);
            }
        }
    }

    @Override
    public String getDescription() {
        return "Завантажити салат з файлу";
    }
}

class OpenSubMenuCommand implements ICommand {
    private SubMenu subMenu;
    private String description;

    public OpenSubMenuCommand(SubMenu subMenu, String description) {
        this.subMenu = subMenu;
        this.description = description;
    }

    @Override
    public void execute() {
        subMenu.run();
    }

    @Override
    public String getDescription() {
        return description;
    }
}

class ExitCommand implements ICommand {
    @Override
    public void execute() {
        System.out.println("\nДякуємо за використання системи!");
        System.exit(0);
    }

    @Override
    public String getDescription() {
        return "Вихід з програми";
    }
}

class SubMenu {
    private String title;
    private Map<Integer, ICommand> commands;
    private Scanner scanner;

    public SubMenu(String title) {
        this.title = title;
        this.commands = new LinkedHashMap<>();
        this.scanner = new Scanner(System.in);
    }

    public void registerCommand(int key, ICommand command) {
        commands.put(key, command);
    }

    private void displayMenu() {
        System.out.println("\n" + title);
        System.out.println("=".repeat(title.length()));

        for (Map.Entry<Integer, ICommand> entry : commands.entrySet()) {
            System.out.printf("%d. %s\n", entry.getKey(), entry.getValue().getDescription());
        }

        System.out.println("0. Повернутися назад");
        System.out.print("\nВиберіть опцію: ");
    }

    public void run() {
        while (true) {
            try {
                displayMenu();
                int choice = scanner.nextInt();
                scanner.nextLine();

                if (choice == 0) {
                    return;
                }

                ICommand command = commands.get(choice);
                if (command != null) {
                    command.execute();
                } else {
                    System.out.println("\nНевірний вибір!");
                }
            } catch (InputMismatchException e) {
                System.out.println("\nПомилка! Введіть число.");
                scanner.nextLine();
            }
        }
    }
}

class ConsoleMenu {
    private Map<Integer, ICommand> commands;
    private Scanner scanner;

    public ConsoleMenu() {
        this.commands = new LinkedHashMap<>();
        this.scanner = new Scanner(System.in);
    }

    public void registerCommand(int key, ICommand command) {
        commands.put(key, command);
    }

    private void displayMenu() {
        System.out.println("\nСистема управління салатами");
        System.out.println("===========================");

        for (Map.Entry<Integer, ICommand> entry : commands.entrySet()) {
            if (entry.getKey() != 0) {
                System.out.printf("%d. %s\n", entry.getKey(), entry.getValue().getDescription());
            }
        }

        System.out.println("0. Вихід з програми");
        System.out.print("\nВиберіть опцію: ");
    }

    public void run() {
        while (true) {
            try {
                displayMenu();
                int choice = scanner.nextInt();
                scanner.nextLine();

                if (choice == 0) {
                    commands.get(0).execute();
                    break;
                }

                ICommand command = commands.get(choice);
                if (command != null) {
                    command.execute();
                } else {
                    System.out.println("\nНевірний вибір!");
                }
            } catch (InputMismatchException e) {
                System.out.println("\nПомилка! Введіть число.");
                scanner.nextLine();
            }
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        VegetableRepository vegetableRepository = new VegetableRepository();
        SaladRepository saladRepository = new SaladRepository();

        SaladService saladService = new SaladService(vegetableRepository, saladRepository);
        FileManager fileManager = new FileManager();

        SubMenu saladMenu = new SubMenu("Робота з салатами");
        saladMenu.registerCommand(1, new CreateSaladCommand(saladService, scanner));
        saladMenu.registerCommand(2, new AddVegetableToSaladCommand(saladService, vegetableRepository, scanner));
        saladMenu.registerCommand(3, new RemoveVegetableFromSaladCommand(saladService, scanner));
        saladMenu.registerCommand(4, new DisplaySaladCommand(saladService, scanner));
        saladMenu.registerCommand(5, new CalculateCaloriesCommand(saladService, scanner));
        saladMenu.registerCommand(6, new SortVegetablesByCaloriesInSaladCommand(saladService, scanner));
        saladMenu.registerCommand(7, new SortVegetablesByWeightInSaladCommand(saladService, scanner));
        saladMenu.registerCommand(8, new DeleteSaladCommand(saladService, scanner));

        SubMenu vegetableMenu = new SubMenu("Робота з овочами");
        vegetableMenu.registerCommand(1, new DisplayAllVegetablesCommand(vegetableRepository));
        vegetableMenu.registerCommand(2, new AddVegetableToRepositoryCommand(vegetableRepository, scanner));
        vegetableMenu.registerCommand(3, new UpdateVegetableCommand(vegetableRepository, scanner));
        vegetableMenu.registerCommand(4, new DeleteVegetableCommand(vegetableRepository, scanner));

        SubMenu fileMenu = new SubMenu("Робота з файлами");
        fileMenu.registerCommand(1, new SaveToFileCommand(saladService, fileManager, scanner));
        fileMenu.registerCommand(2, new LoadFromFileCommand(saladService, fileManager, scanner));
        fileMenu.registerCommand(3, new SaveVegetablesToFileCommand(vegetableRepository, fileManager, scanner));
        fileMenu.registerCommand(4, new LoadVegetablesFromFileCommand(vegetableRepository, fileManager, scanner));

        ConsoleMenu mainMenu = new ConsoleMenu();
        mainMenu.registerCommand(1, new OpenSubMenuCommand(saladMenu, "Дії з салатами"));
        mainMenu.registerCommand(2, new OpenSubMenuCommand(vegetableMenu, "Дії з овочами"));
        mainMenu.registerCommand(3, new OpenSubMenuCommand(fileMenu, "Дії з файлами"));
        mainMenu.registerCommand(0, new ExitCommand());

        mainMenu.run();
    }
}