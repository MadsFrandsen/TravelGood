/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Calendar;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Hotel extends BookingItem {
    
    @XmlElement
    private Location location;
    
    @XmlElement
    private Calendar arrival;
    
    @XmlElement
    private Calendar departure;
    
    public Hotel() {}
 
    public Hotel(String bookingNumber, Location location, Calendar arrival, Calendar departure) {
        super(bookingNumber);
        this.location = location;
        this.arrival = arrival;
        this.departure = departure;
    }
}
