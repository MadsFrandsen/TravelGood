/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class BookingItem {
    
    @XmlElement
    private BookingStatus bookingStatus;
    
    @XmlElement
    private String bookingId;
    
    public BookingItem() {}
    
    public BookingItem(String bookingId) {
        this.bookingId = bookingId;
    }
    
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
