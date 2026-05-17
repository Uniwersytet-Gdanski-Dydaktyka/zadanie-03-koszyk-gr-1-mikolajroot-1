import java.util.ArrayList;
import java.util.List;

public class Cart {

    private List<Product> items;

    public Cart() {

        this.items = new ArrayList<>();
    }

    public void addProduct(Product p) {
        if (p == null) {
            throw new IllegalArgumentException("Nie można dodać pustego produktu do koszyka.");
        }
        this.items.add(p);
    }

    public double calculateTotalBasePrice() {
        double totalPrice = 0;.
        for (Product item : this.items) {
            totalPrice += item.getPrice();
        }
        return totalPrice;
    }

    public double calculateTotalDiscountPrice() {
        double totalPrice = 0;
        for (Product item : this.items) {
            totalPrice += item.getDiscountPrice();
        }
        return totalPrice;
    }



    public List<Product> getItems() {
        return new ArrayList<>(this.items);
    }

    public void updateItems(List<Product> newItems) {
        if (newItems != null) {
            this.items = new ArrayList<>(newItems);
        }
    }
}