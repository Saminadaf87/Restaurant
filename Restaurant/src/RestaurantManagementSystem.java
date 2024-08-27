import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RestaurantManagementSystem {
    private List<Table> tables;
    private List<MenuItem> menu;

    public RestaurantManagementSystem() {
        tables = new ArrayList<>();
        menu = new ArrayList<>();
        initializeMenu();
        initializeTables();
    }

    private void initializeMenu() {
        menu.add(new MenuItem("Burger", 8.99));
        menu.add(new MenuItem("Pizza", 12.99));
        menu.add(new MenuItem("Salad", 6.99));
        menu.add(new MenuItem("Soda", 1.99));
    }

    private void initializeTables() {
        for (int i = 1; i <= 10; i++) {
            tables.add(new Table(i));
        }
    }

    public void displayMenu() {
        System.out.println("Menu:");
        for (int i = 0; i < menu.size(); i++) {
            MenuItem item = menu.get(i);
            System.out.println((i + 1) + ". " + item.getName() + " - $" + item.getPrice());
        }
    }

    public void takeOrder(int tableNumber) {
        Table table = getTableByNumber(tableNumber);
        if (table == null) {
            System.out.println("Invalid table number.");
            return;
        }

        Scanner scanner = new Scanner(System.in);
        List<MenuItem> orderItems = new ArrayList<>();

        System.out.println("Enter the item numbers to add to the order (0 to finish):");
        while (true) {
            int itemNumber = scanner.nextInt();
            if (itemNumber == 0) {
                break;
            } else if (itemNumber > 0 && itemNumber <= menu.size()) {
                MenuItem item = menu.get(itemNumber - 1);
                orderItems.add(item);
            } else {
                System.out.println("Invalid item number.");
            }
        }

        if (!orderItems.isEmpty()) {
            Order order = new Order(orderItems);
            table.addOrder(order);
            System.out.println("Order added successfully.");
        }
    }

    public void generateBill(int tableNumber) {
        Table table = getTableByNumber(tableNumber);
        if (table == null) {
            System.out.println("Invalid table number.");
            return;
        }

        System.out.println("Bill for Table " + tableNumber + ":");
        for (Order order : table.getOrders()) {
            for (MenuItem item : order.getItems()) {
                System.out.println(item.getName() + " - $" + item.getPrice());
            }
        }
        System.out.println("Total: $" + table.getTotalBill());
        table.clearOrders();
    }

    private Table getTableByNumber(int tableNumber) {
        for (Table table : tables) {
            if (table.getTableNumber() == tableNumber) {
                return table;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        RestaurantManagementSystem rms = new RestaurantManagementSystem();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Display Menu");
            System.out.println("2. Take Order");
            System.out.println("3. Generate Bill");
            System.out.println("4. Exit");

            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    rms.displayMenu();
                    break;
                case 2:
                    System.out.print("Enter table number: ");
                    int tableNumber = scanner.nextInt();
                    rms.takeOrder(tableNumber);
                    break;
                case 3:
                    System.out.print("Enter table number: ");
                    tableNumber = scanner.nextInt();
                    rms.generateBill(tableNumber);
                    break;
                case 4:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}
