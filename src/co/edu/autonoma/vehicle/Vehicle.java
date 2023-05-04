package co.edu.autonoma.vehicle;

import co.edu.autonoma.exceptions.*;
import co.edu.autonoma.interfaces.Drawable;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.ImageObserver;
import java.io.IOException;

public class Vehicle implements Drawable{

    private int speed;
    private Engine myEngine;
    private Tire myTire;
    private Drawable drawable;

    public Vehicle() {
        this.speed = 0;
    }

    public void turnOn() {
        myEngine.turnOn();
    }

    public void turnOff() {
        myEngine.turnOff(speed);
    }

    public void speedUp(int magnitude) {
        myEngine.speedUp(magnitude, this.speed);
        speed += magnitude;
        this.drawable.redraw(160, 275, 130, 30);
    }

    public void toBrake(int magnitude) {
        myEngine.toBrake();
        if (magnitude > this.speed) {
            throw new BrakeMagnitudeExceededException(magnitude, this.speed);
        }
        myTire.toBrake(magnitude, speed);
        speed -= magnitude;
        this.drawable.redraw(160, 275, 130, 30);
    }

    public void skid() {
        this.speed = 0;
    }

    public void crash() {
        this.speed = 0;
        this.myEngine.turnOff(speed);
    }

    public void drawSwitchButton(Graphics g, ImageObserver parent) throws IOException {
        myEngine.drawSwitchButton(g, parent);
    }

    public void updateData(Graphics g) {
        g.setColor(Color.WHITE);
        g.setFont(g.getFont().deriveFont(30f));
        g.drawString(this.speed + " km/h", 160, 300);
    }

    public void createEngine(int cylCapacity) {
        this.myEngine = new Engine(cylCapacity);
        this.myEngine.setMaxSpeed();
        this.myEngine.setDrawable(this);
    }

    public void createTire(String type) {
        this.myTire = new Tire(type);
        this.myTire.setMaxSpeed();
    }
    
    public void setDrawable(Drawable drawable){
        this.drawable = drawable;
    }

    @Override
    public void redraw(int x, int y, int width, int height) {
        this.drawable.redraw(x, y, width, height);
    }
}
