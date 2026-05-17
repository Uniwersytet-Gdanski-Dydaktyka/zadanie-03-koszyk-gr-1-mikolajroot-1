import java.util.Arrays;

public class ProductFinder {

    public Product findCheapest(Product[] products) {
        if (products == null || products.length == 0) return null;

        Product cheapest = products[0];
        for (Product product : products) {
            if (product.getPrice() < cheapest.getPrice()) {
                cheapest = product;
            }
        }
        return cheapest;
    }

    public Product findMostExpensive(Product[] products) {
        if (products == null || products.length == 0) return null;

        Product mostExpensive = products[0];
        for (Product product : products) {
            if (product.getPrice() > mostExpensive.getPrice()) {
                mostExpensive = product;
            }
        }
        return mostExpensive;
    }

    public Product[] findNCheapest(Product[] products, int n) {
        if (products == null || products.length == 0) return new Product[0];

        int limit = Math.min(n, products.length);

        Product[] arrayCopy = Arrays.copyOf(products, products.length);

        ProductSorter sorter = new ProductSorter();
        Product[] sortedProducts = sorter.sort(arrayCopy, ProductComparators.byPriceAsc());

        return Arrays.copyOfRange(sortedProducts, 0, limit);
    }

    public Product[] findNMostExpensive(Product[] products, int n) {
        if (products == null || products.length == 0) return new Product[0];

        int limit = Math.min(n, products.length);
        Product[] arrayCopy = Arrays.copyOf(products, products.length);

        ProductSorter sorter = new ProductSorter();
        Product[] sortedProducts = sorter.sort(arrayCopy, ProductComparators.byPriceDescThenNameAsc());

        return Arrays.copyOfRange(sortedProducts, 0, limit);
    }
}