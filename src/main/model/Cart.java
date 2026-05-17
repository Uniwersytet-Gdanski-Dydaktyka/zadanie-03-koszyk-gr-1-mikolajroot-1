import java.util.Arrays;

public class Cart {
    private Product[] items;

    public Cart() {
        this.items = new Product[0];
    }

    public void addProduct(Product p) {
        if (p == null) {
            throw new IllegalArgumentException("Nie można dodać pustego produktu do koszyka.");
        }
        this.items = Arrays.copyOf(this.items, this.items.length + 1);
        this.items[this.items.length - 1] = p;
    }

    public double calculateTotalBasePrice() {
        double totalPrice = 0;
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

    public Product[] getItems() {
        return Arrays.copyOf(this.items, this.items.length);
    }

    public void updateItems(Product[] newItems) {
        this.items = Arrays.copyOf(newItems, newItems.length);
    }
}