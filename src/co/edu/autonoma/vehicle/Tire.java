
package co.edu.autonoma.vehicle;

import co.edu.autonoma.exceptions.TireCapacityExceededException;

public class Tire {
    private String type;
    private int maxSpeed;

    public Tire(String type) {
        this.type = type;
        this.maxSpeed = 0;
    }
    
    public void toBrake(int magnitude, int actualVelocity){
        if(magnitude > 30 & actualVelocity > maxSpeed){
            throw new TireCapacityExceededException();
        }
    }
    
    public void setMaxSpeed() {
        switch (this.type) {
            case "Buenas":
                this.maxSpeed = 110;
                break;
            case "Bonitas":
                this.maxSpeed = 70;
                break;
            case "Baratas":
                this.maxSpeed = 50;
                break;
            default:
                this.maxSpeed = -1;
        }
    }
    
}
