# -_snake_en_java_swing_aplicacionCompleta_- :.

<img width="1024" height="1024" alt="image" src="https://github.com/user-attachments/assets/2d5b8c41-a9de-4b0b-8fad-afb1cfc88363" />  

<img width="1279" height="1075" alt="image" src="https://github.com/user-attachments/assets/0c3fc9b7-6f48-4549-94ab-1411fa63f4a0" />  

# Snake en Java (Swing) — Aplicación Completa .

Implementación completa y funcional del clásico **Snake en Java**, diseñada para ejecutarse directamente en **IntelliJ IDEA**, utilizando **Java Swing** para la interfaz gráfica.

El resultado es un juego clásico, estable, bien estructurado y listo para compilar y ejecutar.

---

## Características Incluidas

- Interfaz gráfica desarrollada con **Java Swing**
- Movimiento fluido mediante `Timer`
- Control del juego con teclado (flechas direccionales)
- Generación aleatoria de comida
- Crecimiento dinámico de la serpiente
- Detección de colisiones:
  - Contra paredes
  - Contra el propio cuerpo
- Pantalla de **Game Over**
- Código contenido en **un solo archivo**
- Ideal para proyectos académicos o demostrativos

---

## Instrucciones de Uso en IntelliJ IDEA

1. Crear un **nuevo proyecto Java**
2. Crear una clase llamada `SnakeGame`
3. Copiar y pegar el código fuente completo
4. Ejecutar el programa (`Shift + F10`)

---

## Código Fuente Completo

```java
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class SnakeGame extends JPanel implements ActionListener {

    // Configuración general
    private static final int TILE_SIZE = 25;
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
        foodX = random.nextInt(WIDTH / TILE_SIZE) * TILE_SIZE;
        foodY = random.nextInt(HEIGHT / TILE_SIZE) * TILE_SIZE;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    private void draw(Graphics g) {
        if (running) {
            // Dibujar comida
            g.setColor(Color.RED);
            g.fillOval(foodX, foodY, TILE_SIZE, TILE_SIZE);

            // Dibujar serpiente
            for (int i = 0; i < snakeLength; i++) {
                if (i == 0) {
                    g.setColor(Color.GREEN);
                } else {
                    g.setColor(new Color(45, 180, 0));
                }
                g.fillRect(snakeX[i], snakeY[i], TILE_SIZE, TILE_SIZE);
            }

            // Puntaje
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

        switch (direction) {
            case 'U' -> snakeY[0] -= TILE_SIZE;
            case 'D' -> snakeY[0] += TILE_SIZE;
            case 'L' -> snakeX[0] -= TILE_SIZE;
            case 'R' -> snakeX[0] += TILE_SIZE;
        }
    }

    private void checkFood() {
        if (snakeX[0] == foodX && snakeY[0] == foodY) {
            snakeLength++;
            generateFood();
        }
    }

    private void checkCollisions() {
        // Colisión con el cuerpo
        for (int i = snakeLength; i > 0; i--) {
            if (snakeX[0] == snakeX[i] && snakeY[0] == snakeY[i]) {
                running = false;
            }
        }

        // Colisión con bordes
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
        g.drawString(
                "GAME OVER",
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

    // Método main
    public static void main(String[] args) {
        JFrame frame = new JFrame("Snake - Java Swing");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.add(new SnakeGame());
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
```
Copyright © 2025  
Autor: Giovanny Alejandro Tapiero Cataño & chatGpt .

Este proyecto ha sido desarrollado con fines educativos.
El autor conserva todos los derechos sobre el código y los recursos asociados.
Cualquier uso comercial o redistribución requiere autorización previa y por escrito.
