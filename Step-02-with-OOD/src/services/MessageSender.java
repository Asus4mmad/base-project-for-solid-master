package services;

/**
 * Interface Segregation: هر interface فقط یک مسئولیت دارد
 */
public interface EmailSender {
    void sendEmail(String to, String message);
}

interface SmsSender {
    void sendSms(String phoneNumber, String message);
}
