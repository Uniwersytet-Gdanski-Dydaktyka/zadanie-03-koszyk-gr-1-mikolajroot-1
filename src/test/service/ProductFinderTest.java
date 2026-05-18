import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProductFinderTest {

    private ProductFinder finder;
    private List<Product> products;

    @BeforeEach
    void setUp() {
        finder = new ProductFinder();

        Product p1 = new Product("P001", "Laptop", 3500.0);
        Product p2 = new Product("P002", "Myszka", 50.0);
        Product p3 = new Product("P003", "Klawiatura", 250.0);
        Product p4 = new Product("P004", "Monitor", 1200.0);

        products = new ArrayList<>(List.of(p1, p2, p3, p4));
    }

    // --- TESTY: findCheapest ---

    @Test
    @DisplayName("findCheapest: Zwraca najtańszy produkt z listy")
    void testFindCheapest() {
        Product cheapest = finder.findCheapest(products);
        assertNotNull(cheapest);
        assertEquals("P002", cheapest.getCode(), "Najtańsza powinna być myszka (50.0)");
        assertEquals(50.0, cheapest.getPrice());
    }

    @Test
    @DisplayName("findCheapest: Zwraca null, gdy lista jest pusta lub null")
    void testFindCheapestEdgeCases() {
        assertNull(finder.findCheapest(new ArrayList<>()), "Pusta lista powinna zwrócić null");
        assertNull(finder.findCheapest(null), "Null powinien zwrócić null");
    }


    @Test
    @DisplayName("findMostExpensive: Zwraca najdroższy produkt z listy")
    void testFindMostExpensive() {
        Product mostExpensive = finder.findMostExpensive(products);
        assertNotNull(mostExpensive);
        assertEquals("P001", mostExpensive.getCode(), "Najdroższy powinien być laptop (3500.0)");
        assertEquals(3500.0, mostExpensive.getPrice());
    }

    @Test
    @DisplayName("findMostExpensive: Zwraca null, gdy lista jest pusta lub null")
    void testFindMostExpensiveEdgeCases() {
        assertNull(finder.findMostExpensive(new ArrayList<>()));
        assertNull(finder.findMostExpensive(null));
    }


    @Test
    @DisplayName("findNCheapest: Zwraca N najtańszych produktów we właściwej kolejności")
    void testFindNCheapest() {
        List<Product> cheapestTwo = finder.findNCheapest(products, 2);

        assertEquals(2, cheapestTwo.size());
        assertEquals("P002", cheapestTwo.get(0).getCode());
        assertEquals("P003", cheapestTwo.get(1).getCode());
    }

    @Test
    @DisplayName("findNCheapest: Bezpiecznie obsługuje żądanie N większego niż rozmiar listy")
    void testFindNCheapestMoreThanSize() {
        List<Product> result = finder.findNCheapest(products, 10);

        assertEquals(4, result.size(), "Powinno zwrócić wszystkie 4 produkty, bez błędu IndexOutOfBounds");
        assertEquals("P002", result.get(0).getCode());
        assertEquals("P001", result.get(3).getCode());
    }

    // --- TESTY: findNMostExpensive ---

    @Test
    @DisplayName("findNMostExpensive: Zwraca N najdroższych produktów we właściwej kolejności")
    void testFindNMostExpensive() {
        List<Product> mostExpensiveTwo = finder.findNMostExpensive(products, 2);

        assertEquals(2, mostExpensiveTwo.size());
        assertEquals("P001", mostExpensiveTwo.get(0).getCode());
        assertEquals("P004", mostExpensiveTwo.get(1).getCode());
    }

    @Test
    @DisplayName("findNMostExpensive: Zwraca pustą listę dla wejścia pustego lub null")
    void testFindNMostExpensiveEdgeCases() {
        List<Product> emptyResult = finder.findNMostExpensive(new ArrayList<>(), 5);
        assertTrue(emptyResult.isEmpty());
        List<Product> nullResult = finder.findNMostExpensive(null, 5);
        assertTrue(nullResult.isEmpty());
    }
}