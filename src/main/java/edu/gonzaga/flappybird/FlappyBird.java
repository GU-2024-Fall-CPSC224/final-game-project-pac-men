package edu.gonzaga.flappybird;

import java.awt.*;
import java.awt.event.*;
//import java.nio.channels.Pipe;
import java.util.ArrayList;
import java.util.Objects;
import javax.swing.*;


public class FlappyBird extends JPanel implements ActionListener, KeyListener {

    public static int boardWidth = 360;
    public static int boardHeight = 640;

    //images
    Image backgroundImg;
    Image birdImg;
    Image topPipeImg;
    Image bottomPipeImg;

    //game logic
    Bird bird;
    int velocityX = -4; //move pipes to the left speed (simulates bird moving right)
    int velocityY = 0; //move bird up/down speed.
    int gravity = 1;

    ArrayList<Pipe> pipes;

    Timer gameLoop;
    Timer placePipeTimer;
    boolean gameOver = false;
    double score = 0; // this is a double because we want to be able to add 0.5, to easily handle the logic of two pipes (top & bottom) being treated as one


    FlappyBird() {
        setPreferredSize(new Dimension(boardWidth, boardHeight));
        setBackground(Color.blue);

        setFocusable(true);
        addKeyListener(this);

        //load images
        backgroundImg = new ImageIcon(Objects.requireNonNull(getClass().getResource("/flappybird/flappybirdbg.png"))).getImage();
        birdImg = new ImageIcon(Objects.requireNonNull(getClass().getResource("/flappybird/flappybird.png"))).getImage();
        topPipeImg = new ImageIcon(Objects.requireNonNull(getClass().getResource("/flappybird/toppipe.png"))).getImage();
        bottomPipeImg = new ImageIcon(Objects.requireNonNull(getClass().getResource("/flappybird/bottompipe.png"))).getImage();
//
//
        bird = new Bird(birdImg);
        pipes = new ArrayList<>();

        //place pipes timer
        placePipeTimer = new Timer(1500, e -> {
            // Code to be executed
            placePipes();
        });
        placePipeTimer.start();

        //game timer
        gameLoop = new Timer(1000/60, this); //how long it takes to start timer, milliseconds gone between frames
        gameLoop.start();
    }

    public void placePipes() {
        //(0-1) * pipeHeight/2.
        // 0 -> -128 (pipeHeight/4)
        // 1 -> -128 - 256 (pipeHeight/4 - pipeHeight/2) = -3/4 pipeHeight
        int randomPipeY = (int) (0 - (double) Pipe.height /4 - Math.random()*((double) Pipe.height /2));
        int openingSpace = boardHeight/4;

        Pipe topPipe = new Pipe(topPipeImg);
        topPipe.y = randomPipeY;
        pipes.add(topPipe);

        Pipe bottomPipe = new Pipe(bottomPipeImg);
        bottomPipe.y = topPipe.y  + Pipe.height + openingSpace;
        pipes.add(bottomPipe);
    }


    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g) {
//        System.out.println("draw");

        //background
        g.drawImage(backgroundImg, 0, 0, boardWidth,  boardHeight, null);
        //bird
        g.drawImage(birdImg, bird.x, bird.y, Bird.width, Bird.height, null);

        //pipes
        for (Pipe pipe : pipes) {
            g.drawImage(pipe.img, pipe.x, pipe.y, Pipe.width, Pipe.height, null);
        }

        //score
        g.setColor(Color.white);
//
        g.setFont(new Font("Arial", Font.PLAIN, 32));
        if (gameOver) {
            g.drawString("Game Over: " + (int) score, 10, 35);
        }
        else {
            g.drawString("" + (int) score, 10, 35);
        }
    }

    public void move() {
        //bird
        velocityY += gravity;
        bird.y += velocityY;
        bird.y = Math.max(bird.y, 0); //apply gravity to current bird.y, limit the bird.y to top of the canvas

        //pipes
        for (Pipe pipe : pipes) {
            pipe.x += velocityX;

            if (!pipe.passed && bird.x > pipe.x + Pipe.width) {
                score += 0.5; //0.5 because there are 2 pipes, so 0.5*2 = 1, 1 for each set of pipes
                pipe.passed = true;
            }

            if (collision(bird, pipe)) {
                gameOver = true;
            }
        }

        if (bird.y > boardHeight) {
            gameOver = true;
        }
    }


    boolean collision(Bird a, Pipe b) {
        return a.x < b.x + Pipe.width &&   //a's top left corner doesn't reach b's top right corner
                a.x + Bird.width > b.x &&   //a's top right corner passes b's top left corner
                a.y < b.y + Pipe.height &&  //a's top left corner doesn't reach b's bottom left corner
                a.y + Bird.height > b.y;    //a's bottom left corner passes b's top left corner
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        move();
        repaint();
        if (gameOver) {
            placePipeTimer.stop();
            gameLoop.stop();

        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            // System.out.println("JUMP!");
            velocityY = -9;

            if (gameOver) {
                //restart game by resetting conditions
                bird.y = FlappyBird.boardHeight/2;
                velocityY = 0;
                pipes.clear();
                gameOver = false;
                score = 0;
                gameLoop.start();
                placePipeTimer.start();
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {}

}
