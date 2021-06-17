import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.security.PrivateKey;

public class GameWindow extends JFrame {
    private static GameWindow gameWindow;
    private static long lastFrameTime;
    private static Image background;
    private static Image gameOver;
    private static Image drop;
    private static float dropLeft = 200;
    private static float dropTop = -100;
    private static float dropV = 200;


    public static void main(String[] args) throws IOException {
        background = ImageIO.read(GameWindow.class.getResourceAsStream("background.jpg"));
        gameOver = ImageIO.read(GameWindow.class.getResourceAsStream("gameOver.png"));
        drop = ImageIO.read(GameWindow.class.getResourceAsStream("drop.png"));
        gameWindow = new GameWindow();
        gameWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        gameWindow.setLocation(200, 100);
        gameWindow.setSize(906,478);
        gameWindow.setResizable(false);
        lastFrameTime = System.nanoTime();
        GameField game_field = new GameField();
        gameWindow.add(game_field);
        gameWindow.setVisible(true);
    }

    private static void onRepaint (Graphics g){
        long currentTime = System.nanoTime();
        float deltaTime = (currentTime - lastFrameTime) *0.000000001f;
        lastFrameTime = currentTime;

        dropTop = dropTop + dropV*deltaTime;
        g.drawImage(background, 0, 0 ,null);
        g.drawImage(drop, (int)dropLeft, (int)dropTop, null);
        g.drawImage(gameOver,280,120,null);
    }

    private static class  GameField extends JPanel{
        @Override
        protected void paintComponent(Graphics g){
            super.paintComponent(g);
            onRepaint(g);
            repaint();
        }
    }
}
