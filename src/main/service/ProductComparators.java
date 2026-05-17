import java.util.Comparator;

public class ProductComparators {

    private ProductComparators() {}

    public static Comparator<Product> byPriceDescThenNameAsc() {
        return Comparator.comparing(Product::getPrice).reversed().thenComparing(Product::getName);
    }

    public static Comparator<Product> byNameAsc() {
        return Comparator.comparing(Product::getName);
    }

    public static Comparator<Product> byPriceAsc() {
        return Comparator.comparing(Product::getPrice);
    }
}
