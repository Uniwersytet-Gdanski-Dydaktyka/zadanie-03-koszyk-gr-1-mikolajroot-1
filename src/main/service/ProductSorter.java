import java.util.Arrays;
import java.util.Comparator;

public class ProductSorter {

    public Product[] sort(Product[] products,Comparator<Product> comparator){
        Arrays.sort(products,comparator);
        return products;
    }
}
