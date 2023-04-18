/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.autonoma.exceptions;

/**
 *
 * @author izibr
 */
public class BrakeMagnitudeExceededException extends RuntimeException{

    public BrakeMagnitudeExceededException(int magnitud, int velocity) {
        super("Frenaste con una magnitud de " + magnitud + "km/h pero la velocidad actual es " + velocity +"km/h");
    }
    
}
