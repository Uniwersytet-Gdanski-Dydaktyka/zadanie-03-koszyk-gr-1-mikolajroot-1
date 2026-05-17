import java.util.*;

public class ProductSorter {

    public List<Product> sort(List<Product> products, Comparator<Product> comparator){
        products.sort(comparator);
        return products;
    }
}
