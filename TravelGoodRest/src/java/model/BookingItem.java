/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement
public class BookingItem {
    
    @XmlElement
    private BookingStatus bookingStatus = BookingStatus.UNCONFIRMED;
    
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
    
    @XmlTransient
    public void setBookingStatus(BookingStatus bookingStatus) {
        this.bookingStatus = bookingStatus;
    }
    
    public String getBookingId() {
        return bookingId;
    }
    
    public BookingStatus getBookingStatus() {
        return bookingStatus;
    }
}
