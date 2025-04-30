/*
 * Final project main driver class
 * 
 * 
 * Project Description:
 * 
 * 
 * Contributors:
 *
 * 
 * Copyright: 2023
 */
package edu.gonzaga;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import edu.gonzaga.NewPong.Pong;

/** Main program class for launching the 'Game Launcher'. */
public class MainGame {
    public static void main(String[] args) {
        // Setup main frame
        JFrame frame = new JFrame("Game Launcher");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new GridLayout(4, 1, 10, 10)); // 4 rows, 1 column

        // Title label
        JLabel titleLabel = new JLabel("Select a Game", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        frame.add(titleLabel);

        // PacMan Button
        JButton pacManButton = new JButton("Play PacMan");
        pacManButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose(); // Close launcher window
                edu.gonzaga.pacman.PacMan.main(new String[]{}); // Start PacMan
            }
        });
        frame.add(pacManButton);

        // Flappy Bird Button
        JButton flappyButton = new JButton("Play Flappy Bird");
        flappyButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                try {
                    edu.gonzaga.flappybird.App.main(new String[]{}); // Start Flappy Bird
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        frame.add(flappyButton);

        // Pong Button
        JButton pongButton = new JButton("Play Pong");
        pongButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                Pong.main(new String[]{}); // Start new Pong Game
            }
        });
        frame.add(pongButton);

        frame.setVisible(true);
    }
}

