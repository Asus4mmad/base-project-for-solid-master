package services;

import constants.Notifier;

/**
 * SRP: فقط اطلاع‌رسانی به مشتری مسئول است
 * DIP: NotificationProvider و EmailNotifier/SmsNotifier injected می‌شوند
 */
public class NotificationService {
    private EmailNotifier emailNotifier;
    private SmsNotifier smsNotifier;

    public NotificationService(EmailNotifier emailNotifier, SmsNotifier smsNotifier) {
        this.emailNotifier = emailNotifier;
        this.smsNotifier = smsNotifier;
    }

    public void notifyCustomer(Reservation reservation, Notifier notificationMethod) {
        switch (notificationMethod) {
            case EMAIL:
                emailNotifier.sendEmail(
                    reservation.getCustomerEmail(),
                    "Your reservation confirmed!"
                );
                System.out.println("Email sent successfully!");
                break;
            case SMS:
                smsNotifier.sendSms(
                    reservation.getCustomerPhone(),
                    "Your reservation confirmed!"
                );
                System.out.println("SMS sent successfully!");
                break;
            default:
                System.out.println("There is no Message Provider");
        }
    }
}
