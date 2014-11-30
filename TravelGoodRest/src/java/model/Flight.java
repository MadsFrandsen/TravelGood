/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Calendar;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
/**
 *
 * @author Mads
 */
@XmlRootElement
public class Flight extends BookingItem {
    
    @XmlElement
    private Calendar departureTime;
    
    @XmlElement
    private Route route;
    
    @XmlElement
    private int price;
    
    public Flight() {}
    
    public Flight(String bookingNumber, int price) {
        super(bookingNumber);
        this.price = price;
    }
    
    public Flight(String bookingNumber, Route route, Calendar departureTime, int price) {
        super(bookingNumber);
        this.route = route;
        this.departureTime = departureTime;
        this.price = price;
    }
    
    public Route getRoute() {
        return route;
    }
    
    public int getPrice() {
        return price;
    }
}
