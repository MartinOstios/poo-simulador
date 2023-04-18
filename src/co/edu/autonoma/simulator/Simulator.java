package co.edu.autonoma.simulator;

import co.edu.autonoma.exceptions.*;
import co.edu.autonoma.vehicle.Vehicle;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.Timer;

public class Simulator extends javax.swing.JFrame {

    Vehicle vehicle;
    private int magnitude = 0;
    // TIMER
    Timer timer;
    private final int INITIAL_DELAY = 400;

    public Simulator() {
        initComponents();
        setLocationRelativeTo(null);
        setTitle("Simulador");
        setResizable(false);
        timer = new Timer(INITIAL_DELAY, action);
        vehicle = new Vehicle();
        try {
            RandomAccessFile file = new RandomAccessFile("C:/Users/izibr/OneDrive/Escritorio/Universidad/ProOri/Simulador/src/co/edu/autonoma/config/config.txt", "r");
            String line1 = file.readLine();
            String line2 = file.readLine();
            vehicle.createTire(line1.split(" ")[1]);
            vehicle.createEngine(Integer.parseInt(line2.split(" ")[1]));
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e2) {
            System.out.println(e2.getMessage());
        }
    }

    @Override
    public void paint(Graphics g) {
        this.drawBackgroundImage(g);
        this.drawComponents(g);
        this.drawMagnitude(g);
        this.drawVehicleVelocity(g);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                formMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                formMouseReleased(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1000, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 500, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
        if (isOnButtonClicked(evt)) {
            try {
                vehicle.turnOn();
            } catch (EngineOnException e) {
                JOptionPane.showMessageDialog(this, "Intentaste encender el vehículo pero este ya está encendido");
            }
        }
        if (isOffButtonClicked(evt)) {
            try {
                vehicle.turnOff();
            } catch (EngineOffException e1) {
                JOptionPane.showMessageDialog(this, "Intentaste apagar el vehículo pero este ya está apagado");
            } catch (EngineOffAtHighSpeedException e2) {
                JOptionPane.showMessageDialog(this, e2.getMessage());
                crash();
            }
        }
    }//GEN-LAST:event_formMouseClicked

    private void formMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMousePressed
        resetTimer();
        if (isSpeedButtonClicked(evt)) {
            timer.start();
        }
        if (isBrakeButtonClicked(evt)) {
            timer.start();
        }
    }//GEN-LAST:event_formMousePressed

    private void formMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseReleased
        timer.stop();
        if (isSpeedButtonClicked(evt)) {
            try {
                vehicle.speedUp(magnitude);
            } catch (EngineOffException e1) {
                JOptionPane.showMessageDialog(this, "No puedes acelerar porque el motor está apagado");
            } catch (EngineCapacityExceededException e2) {
                JOptionPane.showMessageDialog(this, e2.getMessage());
                crash();
            }
        }
        if (isBrakeButtonClicked(evt)) {
            try {
                vehicle.toBrake(magnitude);
            } catch (EngineOffException e1) {
                JOptionPane.showMessageDialog(this, "No puedes frenar porque el motor está apagado");
            } catch (TireCapacityExceededException e2) {
                JOptionPane.showMessageDialog(this, e2.getMessage());
                skid();
            } catch (BrakeMagnitudeExceededException e3) {
                JOptionPane.showMessageDialog(this, e3.getMessage());
                skid();
            }
        }
    }//GEN-LAST:event_formMouseReleased

    public void drawMagnitude(Graphics g) {
        g.setColor(Color.BLACK);
        g.setFont(g.getFont().deriveFont(30f));
        g.drawString("Magnitude: " + magnitude, this.getWidth() - 230, 80);
        //g.drawRect(940, 55, 60, 25);
        repaint(940, 55, 60, 25);
    }

    public void drawVehicleVelocity(Graphics g) {
        vehicle.updateData(g);
        g.drawRect(160, 275, 130, 30);
        repaint(160, 275, 130, 30);
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Simulator().setVisible(true);
            }
        });
    }

    public void drawBackgroundImage(Graphics g) {
        try {
            BufferedImage image = ImageIO.read(getClass().getResource("../imgs/background_image.png"));
            g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), this);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void drawComponents(Graphics g) {
        try {
            vehicle.drawSwitchButton(g, this);
            //g.drawRect(386, 396, 80, 80);
            repaint(386, 396, 80, 80);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        
    }

    public boolean isOnButtonClicked(MouseEvent evt) {
        if (evt.getX() > 626 & evt.getX() < 626 + 100) {
            if (evt.getY() >= 386 & evt.getY() < 386 + 100) {
                return true;
            }
        }
        return false;
    }

    public boolean isOffButtonClicked(MouseEvent evt) {
        if (evt.getX() > 496 & evt.getX() < 496 + 100) {
            if (evt.getY() >= 386 & evt.getY() < 386 + 100) {
                return true;
            }
        }
        return false;
    }

    public boolean isSpeedButtonClicked(MouseEvent evt) {
        if (evt.getX() > 886 & evt.getX() < 886 + 100) {
            if (evt.getY() >= 194 & evt.getY() < 194 + 292) {
                return true;
            }
        }
        return false;
    }

    public boolean isBrakeButtonClicked(MouseEvent evt) {
        if (evt.getX() > 756 & evt.getX() < 756 + 100) {
            if (evt.getY() >= 317 & evt.getY() < 317 + 168) {
                return true;
            }
        }
        return false;
    }

    public void crash() {
        JOptionPane.showMessageDialog(this, "¡Accidente! El motor se ha apagado y el vehículo se ha detenido");
        vehicle.crash();
    }

    public void skid() {
        JOptionPane.showMessageDialog(this, "¡Derrapando! El vehículo se ha detenido");
        vehicle.skid();
    }

    private ActionListener action = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            magnitude++;
            if (timer.getDelay() > 40) {
                timer.setDelay(INITIAL_DELAY - magnitude * 25);
            }
        }
    };

    public void resetTimer() {
        magnitude = 0;
        timer.setDelay(INITIAL_DELAY);
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
