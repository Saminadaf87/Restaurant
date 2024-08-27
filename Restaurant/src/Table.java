import java.util.ArrayList;
import java.util.List;

public class Table {
    private int tableNumber;
    private List<Order> orders;

    public Table(int tableNumber) {
        this.tableNumber = tableNumber;
        this.orders = new ArrayList<>();
    }

    public int getTableNumber() {
        return tableNumber;
    }

    public void addOrder(Order order) {
        orders.add(order);
    }

    public List<Order> getOrders() {
        return orders;
    }

    public double getTotalBill() {
        double total = 0;
        for (Order order : orders) {
            total += order.getTotalPrice();
        }
        return total;
    }

    public void clearOrders() {
        orders.clear();
    }
}
