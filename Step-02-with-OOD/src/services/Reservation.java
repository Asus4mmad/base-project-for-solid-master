package services;

import models.Customer;
import models.Room;

/**
 * PLK: Reservation getter‌های ایمن ارائه می‌دهد
 * بدون نیاز به دسترسی مستقیم به room و customer
 */
public class Reservation {
    private Room room;
    private Customer customer;
    private int nights;

    public Reservation(Room r, Customer c, int nights) {
        this.room = r;
        this.customer = c;
        this.nights = nights;
    }

    public double getTotalPrice(){
        return room.getPrice() * nights;
    }

    // ✅ PLK: Getter‌های ایمن (بدون نیاز به دسترسی مستقیم)
    public String getCustomerName() {
        return customer.getName();
    }

    public String getCustomerEmail() {
        return customer.getEmail();
    }

    public String getCustomerCity() {
        return customer.getCity();
    }

    public String getCustomerPhone() {
        return customer.getMobile();
    }

    public String getRoomNumber() {
        return room.getNumber();
    }

    public String getRoomType() {
        return room.getType();
    }

    public double getRoomPrice() {
        return room.getPrice();
    }

    public void setRoomPrice(double price) {
        room.setPrice(price);
    }

    public int getNights() {
        return nights;
    }
}