import java.util.List;

public class CouponStrategy implements PromotionStrategy {
    private final String targetProductCode;
    private final double percentage;

    public CouponStrategy(String targetProductCode, double percentage) {
        this.targetProductCode = targetProductCode;
        this.percentage = percentage;
    }

    @Override
    public void apply(Cart cart) {
        if (cart == null) return;

        List<Product> items = cart.getItems();
        if (items == null || items.isEmpty()) return;

        int targetIndex = -1;
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i) != null && items.get(i).getCode().equals(targetProductCode)) {
                targetIndex = i;
                break;
            }
        }

        if (targetIndex == -1) return;

        Product oldProduct = items.get(targetIndex);


        double multiplier = 1.0 - (this.percentage / 100.0);


        double newPrice = oldProduct.getDiscountPrice() * multiplier;

        Product discountedProduct = oldProduct.withDiscountPrice(newPrice);
        items.set(targetIndex, discountedProduct);

        cart.updateItems(items);
    }

    @Override
    public String getPromotionName() {
        return "Kupon rabatowy -" + this.percentage + "% na produkt: " + this.targetProductCode;
    }
}