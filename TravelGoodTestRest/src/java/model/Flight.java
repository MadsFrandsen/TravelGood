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
public class Flight {
    
    private String id;
    private Calendar departureTime;
    private Route route;
    
    public Flight(String id, Calendar departureTime, Route route) {
        this.id = id;
        this.departureTime = departureTime;
        this.route = route;
    }
    
    public Route getRoute() {
        return route;
    }
}
