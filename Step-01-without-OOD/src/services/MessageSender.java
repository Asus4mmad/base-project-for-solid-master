package services;

public interface MessageSender {
    public void sendEmail(String to, String message);
    public void sendSms(String phoneNumber, String message);
}
