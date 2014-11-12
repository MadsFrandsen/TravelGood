/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Calendar;

/**
 *
 * @author Mads
 */
public class Flight extends BookingItem {
    
    private Calendar departureTime;
    private Route route;
    
    public Route getRoute() {
        return route;
    }
}
