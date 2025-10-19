import java.util.*;

interface ICommand {
    void execute();
    String getDescription();
}

// Команди для роботи з салатами
class CreateSaladCommand implements ICommand {


    @Override
    public void execute() {
        System.out.println("\nРеалізувати createSalad()");
    }

    @Override
    public String getDescription() {
        return "Створити новий салат";
    }
}

class AddVegetableToSaladCommand implements ICommand {
    @Override
    public void execute() {
        System.out.println("\nРеалізувати addVegetableToSalad()");
    }

    @Override
    public String getDescription() {
        return "Додати овоч до салату";
    }
}

class RemoveVegetableFromSaladCommand implements ICommand {
    @Override
    public void execute() {
        System.out.println("\nРеалізувати removeVegetableFromSalad()");
    }

    @Override
    public String getDescription() {
        return "Видалити овоч із салату";
    }
}

class DisplaySaladCommand implements ICommand {
    @Override
    public void execute() {
        System.out.println("\nРеалізувати displaySalad()");
    }

    @Override
    public String getDescription() {
        return "Показати інформацію про салат";
    }
}

class DisplayAllVegetablesInSaladCommand implements ICommand {

    @Override
    public void execute() {
        System.out.println("\nРеалізувати displayAllVegetablesInSalad()");
    }

    @Override
    public String getDescription() {
        return "Вивести всі овочі салату";
    }
}

class CalculateCaloriesCommand implements ICommand {

    @Override
    public void execute() {
        System.out.println("\nРеалізувати calculateCalories()");
    }

    @Override
    public String getDescription() {
        return "Розрахувати калорії салату";
    }
}

class SortVegetablesByCaloriesInSaladCommand implements ICommand {

    @Override
    public void execute() {
        System.out.println("\nРеалізувати sortVegetablesByCaloriesInSalad()");
    }

    @Override
    public String getDescription() {
        return "Сортувати овочі за калоріями";
    }
}

class SortVegetablesByWeightInSaladCommand implements ICommand {


    @Override
    public void execute() {
        System.out.println("\nРеалізувати sortVegetablesByWeightInSalad()");
    }

    @Override
    public String getDescription() {
        return "Сортувати овочі за вагою";
    }
}

class DeleteSaladCommand implements ICommand {

    @Override
    public void execute() {
        System.out.println("\nРеалізувати deleteSalad()");
    }

    @Override
    public String getDescription() {
        return "Видалити салат";
    }
}

// Команди для роботи з овочами
class DisplayAllVegetablesCommand implements ICommand {

    @Override
    public void execute() {
        System.out.println("\nРеалізувати displayAllVegetables()");
    }

    @Override
    public String getDescription() {
        return "Показати всі доступні овочі";
    }
}

class AddVegetableToRepositoryCommand implements ICommand {

    @Override
    public void execute() {
        System.out.println("\nРеалізувати addVegetableToRepository()");
    }

    @Override
    public String getDescription() {
        return "Додати новий овоч до бази";
    }
}

class UpdateVegetableCommand implements ICommand {

    @Override
    public void execute() {
        System.out.println("\n Реалізувати updateVegetable()");
    }

    @Override
    public String getDescription() {
        return "Змінити інформацію про овоч";
    }
}

class DeleteVegetableCommand implements ICommand {

    @Override
    public void execute() {
        System.out.println("\nРеалізувати deleteVegetable()");
    }

    @Override
    public String getDescription() {
        return "Видалити овоч з бази";
    }
}

// Команди для роботи з файлами
class SaveVegetablesToFileCommand implements ICommand {

    @Override
    public void execute() {
        System.out.println("\nРеалізувати saveVegetablesToFile()");
    }

    @Override
    public String getDescription() {
        return "Зберегти овочі у файл";
    }
}

class LoadVegetablesFromFileCommand implements ICommand {

    @Override
    public void execute() {
        System.out.println("\nРеалізувати loadVegetablesFromFile()");
    }

    @Override
    public String getDescription() {
        return "Завантажити овочі з файлу";
    }
}

class SaveToFileCommand implements ICommand {

    @Override
    public void execute() {
        System.out.println("\nРеалізувати saveToFile()");
    }

    @Override
    public String getDescription() {
        return "Зберегти салат у файл";
    }
}

class LoadFromFileCommand implements ICommand {
    // додати FileManager

    @Override
    public void execute() {
        System.out.println("\nРеалізувати loadFromFile()");
    }

    @Override
    public String getDescription() {
        return "Завантажити салат з файлу";
    }
}

// Команда для відкриття підменю
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

// Команда виходу
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

// Підменю
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

// Головне меню
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

// Головна програма
public class Main {
    public static void main(String[] args) {
        // Коли будуть готові SaladService та FileManager
        // SaladService saladService = new SaladService(...);
        // FileManager fileManager = new FileManager();

        // Підменю для салатів
        SubMenu saladMenu = new SubMenu("Робота з салатами");
        saladMenu.registerCommand(1, new CreateSaladCommand());
        saladMenu.registerCommand(2, new AddVegetableToSaladCommand());
        saladMenu.registerCommand(3, new RemoveVegetableFromSaladCommand());
        saladMenu.registerCommand(4, new DisplaySaladCommand());
        saladMenu.registerCommand(5, new CalculateCaloriesCommand());
        saladMenu.registerCommand(6, new SortVegetablesByCaloriesInSaladCommand());
        saladMenu.registerCommand(7, new SortVegetablesByWeightInSaladCommand());
        saladMenu.registerCommand(8, new DeleteSaladCommand());

        // Підменю для овочів
        SubMenu vegetableMenu = new SubMenu("Робота з овочами");
        vegetableMenu.registerCommand(1, new DisplayAllVegetablesCommand());
        vegetableMenu.registerCommand(2, new AddVegetableToRepositoryCommand());
        vegetableMenu.registerCommand(3, new UpdateVegetableCommand());
        vegetableMenu.registerCommand(4, new DeleteVegetableCommand());

        // Підменю для файлів
        SubMenu fileMenu = new SubMenu("Робота з файлами");
        fileMenu.registerCommand(1, new SaveToFileCommand());
        fileMenu.registerCommand(2, new LoadFromFileCommand());
        fileMenu.registerCommand(3, new SaveVegetablesToFileCommand());
        fileMenu.registerCommand(4, new LoadVegetablesFromFileCommand());

        // Головне меню
        ConsoleMenu mainMenu = new ConsoleMenu();
        mainMenu.registerCommand(1, new OpenSubMenuCommand(saladMenu, "Дії з салатами"));
        mainMenu.registerCommand(2, new OpenSubMenuCommand(vegetableMenu, "Дії з овочами"));
        mainMenu.registerCommand(3, new OpenSubMenuCommand(fileMenu, "Дії з файлами"));
        mainMenu.registerCommand(0, new ExitCommand());

        mainMenu.run();
    }
}