import java.util.ArrayList;
import java.util.List;

public class PercentageDiscountStrategy implements PromotionStrategy {

    private final double threshold;
    private final double percentage;

    public PercentageDiscountStrategy(double threshold, double percentage) {
        this.threshold = threshold;
        this.percentage = percentage;
    }

    @Override
    public void apply(Cart cart) {
        if (cart == null) return;

        if (cart.calculateTotalBasePrice() > this.threshold) {

            List<Product> originalItems = cart.getItems();
            List<Product> discountedItems = new ArrayList<>();

            double multiplier = 1.0 - (this.percentage / 100.0);

            for (Product item : originalItems) {
                if (item == null) continue;

                double newPrice = item.getDiscountPrice() * multiplier;


                discountedItems.add(item.withDiscountPrice(newPrice));
            }

            cart.updateItems(discountedItems);
        }
    }

    @Override
    public String getPromotionName() {
        return "Zniżka " + this.percentage + "% powyżej " + this.threshold + " zł";
    }
}