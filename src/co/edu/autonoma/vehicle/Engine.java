package co.edu.autonoma.vehicle;

import co.edu.autonoma.exceptions.*;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Engine {

    private int cylCapacity;
    private int maxSpeed;
    private boolean switchOn;

    public Engine(int cylCapacity) {
        this.cylCapacity = cylCapacity;
        this.maxSpeed = 0;
        this.switchOn = false;
    }

    public void turnOn() throws EngineOnException {
        if (switchOn) {
            throw new EngineOnException();
        }
        this.switchOn = true;
    }

    public void turnOff(int actualVelocity) {
        if (!switchOn) {
            throw new EngineOffException();
        }
        if (actualVelocity > 60) {
            throw new EngineOffAtHighSpeedException();
        }
        this.switchOn = false;
    }

    public void speedUp(int magnitude, int actualSpeed) {
        if (!switchOn) {
            throw new EngineOffException();
        }
        if (magnitude + actualSpeed > this.maxSpeed) {
            throw new EngineCapacityExceededException(magnitude, actualSpeed, this.maxSpeed);
        }
    }

    public void toBrake() {
        if (!switchOn) {
            throw new EngineOffException();
        }
    }

    public void drawSwitchButton(Graphics g, ImageObserver parent) throws IOException {
        BufferedImage image;
        if (switchOn) {
            image = ImageIO.read(getClass().getResource("../imgs/engine_on_image.png"));
        } else {
            image = ImageIO.read(getClass().getResource("../imgs/engine_off_image.png"));
        }
        g.drawImage(image, 386, 396, 80, 80, parent);
    }
    
    public void setMaxSpeed() {
        
        switch (this.cylCapacity) {
            case 1000:
                this.maxSpeed = 100;
                break;
            case 2000:
                this.maxSpeed = 160;
                break;
            case 3000:
                this.maxSpeed = 220;
                break;
            default:
                this.maxSpeed = -1;
        }
    }

}
