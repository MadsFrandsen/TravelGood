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
        UNCONFIRMED
    }
    
    public String getBookingId() {
        return bookingId;
    }
    
}
