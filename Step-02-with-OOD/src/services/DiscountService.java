package services;

/**
 * SRP: فقط محاسبه تخفیف مسئول است
 */
public class DiscountService {
    public double applyDiscount(Reservation reservation) {
        String city = reservation.getCustomerCity();
        
        if ("Paris".equals(city)) {
            System.out.println("Apply city discount for Paris!");
            return reservation.getTotalPrice() * 0.9; // 10% discount
        }
        
        return reservation.getTotalPrice();
    }
}
