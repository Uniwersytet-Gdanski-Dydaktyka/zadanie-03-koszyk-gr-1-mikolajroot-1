import java.util.List;

public class FreeMugStrategy implements PromotionStrategy {
    private final double threshold;

    public FreeMugStrategy(double threshold) {
        this.threshold = threshold;
    }

    @Override
    public void apply(Cart cart){
        if (cart == null) return;

        if (cart.calculateTotalBasePrice() > threshold) {
            List<Product> items = cart.getItems();
            if (items == null) return;

            boolean alreadyHasMug = false;
            String mugCode = "FREE_MUG";
            for (Product item : items) {
                if (item != null && item.getCode().equals(mugCode)) {
                    alreadyHasMug = true;
                    break;
                }
            }

            if (!alreadyHasMug) {
                Product mug = new Product(mugCode, "T-shirt", 0.0);
                items.add(mug);
                cart.updateItems(items);
            }
        }
    }

    @Override
    public String getPromotionName() {
        return "Darmowy kubek powyżej " + threshold + " zł";
    }
}