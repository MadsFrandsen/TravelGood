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
    
    public Flight() {}
    
    public Flight(String bookingNumber, Route route, Calendar departureTime) {
        super(bookingNumber);
        this.route = route;
        this.departureTime = departureTime;
    }
    
    public Route getRoute() {
        return route;
    }
}
