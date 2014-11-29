/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package LameDuck;

import java.util.GregorianCalendar;


/**
 *
 * @author Nygaard
 */
public class Flight {
    
    private int id;
    private static int nextId = 0;
    private GregorianCalendar departureCalendar;
    private GregorianCalendar arrivalCalendar;
    private String source;
    private String destination;
    private String airline;

    public Flight(){
        
    }
    
    public Flight(String airline, String source, String departureDate, String departureTime, String destination, String arrivalDate, String arrivalTime ) {
        nextId++;
        this.id = nextId;
        
        /* Convert string date to Gregorian Calendar */
        String departureDay = departureDate.substring(0, 2);
        String departureMonth = departureDate.substring(2, 4);
        String departureYear = departureDate.substring(4, 8);
        String departureHour = departureTime.substring(0, 2);
        String departureMinute = departureTime.substring(2, 4);
        this.departureCalendar = new GregorianCalendar(Integer.parseInt(departureYear), Integer.parseInt(departureMonth)-1, Integer.parseInt(departureDay), Integer.parseInt(departureHour), Integer.parseInt(departureMinute));
        
        String arrivalDay = arrivalDate.substring(0, 2);
        String arrivalMonth = arrivalDate.substring(2, 4);
        String arrivalYear = arrivalDate.substring(4, 8);
        String arrivalHour = arrivalTime.substring(0, 2);
        String arrivalMinute = arrivalTime.substring(2, 4);
        this.arrivalCalendar = new GregorianCalendar(Integer.parseInt(arrivalYear), Integer.parseInt(arrivalMonth)-1, Integer.parseInt(arrivalDay), Integer.parseInt(arrivalHour), Integer.parseInt(arrivalMinute));
        
        this.source = source;
        this.destination = destination;
        this.airline = airline;
    }

    
    
    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @return the departureTime
     */
    public GregorianCalendar getDepartureTime() {
        return departureCalendar;
    }

    /**
     * @param departureTime the departureTime to set
     */
    public void setDepartureTime(GregorianCalendar departureTime) {
        this.departureCalendar = departureTime;
    }

    /**
     * @return the arrivalTime
     */
    public GregorianCalendar getArrivalTime() {
        return arrivalCalendar;
    }

    /**
     * @param arrivalTime the arrivalTime to set
     */
    public void setArrivalTime(GregorianCalendar arrivalTime) {
        this.arrivalCalendar = arrivalTime;
    }

    /**
     * @return the source
     */
    public String getSource() {
        return source;
    }

    /**
     * @param source the source to set
     */
    public void setSource(String source) {
        this.source = source;
    }

    /**
     * @return the destination
     */
    public String getDestination() {
        return destination;
    }

    /**
     * @param destination the destination to set
     */
    public void setDestination(String destination) {
        this.destination = destination;
    }

    /**
     * @return the airline
     */
    public String getAirline() {
        return airline;
    }

    /**
     * @param airline the airline to set
     */
    public void setAirline(String airline) {
        this.airline = airline;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }
    
    
    
}
