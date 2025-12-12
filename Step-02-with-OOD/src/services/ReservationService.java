package services;

import constants.Notifier;

/**
 *  SRP: فقط مدیریت رزرو (هماهنگی بین سرویس‌ها)
 *  DIP: تمام وابستگی‌ها injected می‌شوند
 *  PLK: از getter‌های Reservation استفاده می‌کند
 */
public class ReservationService {
    private PaymentProvider paymentProvider;
    private DiscountService discountService;
    private InvoiceService invoiceService;
    private NotificationService notificationService;

    //  DIP: تمام سرویس‌ها constructor injection می‌شوند
    public ReservationService(
            PaymentProvider paymentProvider,
            DiscountService discountService,
            InvoiceService invoiceService,
            NotificationService notificationService) {
        this.paymentProvider = paymentProvider;
        this.discountService = discountService;
        this.invoiceService = invoiceService;
        this.notificationService = notificationService;
    }

    public void makeReservation(Reservation res, Notifier notificationMethod) {
        System.out.println("Processing reservation for " + res.getCustomerName());

        // Step 1: محاسبه و اعمال تخفیف
        double finalPrice = discountService.applyDiscount(res);
        res.setRoomPrice(finalPrice);

        // Step 2: پرداخت
        paymentProvider.processPayment(finalPrice);

        // Step 3: چاپ صورت‌حساب
        invoiceService.printInvoice(res, finalPrice);

        // Step 4: اطلاع‌رسانی مشتری
        notificationService.notifyCustomer(res, notificationMethod);
    }
}