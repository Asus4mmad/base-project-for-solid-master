package services;

/**
 * OCP: برای اضافه‌کردن روش پرداخت جدید، فقط یک کلاس جدید می‌سازیم
 * بدون تغییر ReservationService
 */
public interface PaymentProvider {
    void processPayment(double amount);
}
