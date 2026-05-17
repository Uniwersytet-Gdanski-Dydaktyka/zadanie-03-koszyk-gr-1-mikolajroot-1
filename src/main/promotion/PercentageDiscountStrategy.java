public class PercentageDiscountStrategy implements PromotionStrategy {

    @Override
    public void apply(Cart cart) {
        if (cart == null) return;

        if (cart.calculateTotalBasePrice() > 300) {

            Product[] originalItems = cart.getItems();
            Product[] discountedItems = new Product[originalItems.length];

            for (int i = 0; i < originalItems.length; i++) {
                Product item = originalItems[i];
                if (item == null) continue;

                double newPrice = item.getDiscountPrice() * 0.95;

                discountedItems[i] = item.withDiscountPrice(newPrice);
            }

            cart.updateItems(discountedItems);
        }
    }
}