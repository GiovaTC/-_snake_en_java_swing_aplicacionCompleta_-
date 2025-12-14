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
                }
            }
        }
    }


    public static void main(String[] args) {

    }
}