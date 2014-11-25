/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package niceView;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author planaspa
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Reservation {
   
    private int bookingNumber;
    private int totalPrice;
    private String name;
    private String address;
    private boolean creditCardGuarantee;
    private String hotelReservationService;
  
    public Reservation(){
        
    }

    public Reservation(int bookingNumber, int totalPrice, String name, String address, boolean creditCardGuarantee, String hotelReservationService) {
        this.bookingNumber = bookingNumber;
        this.totalPrice = totalPrice;
        this.name = name;
        this.address = address;
        this.creditCardGuarantee = creditCardGuarantee;
        this.hotelReservationService = hotelReservationService;
    }



    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public boolean isCreditCardGuarantee() {
        return creditCardGuarantee;
    }

    public int getBookingNumber() {
        return bookingNumber;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCreditCardGuarantee(boolean creditCardGuarantee) {
        this.creditCardGuarantee = creditCardGuarantee;
    }
 

    public void setBookingNumber(int bookingNumber) {
        this.bookingNumber = bookingNumber;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }
    
    public String getHotelReservationService() {
        return hotelReservationService;
    }

    public void setHotelReservationService(String hotelReservationService) {
        this.hotelReservationService = hotelReservationService;
    }

}
