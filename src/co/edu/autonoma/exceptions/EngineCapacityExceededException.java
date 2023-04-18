/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.autonoma.exceptions;

/**
 *
 * @author izibr
 */
public class EngineCapacityExceededException extends RuntimeException{

    public EngineCapacityExceededException(int magnitude, int actualSpeed, int maxSpeed) {
        super("Intentaste acelerar hasta " + (magnitude + actualSpeed) + "km/h pero la capacidad máxima del motor es: " + maxSpeed + "km/h");
    }
    
}
