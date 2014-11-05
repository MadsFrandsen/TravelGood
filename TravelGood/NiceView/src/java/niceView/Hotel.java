/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package niceView;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author planaspa
 */
@XmlRootElement
@XmlType(propOrder = {"name", "address", "creditCardGuarantee", "hotelReservationService"})
@XmlAccessorType(XmlAccessType.NONE)
public class Hotel {   
    @XmlElement
    private String name;
    @XmlElement
    private String address;
    @XmlElement
    private boolean creditCardGuarantee;
    private int price;   //Price per night
    @XmlElement
    private String hotelReservationService;
    private String city;

    public Hotel(String name, String address, boolean creditCardGuarantee, int price, String hotelReservationService, String city) {
        this.name = name;
        this.address = address;
        this.creditCardGuarantee = creditCardGuarantee;
        this.price = price;
        this.hotelReservationService = hotelReservationService;
        this.city = city;
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

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCreditCardGuarantee(boolean creditCardGuarantee) {
        this.creditCardGuarantee = creditCardGuarantee;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setHotelReservationService(String hotelReservationService) {
        this.hotelReservationService = hotelReservationService;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getPrice() {
        return price;
    }

    public String getHotelReservationService() {
        return hotelReservationService;
    }
    
    public String getCity(){
        return city;
    }
}
