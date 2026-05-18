import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProductSorterTest {

    private ProductSorter sorter;
    private List<Product> products;
    private Product p1, p2, p3, p4, p5;

    @BeforeEach
    void setUp() {
        sorter = new ProductSorter();


        p1 = new Product("P001", "Zegarek", 500.0);
        p2 = new Product("P002", "Kubek", 20.0);
        p3 = new Product("P003", "Brelok A", 10.0);
        p4 = new Product("P004", "Laptop", 3500.0);
        p5 = new Product("P005", "Brelok B", 10.0);

        products = new ArrayList<>(List.of(p1, p2, p3, p4, p5));
    }

    @Test
    @DisplayName("sort: Sortuje poprawnie rosnąco po cenie (byPriceAsc)")
    void testSortByPriceAsc() {
        List<Product> sortedList = sorter.sort(products, ProductComparators.byPriceAsc());

        assertEquals(5, sortedList.size());

        assertEquals(10.0, sortedList.get(0).getPrice());
        assertEquals(10.0, sortedList.get(1).getPrice());
        assertEquals("P002", sortedList.get(2).getCode()); // 20.0
        assertEquals("P001", sortedList.get(3).getCode()); // 500.0
        assertEquals("P004", sortedList.get(4).getCode()); // 3500.0
    }

    @Test
    @DisplayName("sort: Sortuje poprawnie alfabetycznie po nazwie (byNameAsc)")
    void testSortByNameAsc() {
        List<Product> sortedList = sorter.sort(products, ProductComparators.byNameAsc());

        assertEquals(5, sortedList.size());

        assertEquals("Brelok A", sortedList.get(0).getName());
        assertEquals("Brelok B", sortedList.get(1).getName());
        assertEquals("Kubek", sortedList.get(2).getName());
        assertEquals("Laptop", sortedList.get(3).getName());
        assertEquals("Zegarek", sortedList.get(4).getName());
    }

    @Test
    @DisplayName("sort: Sortuje wg złożonych reguł malejąco po cenie, a potem rosnąco po nazwie (byPriceDescThenNameAsc)")
    void testSortByPriceDescThenNameAsc() {
        List<Product> sortedList = sorter.sort(products, ProductComparators.byPriceDescThenNameAsc());

        assertEquals(5, sortedList.size());


        assertEquals("P004", sortedList.get(0).getCode());
        assertEquals("P001", sortedList.get(1).getCode());
        assertEquals("P002", sortedList.get(2).getCode());

        assertEquals("P003", sortedList.get(3).getCode()); // Brelok A
        assertEquals("P005", sortedList.get(4).getCode()); // Brelok B
    }

    @Test
    @DisplayName("sort: Poprawnie obsługuje pustą listę (nie rzuca wyjątków)")
    void testSortEmptyList() {
        List<Product> emptyList = new ArrayList<>();
        List<Product> sortedList = sorter.sort(emptyList, ProductComparators.byPriceAsc());

        assertNotNull(sortedList);
        assertTrue(sortedList.isEmpty());
    }

    @Test
    @DisplayName("sort: Poprawnie obsługuje listę z jednym elementem")
    void testSortSingleElementList() {
        List<Product> singleElementList = new ArrayList<>(List.of(p1));
        List<Product> sortedList = sorter.sort(singleElementList, ProductComparators.byPriceAsc());

        assertEquals(1, sortedList.size());
        assertEquals("P001", sortedList.getFirst().getCode());
    }
}