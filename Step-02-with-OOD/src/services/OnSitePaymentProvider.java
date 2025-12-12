package services;

/**
 * پیاده‌سازی برای پرداخت حضوری
 */
public class OnSitePaymentProvider implements PaymentProvider {
    @Override
    public void processPayment(double amount) {
        System.out.println("Paid in person: " + amount);
    }
}
