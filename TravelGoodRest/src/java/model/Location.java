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
public class Location {
    
    @XmlElement
    private String name;
    
    public Location() {}
    
    public Location(String name) {
        this.name = name;
    }
    
    public String getName() {
        return name;
    }
}
