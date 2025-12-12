package services;

/**
 * پیاده‌سازی برای پرداخت نقد
 */
public class CashPaymentProvider implements PaymentProvider {
    @Override
    public void processPayment(double amount) {
        System.out.println("Paid by cash: " + amount);
    }
}
