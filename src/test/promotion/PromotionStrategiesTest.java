import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PromotionStrategiesTest {

    private Cart cart;
    private Product p1;
    private Product p2;
    private Product p3;
    private Product p4;

    @BeforeEach
    void setUp() {
        cart = new Cart();
        p1 = new Product("P001", "Laptop", 2000.0);
        p2 = new Product("P002", "Mysz", 100.0);
        p3 = new Product("P003", "Klawiatura", 300.0);
        p4 = new Product("P004", "Monitor", 1000.0);
    }

    // --- TESTY: PercentageDiscountStrategy ---

    @Test
    @DisplayName("PercentageDiscount: Aplikuje zniżkę, gdy wartość koszyka przekracza próg")
    void testPercentageDiscountApplied() {
        cart.addProduct(p1); // 2000
        cart.addProduct(p2); // 100
        PercentageDiscountStrategy strategy = new PercentageDiscountStrategy(1000.0, 10.0);
        strategy.apply(cart);


        assertEquals(1890.0, cart.calculateTotalDiscountPrice(), 0.01);
    }

    @Test
    @DisplayName("PercentageDiscount: Ignoruje koszyk, gdy wartość nie przekracza progu")
    void testPercentageDiscountIgnored() {
        cart.addProduct(p2);


        PercentageDiscountStrategy strategy = new PercentageDiscountStrategy(1000.0, 10.0);
        strategy.apply(cart);


        assertEquals(100.0, cart.calculateTotalDiscountPrice(), 0.01);
    }

    // --- TESTY: FreeMugStrategy ---

    @Test
    @DisplayName("FreeMug: Dodaje kubek za 0 zł, gdy wartość przekracza próg")
    void testFreeTShirtApplied() {
        cart.addProduct(p3);
        FreeMugStrategy strategy = new FreeMugStrategy(200.0);
        strategy.apply(cart);

        List<Product> items = cart.getItems();
        assertEquals(2, items.size(), "W koszyku powinny być 2 produkty");

        Product addedTShirt = items.get(1);
        assertEquals("FREE_MUG", addedTShirt.getCode());
        assertEquals(0.0, addedTShirt.getDiscountPrice(), 0.01);

        assertEquals(300.0, cart.calculateTotalDiscountPrice(), 0.01);
    }

    @Test
    @DisplayName("FreeTShirt: Nie dodaje drugiego t-shirtu, jeśli już jest w koszyku")
    void testFreeTShirtNotDuplicated() {
        cart.addProduct(p1); // 2000
        FreeMugStrategy strategy = new FreeMugStrategy(200.0);

        strategy.apply(cart);
        strategy.apply(cart);
        strategy.apply(cart);

        List<Product> items = cart.getItems();
        assertEquals(2, items.size(), "W koszyku powinna być tylko oryginalna rzecz + 1 darmowy T-Shirt");
    }

    // --- TESTY: CouponStrategy ---

    @Test
    @DisplayName("Coupon: Przecenia tylko jeden konkretny produkt na liście")
    void testCouponAppliedToOneProduct() {
        cart.addProduct(p1);
        cart.addProduct(p2);
        cart.addProduct(p3);

        CouponStrategy strategy = new CouponStrategy("P002", 50.0);
        strategy.apply(cart);

        assertEquals(2350.0, cart.calculateTotalDiscountPrice(), 0.01);
    }

    @Test
    @DisplayName("Coupon: Działa jednorazowo - przecenia tylko pierwszy znaleziony produkt")
    void testCouponAppliedOnlyOnce() {
        cart.addProduct(p2);
        cart.addProduct(p2);


        CouponStrategy strategy = new CouponStrategy("P002", 50.0);
        strategy.apply(cart);

        assertEquals(150.0, cart.calculateTotalDiscountPrice(), 0.01);
    }

    // --- TESTY: TwoPlusOneFreeStrategy ---

    @Test
    @DisplayName("TwoPlusOneFree: Najtańszy z trójki jest za darmo")
    void testTwoPlusOneApplied() {
        cart.addProduct(p1);
        cart.addProduct(p2);
        cart.addProduct(p3);

        cart.addProduct(p4);

        TwoPlusOneFreeStrategy strategy = new TwoPlusOneFreeStrategy();
        strategy.apply(cart);


        assertEquals(3100.0, cart.calculateTotalDiscountPrice(), 0.01);
    }

    @Test
    @DisplayName("TwoPlusOneFree: Nie robi nic, gdy produktów jest mniej niż 3")
    void testTwoPlusOneIgnored() {
        cart.addProduct(p1);
        cart.addProduct(p2);

        TwoPlusOneFreeStrategy strategy = new TwoPlusOneFreeStrategy();
        strategy.apply(cart);

        assertEquals(2100.0, cart.calculateTotalDiscountPrice(), 0.01);
    }
}