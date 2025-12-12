package services;

/**
 * SRP: فقط چاپ صورت‌حساب مسئول است
 */
public class InvoiceService {
    public void printInvoice(Reservation reservation, double finalPrice) {
        System.out.println("----- INVOICE -----");
        System.out.println("Customer: " + reservation.getCustomerName());
        System.out.println("Room: " + reservation.getRoomNumber() + " (" + reservation.getRoomType() + ")");
        System.out.println("Total: " + finalPrice);
        System.out.println("-------------------");
    }
}
