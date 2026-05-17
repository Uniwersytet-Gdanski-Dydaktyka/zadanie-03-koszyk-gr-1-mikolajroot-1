public interface PromotionStrategy {
    void apply(Cart cart);
    String getPromotionName();
}
