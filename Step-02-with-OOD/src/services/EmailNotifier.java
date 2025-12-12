package services;

/**
 * Interface Segregation Principle: فقط ایمیل فرستادن
 */
public interface EmailNotifier {
    void sendEmail(String to, String message);
}
