package services;

/**
 * Interface Segregation Principle: فقط SMS فرستادن
 */
public interface SmsNotifier {
    void sendSms(String phoneNumber, String message);
}
