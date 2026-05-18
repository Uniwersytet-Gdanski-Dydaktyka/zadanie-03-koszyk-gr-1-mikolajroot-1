import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CartTest {

    private Cart cart;
    private Product p1;
    private Product p2;

    @BeforeEach
    void setUp() {
        cart = new Cart();
        p1 = new Product("P001", "Laptop", 3000.0, 3000.0);
        p2 = new Product("P002", "Mysz", 100.0, 50.0);
    }

    @Test
    @DisplayName("Nowy koszyk powinien być pusty i mieć wartość 0")
    void testNewCartIsEmpty() {
        assertTrue(cart.getItems().isEmpty());
        assertEquals(0.0, cart.calculateTotalBasePrice());
        assertEquals(0.0, cart.calculateTotalDiscountPrice());
    }

    @Test
    @DisplayName("Dodanie prawidłowego produktu powiększa listę")
    void testAddProductSuccessfully() {
        cart.addProduct(p1);

        assertEquals(1, cart.getItems().size());
        assertEquals("P001", cart.getItems().getFirst().getCode());
    }

    @Test
    @DisplayName("Dodanie null rzuca wyjątek IllegalArgumentException")
    void testAddNullProductThrowsException() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> cart.addProduct(null)
        );
        assertEquals("Nie można dodać pustego produktu do koszyka.", exception.getMessage());
    }

    @Test
    @DisplayName("calculateTotalBasePrice poprawnie sumuje oryginalne ceny")
    void testCalculateTotalBasePrice() {
        cart.addProduct(p1);
        cart.addProduct(p2);

        double total = cart.calculateTotalBasePrice();

        assertEquals(3100.0, total);
    }

    @Test
    @DisplayName("calculateTotalDiscountPrice poprawnie sumuje ceny promocyjne")
    void testCalculateTotalDiscountPrice() {
        cart.addProduct(p1);
        cart.addProduct(p2);

        double totalDiscounted = cart.calculateTotalDiscountPrice();

        assertEquals(3050.0, totalDiscounted);
    }

    @Test
    @DisplayName("getItems zwraca kopie listy, chroniąc koszyk przed zepsuciem")
    void testGetItemsReturnsDefensiveCopy() {
        cart.addProduct(p1);

        List<Product> externalList = cart.getItems();
        externalList.clear();

        assertEquals(1, cart.getItems().size());
    }

    @Test
    @DisplayName("updateItems podmienia listę na nową")
    void testUpdateItems() {
        cart.addProduct(p1);

        List<Product> newProducts = new ArrayList<>();
        newProducts.add(p2);

        cart.updateItems(newProducts);

        newProducts.clear();

        assertEquals(1, cart.getItems().size());
        assertEquals("P002", cart.getItems().getFirst().getCode(), "Koszyk nie zaaktualizował listy poprawnie");
    }
}