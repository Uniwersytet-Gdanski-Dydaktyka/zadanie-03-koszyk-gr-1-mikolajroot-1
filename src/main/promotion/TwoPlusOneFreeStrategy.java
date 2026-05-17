import java.util.ArrayList;
import java.util.List;

public class TwoPlusOneFreeStrategy implements PromotionStrategy {

    @Override
    public void apply(Cart cart) {
        if (cart == null) return;

        List<Product> items = cart.getItems();

        if (items.size() < 3) return;

        ProductSorter sorter = new ProductSorter();
        sorter.sort(items, ProductComparators.byPriceDescThenNameAsc());

        List<Product> discountedItems = new ArrayList<>(items);

        for (int i = 2; i < discountedItems.size(); i += 3) {
            Product thirdItem = discountedItems.get(i);

            Product freeItem = thirdItem.withDiscountPrice(0.0);

            discountedItems.set(i, freeItem);
        }

        cart.updateItems(discountedItems);
    }

    @Override
    public String getPromotionName() {
        return "Promocja 2+1 (Najtańszy z trójki gratis)";
    }
}