import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;


public class SnakeGame extends JPanel implements ActionListener {

    // configuracion general
    private static final int TiLE_SIZE = 25;
    private static final int WIDTH = 600;
    private static final int HEIGHT = 600;
    private static final int DELAY = 120;

    // Estado del juego
    private final int[] snakeX = new int[WIDTH * HEIGHT];
    private final int[] snakeY = new int[WIDTH * HEIGHT];
    private int snakeLength = 3;

    private int foodX;
    private int foodY;

    private char direction = 'R';
    private boolean running = false;

    private Timer timer;
    private final Random random = new Random();

    public SnakeGame() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.BLACK);
        setFocusable(true);
        addKeyListener(new SnakeKeyAdapter());
        startGame();
    }

    private void startGame() {
        generateFood();
        running = true;
        timer = new Timer(DELAY, this);
        timer.start();
    }

    private void generateFood() {
        foodX = random.nextInt(WIDTH / TiLE_SIZE) * TiLE_SIZE;
        foodY = random.nextInt(HEIGHT / TiLE_SIZE) * TiLE_SIZE;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    private void draw(Graphics g) {
        if (running) {
            // Dibujar comida
            g.setColor(Color.RED);
            g.fillOval(foodX, foodY, TiLE_SIZE, TiLE_SIZE);

            // dibujar serpiente
            for (int i = 0; i < snakeX.length; i++) {
                if (i == 0) {
                    g.setColor(Color.GREEN);
                } else {
                    g.setColor(new Color(45, 180, 0));
                }
                g.fillRect(snakeX[i], snakeY[i], TiLE_SIZE, TiLE_SIZE);
            }

            // puntaje
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.BOLD, 18));
            g.drawString("Score: " + (snakeLength - 3), 10, 20);

        } else {
            gameOver(g);
        }
    }

    private void move() {
        for (int i = snakeLength; i > 0; i--) {
            snakeX[i] = snakeX[i - 1];
            snakeY[i] = snakeY[i - 1];
        }

        switch(direction) {
            case 'U' -> snakeY[0] -= TiLE_SIZE;
            case 'D' -> snakeY[0] += TiLE_SIZE;
            case 'L' -> snakeX[0] -= TiLE_SIZE;
            case 'R' -> snakeX[0] += TiLE_SIZE;
        }
    }

    private void checkFood() {
        if (snakeX[0] == foodX && snakeY[0] == foodY) {
            snakeLength++;
            generateFood();
        }
    }

    private void checkCollisions() {
        // colision con el cuerpo
        for (int i = snakeLength; i > 0; i--) {
            if (snakeX[0] == snakeX[i] && snakeY[0] == snakeY[i]) {
                running = false;
            }
        }

        // colision con bordes
        if (snakeX[0] < 0 || snakeX[0] >= WIDTH ||
            snakeY[0] < 0 || snakeY[0] >= HEIGHT) {
            running = false;
        }

        if (!running) {
            timer.stop();
        }
    }

    private void gameOver(Graphics g) {
        g.setColor(Color.RED);
        g.setFont(new Font("Arial", Font.BOLD, 40));
        FontMetrics metrics = getFontMetrics(g.getFont());
        g.drawString("GAME OVER",
                (WIDTH - metrics.stringWidth("GAME OVER")) / 2,
                HEIGHT / 2
        );

        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.PLAIN, 18));
        g.drawString(
                "Score: " + (snakeLength - 3),
                (WIDTH - 80) / 2,
                HEIGHT / 2 + 40
        );
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (running) {
            move();
            checkFood();
            checkCollisions();
        }
        repaint();
    }

    private class SnakeKeyAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_LEFT -> {
                    if (direction != 'R') direction = 'L';
                }
                case KeyEvent.VK_RIGHT -> {
                    if (direction != 'L') direction = 'R';
                }
                case KeyEvent.VK_UP -> {
                    if (direction != 'D') direction = 'U';
                }
                case KeyEvent.VK_DOWN -> {
                    if (direction != 'U') direction = 'D';
                }
            }
        }
    }

    // metodo main
    public static void main(String[] args) {
        JFrame frame = new JFrame("Snake - Java Swing");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.add(new SnakeGame());
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}