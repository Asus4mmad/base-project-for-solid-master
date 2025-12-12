package services;

import constants.PaymentMethods;

/**
 * Factory Pattern: PaymentProvider مناسب را برمی‌گرداند
 * (این کلاس تنها مکانی است که switch statement از PaymentMethods استفاده می‌کند)
 */
public class PaymentProviderFactory {
    public static PaymentProvider getPaymentProvider(PaymentMethods method) {
        switch (method) {
            case CARD:
                return new CardPaymentProvider();
            case PAYPAL:
                return new PayPalPaymentProvider();
            case CASH:
                return new CashPaymentProvider();
            case ONSITE:
                return new OnSitePaymentProvider();
            default:
                throw new IllegalArgumentException("Unknown payment method: " + method);
        }
    }
}
