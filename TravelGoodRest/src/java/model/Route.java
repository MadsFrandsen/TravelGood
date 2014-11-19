/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Mads
 */
@XmlRootElement
public class Route {
    
    @XmlElement
    private Location origin;
    
    @XmlElement
    private Location destination;
    
    public Route() {}
    
    public Route(Location origin, Location destination) {
        this.origin = origin;
        this.destination = destination;
    }
}
