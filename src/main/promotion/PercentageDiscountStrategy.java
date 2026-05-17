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

            Product[] originalItems = cart.getItems();
            Product[] discountedItems = new Product[originalItems.length];


            double multiplier = 1.0 - (this.percentage / 100.0);

            for (int i = 0; i < originalItems.length; i++) {
                Product item = originalItems[i];
                if (item == null) continue;

                double newPrice = item.getDiscountPrice() * multiplier;

                discountedItems[i] = item.withDiscountPrice(newPrice);
            }

            cart.updateItems(discountedItems);
        }
    }

    @Override
    public String getPromotionName() {
        return "Zniżka " + this.percentage + "% powyżej " + this.threshold + " zł";
    }
}