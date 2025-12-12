package services;

/**
 * تحقق ISP: فقط SmsNotifier را implement می‌کند
 */
public class SmsSender implements SmsNotifier {
    @Override
    public void sendSms(String phoneNumber, String message){
        System.out.println("Sending SMS to " + phoneNumber + ": " + message);
    }
}
