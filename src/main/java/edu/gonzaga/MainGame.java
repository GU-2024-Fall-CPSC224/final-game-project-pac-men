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

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.io.InputStream;

public class MainGame {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                new GameWindow();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}

class GameWindow extends JFrame {
    public GameWindow() throws IOException {
        BufferedImage iconImage = ImageIO.read(getClass().getResource("/pacman/ghosts/Sprite-PurpleGhost/PurpleGhostDown-2.png.png"));
        setIconImage(iconImage);

        // Load arcade image
        BufferedImage arcadeImage = ImageIO.read(getClass().getResource("/main-screen/ArcadeBorder-Pixel.png"));
        int width = arcadeImage.getWidth();
        int height = arcadeImage.getHeight();

        // Configure frame (normal window with title bar)
        setTitle("Arcade Games");
        setSize(width, height);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        // Add game panel
        GamePanel panel = new GamePanel(this, arcadeImage);
        setContentPane(panel);
        setVisible(true);
    }
}

class GamePanel extends JPanel {
    private BufferedImage arcadeImage;
    private BufferedImage spaceImage;
    private List<Double> spaceXPositions = new ArrayList<>();
    private Timer animationTimer;
    private double scrollSpeed = 0.5;
    private JFrame frame;

    public GamePanel(JFrame frame, BufferedImage arcadeImage) {
        this.frame = frame;
        this.arcadeImage = arcadeImage;

        try {
            spaceImage = ImageIO.read(getClass().getResource("/main-screen/SpaceBackground.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.BLACK);

        // Initialize X positions for scrolling background
        int panelWidth = arcadeImage.getWidth();
        for (int x = 0; x < panelWidth * 2; x += spaceImage.getWidth()) {
            spaceXPositions.add((double) x);
        }

        startAnimation();
        addButtons();
    }

    private void startAnimation() {
        animationTimer = new Timer(16, e -> {
            for (int i = 0; i < spaceXPositions.size(); i++) {
                double newX = spaceXPositions.get(i) - scrollSpeed;
                if (newX + spaceImage.getWidth() <= 0) {
                    double maxX = spaceXPositions.stream().max(Double::compare).orElse(0.0);
                    newX = maxX + spaceImage.getWidth();
                }
                spaceXPositions.set(i, newX);
            }
            repaint();
        });
        animationTimer.start();
    }

    private void addButtons() {
        StyledButton pacmanButton = new StyledButton("Play Pacman");
        StyledButton pongButton = new StyledButton("Play Pong");
        StyledButton flappyButton = new StyledButton("Play Flappy Bird");

        Dimension buttonSize = new Dimension(350, 70);
        pacmanButton.setMaximumSize(buttonSize);
        pongButton.setMaximumSize(buttonSize);
        flappyButton.setMaximumSize(buttonSize);

        pacmanButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        pongButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        flappyButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        add(Box.createVerticalGlue());
        add(pacmanButton);
        add(Box.createVerticalStrut(20));
        add(pongButton);
        add(Box.createVerticalStrut(20));
        add(flappyButton);
        add(Box.createVerticalGlue());

        pacmanButton.addActionListener(e -> {
            frame.dispose(); // Close launcher window
            edu.gonzaga.pacman.PacMan.main(new String[]{});
        });

        pongButton.addActionListener(e -> {
            frame.dispose();
            edu.gonzaga.NewPong.Pong.main(new String[]{});
        });

        flappyButton.addActionListener(e -> {
            frame.dispose();
            edu.gonzaga.flappybird.App.main(new String[]{});
        });
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw scrolling space background
        for (double x : spaceXPositions) {
            g.drawImage(spaceImage, (int) x, 0, spaceImage.getWidth(), getHeight(), null);
        }

        // Draw arcade overlay on top
        g.drawImage(arcadeImage, 0, 0, getWidth(), getHeight(), null);
    }
}


class StyledButton extends JButton {
    private static Font pixelFont;

    static {
        // Load the custom font once when the class is loaded
        try {
            InputStream is = StyledButton.class.getResourceAsStream("/pacman/fonts/Joystix.TTF");
            if (is != null) {
                pixelFont = Font.createFont(Font.TRUETYPE_FONT, is)
                        .deriveFont(Font.BOLD, 28f);
                GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
                ge.registerFont(pixelFont);
            } else {
                pixelFont = new Font("Monospaced", Font.BOLD, 28); // fallback
            }
        } catch (Exception e) {
            e.printStackTrace();
            pixelFont = new Font("Monospaced", Font.BOLD, 28); // fallback
        }
    }

    public StyledButton(String text) {
        super(text);
        setFont(pixelFont);
        setContentAreaFilled(false);
        setFocusPainted(false);
        setBorderPainted(true);
        setOpaque(false);
        setForeground(Color.WHITE);
        setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(350, 70);
    }


    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();

        // Draw shadowed text
        FontMetrics fm = g2.getFontMetrics();
        String text = getText();
        int textWidth = fm.stringWidth(text);
        int textHeight = fm.getAscent();
        int x = (getWidth() - textWidth) / 2;
        int y = (getHeight() + textHeight) / 2 - 4;

        g2.setFont(getFont());
        g2.setColor(Color.GRAY); // shadow
        g2.drawString(text, x + 2, y + 2);
        g2.setColor(getForeground()); // main text
        g2.drawString(text, x, y);

        g2.dispose();
        super.paintComponent(g); // draw border
    }
}


