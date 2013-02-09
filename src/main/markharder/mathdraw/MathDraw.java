package markharder.mathdraw;

import javax.swing.JFrame;
import javax.swing.JComponent;
import java.awt.Graphics;
import java.awt.Color;

/**
 * A series of mathematical based drawings
 *
 * @author Mark Harder
 * @version 1.0
 */
public class MathDraw extends JComponent {
    private static MathDraw canvas;
    private static boolean running = false;

    // the width and height of the entire screen
    private static int SCREEN_WIDTH;
    private static int SCREEN_HEIGHT;

    // the width and height of the application
    private static int WIDTH = 600;
    private static int HEIGHT = 600;

    // the location of the upper left corner of the application
    private static int CORNER_X;
    private static int CORNER_Y;

    public MathDraw() {
    }

    public void paint(Graphics g) {
        g.setColor(new Color(0, 0, 0));
        g.fillRect(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
        g.setColor(new Color(255, 255, 255));
        g.fillRect(CORNER_X, CORNER_Y, WIDTH, HEIGHT);
    }

    public void start() {
        running = true;

        while(running) {
            repaint();
        }
    }

    public void stop() {
        running = false;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();

        frame.setUndecorated(true);
        frame.pack();
        
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);

        SCREEN_WIDTH = frame.getWidth();
        SCREEN_HEIGHT = frame.getHeight();
        CORNER_X = (SCREEN_WIDTH - WIDTH) / 2;
        CORNER_Y = (SCREEN_HEIGHT - HEIGHT) / 2;

        canvas = new MathDraw();

        frame.add(canvas);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        canvas.start();
        System.exit(0);
    }
}

