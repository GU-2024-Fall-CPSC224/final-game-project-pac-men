package edu.gonzaga.NewPong;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener {

    private boolean introScreen = true;

    // Setup Later
    private boolean setUpScreen = false;
    private boolean gamePlayScreen = false;
    private boolean endScreen = false;

    private JButton startButton;
    private Timer timer;
    private Image image;

    public GamePanel() {
        setLayout(null); 

        
        image = Toolkit.getDefaultToolkit().getImage("setUpImage.png");

        // Make Start Button
        startButton = new JButton("Start Game!");
        startButton.setBounds(300, 400, 200, 50);
        startButton.addActionListener(this);
        add(startButton);

        // Make it 60 FPS
        timer = new Timer(16, this);
        timer.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (introScreen) {
            g.setColor(Color.BLACK);
            g.fillRect(0, 0, getWidth(), getHeight());

            if (image != null) {
                g.drawImage(image, 150, 50, 500, 300, this);
            } else {
                g.setColor(Color.WHITE);
                g.setFont(new Font("Arial", Font.BOLD, 48));
                g.drawString("PONG", 320, 200);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == startButton) {
            // Switch from setup to game
            introScreen = false;
            introScreen = true;
            remove(startButton);
            repaint();
        }
    }
}
