package services;

/**
 * پیاده‌سازی برای پرداخت PayPal
 */
public class PayPalPaymentProvider implements PaymentProvider {
    @Override
    public void processPayment(double amount) {
        System.out.println("Paid by PayPal: " + amount);
    }
}
