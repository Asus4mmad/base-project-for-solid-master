package services;

/**
 * تحقق ISP: فقط EmailNotifier را implement می‌کند
 */
public class EmailSender implements EmailNotifier {
    @Override
    public void sendEmail(String to, String message){
        System.out.println("Sending email to " + to + ": " + message);
    }
}
