/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dtu.ws.travelgood.SOAPTest;

import java.util.GregorianCalendar;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 *
 * @author srnjcbsn
 */
public class Hotel 
{
    private String name;
    private String address;
    private boolean creditCardGuarantee;
    private int price;
    private String hotelReservationService;
    private String city;
    private XMLGregorianCalendar fromDate;
    private XMLGregorianCalendar toDate;
    
    public Hotel(String name, String address, boolean creditCardGuarantee, int price, String hotelReservationService, String city) 
    {
        this.name = name;
        this.address = address;
        this.creditCardGuarantee = creditCardGuarantee;
        this.price = price;
        this.hotelReservationService = hotelReservationService;
        this.city = city;
        
        DatatypeFactory df = null;
        try {
            df = DatatypeFactory.newInstance();
        } catch (Exception e) { }
        
        fromDate = df.newXMLGregorianCalendar(new GregorianCalendar(2015, 3, 10));
        toDate = df.newXMLGregorianCalendar(new GregorianCalendar(2015, 4, 10));
    }
    
    public XMLGregorianCalendar getFromDate() {
        return fromDate;
    }

    public void setFromDate(XMLGregorianCalendar fromDate) {
        this.fromDate = fromDate;
    }

    public XMLGregorianCalendar getToDate() {
        return toDate;
    }

    public void setToDate(XMLGregorianCalendar toDate) {
        this.toDate = toDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isCreditCardGuarantee() {
        return creditCardGuarantee;
    }

    public void setCreditCardGuarantee(boolean creditCardGuarantee) {
        this.creditCardGuarantee = creditCardGuarantee;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getHotelReservationService() {
        return hotelReservationService;
    }

    public void setHotelReservationService(String hotelReservationService) {
        this.hotelReservationService = hotelReservationService;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
