import java.util.List;

public class Order {
    private List<MenuItem> items;

    public Order(List<MenuItem> items) {
        this.items = items;
    }

    public double getTotalPrice() {
        double total = 0;
        for (MenuItem item : items) {
            total += item.getPrice();
        }
        return total;
    }

    public List<MenuItem> getItems() {
        return items;
    }
}
