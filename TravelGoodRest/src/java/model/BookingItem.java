/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

public class BookingItem {
    
    private BookingStatus bookingStatus;
    private String bookingId;
    
    public enum BookingStatus {
        CONFIRMED,
        UNCONFIRMED,
        CANCELLED
    }
    
    public String getBookingId() {
        return bookingId;
    }
    
    public BookingStatus getBookingStatus() {
        return bookingStatus;
    }
}
