import java.util.ArrayList;
import java.util.List;

public class ProductFinder {

    public Product findCheapest(List<Product> products) {
        if (products == null || products.isEmpty()) return null;

        Product cheapest = products.getFirst();
        for (Product product : products) {
            if (product.getPrice() < cheapest.getPrice()) {
                cheapest = product;
            }
        }
        return cheapest;
    }

    public Product findMostExpensive(List<Product> products) {
        if (products == null || products.isEmpty()) return null;

        Product mostExpensive = products.get(0);
        for (Product product : products) {
            if (product.getPrice() > mostExpensive.getPrice()) {
                mostExpensive = product;
            }
        }
        return mostExpensive;
    }

    public List<Product> findNCheapest(List<Product> products, int n) {
        if (products == null || products.isEmpty()) return new ArrayList<>();

        int limit = Math.min(n, products.size());

        List<Product> listCopy = new ArrayList<>(products);

        ProductSorter sorter = new ProductSorter();
        List<Product> sortedProducts = sorter.sort(listCopy, ProductComparators.byPriceAsc());

        return new ArrayList<>(sortedProducts.subList(0, limit));
    }

    public List<Product> findNMostExpensive(List<Product> products, int n) {
        if (products == null || products.isEmpty()) return new ArrayList<>();

        int limit = Math.min(n, products.size());
        List<Product> listCopy = new ArrayList<>(products);

        ProductSorter sorter = new ProductSorter();
        List<Product> sortedProducts = sorter.sort(listCopy, ProductComparators.byPriceDescThenNameAsc());

        return new ArrayList<>(sortedProducts.subList(0, limit));
    }
}