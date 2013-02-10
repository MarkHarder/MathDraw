package markharder.mathdraw;

import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

/**
 * A KeyListener to switch modes
 *
 * @author Mark Harder
 * @version 1.0
 */
public class Listener implements KeyListener {
    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        switch (key) {
        case KeyEvent.VK_1:
            MathDraw.canvas.mode = "Instructions";
            break;
        case KeyEvent.VK_2:
            MathDraw.canvas.mode = "Rectangle";
            break;
        case KeyEvent.VK_3:
            MathDraw.canvas.mode = "Circle";
            break;
        case KeyEvent.VK_4:
            MathDraw.canvas.mode = "Heart";
            break;
        case KeyEvent.VK_5:
            MathDraw.canvas.mode = "Clear";
            break;
        case KeyEvent.VK_Q:
            MathDraw.canvas.stop();
            break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

}

