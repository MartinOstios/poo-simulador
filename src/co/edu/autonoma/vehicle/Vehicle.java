package co.edu.autonoma.vehicle;

import co.edu.autonoma.exceptions.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.ImageObserver;
import java.io.IOException;
import javax.swing.JTextField;

public class Vehicle {

    private int velocity;
    private Engine myEngine;
    private Tire myTire;

    public Vehicle() {
        this.velocity = 0;
    }

    public void turnOn() {
        System.out.println(">>> Encendiendo el vehículo <<<");
        myEngine.turnOn();
    }

    public void turnOff() {
        System.out.println(">>> Apagando el vehículo <<<");
        myEngine.turnOff(velocity);
    }

    public void speedUp(int magnitude) {
        System.out.println(">>> Acelerando: " + magnitude + " <<<");
        myEngine.speedUp(magnitude, this.velocity);
        velocity += magnitude;
        System.out.println("Velocidad actual: " + this.velocity);
    }

    public void toBrake(int magnitude) {
        System.out.println(">>> Frenando: " + magnitude + " <<<");
        myEngine.toBrake();
        if (magnitude > this.velocity) {
            throw new BrakeMagnitudeExceededException();
        }
        myTire.toBrake(magnitude, velocity);
        velocity -= magnitude;
        System.out.println("Velocidad actual: " + this.velocity);
    }
    
    public void skid(){
        this.velocity = 0;
    }
    
    public void crash(){
        this.velocity = 0;
        this.myEngine.turnOff(velocity);
    }

    public void drawSwitchButton(Graphics g, ImageObserver parent) throws IOException {
        myEngine.drawSwitchButton(g, parent);
    }

    public void updateData(Graphics g) {
        g.setColor(Color.WHITE);
        g.setFont(g.getFont().deriveFont(30f));
        g.drawString(this.velocity+" km/h", 160, 300);
    }

    public void createEngine(int cylCapacity) {
        this.myEngine = new Engine(cylCapacity);
        this.myEngine.setMaxSpeed();
    }

    public void createTire(String type) {
        this.myTire = new Tire(type);
        this.myTire.setMaxSpeed();
    }
}
