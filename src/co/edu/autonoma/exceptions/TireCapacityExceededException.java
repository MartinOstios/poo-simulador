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

    public TireCapacityExceededException() {
        super("Frenó bruscamente a una velocidad superior a la soportada por las ruedas");
    }
    
}
