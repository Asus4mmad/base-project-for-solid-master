package services;

class EmailSender implements MessageSender{
    public void sendEmail(String to, String message){
        System.out.println("Sending email to " + to + ": " + message);
    }

        @Override
    public void sendSms(String phoneNumber, String message) {
        // SMS sending is not available for EmailSender
    }
}
