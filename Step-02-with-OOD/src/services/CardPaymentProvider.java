package services;

/**
 * پیاده‌سازی برای پرداخت کارت
 */
public class CardPaymentProvider implements PaymentProvider {
    @Override
    public void processPayment(double amount) {
        System.out.println("Paid by card: " + amount);
    }
}
