import java.util.List;

public class FreeTShirtStrategy implements PromotionStrategy {
    private final double threshold;

    public FreeTShirtStrategy(double threshold) {
        this.threshold = threshold;
    }

    @Override
    public void apply(Cart cart){
        if (cart == null) return;

        if (cart.calculateTotalBasePrice() > threshold) {
            List<Product> items = cart.getItems();
            if (items == null) return;

            boolean alreadyHasTShirt = false;
            String tShirtCode = "FREE_TSHIRT";
            for (Product item : items) {
                if (item != null && item.getCode().equals(tShirtCode)) {
                    alreadyHasTShirt = true;
                    break;
                }
            }

            if (!alreadyHasTShirt) {
                Product tShirt = new Product(tShirtCode, "T-shirt", 0.0);
                items.add(tShirt);
                cart.updateItems(items);
            }
        }
    }

    @Override
    public String getPromotionName() {
        return "Darmowy t-shirt powyżej " + threshold + " zł";
    }
}