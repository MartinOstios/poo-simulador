/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.autonoma.exceptions;

/**
 *
 * @author izibr
 */
public class TireCapacityExceededException extends RuntimeException {

    public TireCapacityExceededException(int magnitude, int actualVelocity, int maxSpeed) {
        super("Frenaste bruscamente (" + magnitude +"km/h) a una velocidad de "+ actualVelocity + "km/h pero la capacidad máxima de las llantas es: " + maxSpeed + "km/h");
    }
    
}
