package markharder.mathdraw;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.awt.Point;

/**
 * A class to represent a mouse and handle actions
 *
 * @author Mark Harder
 * @version 1.0
 */
public class Mouse implements MouseMotionListener, MouseListener {
    public static Point mse = new Point(0, 0);
    public static boolean pressed = false;

    public Mouse() {
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        MathDraw.canvas.click();
        pressed = true;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        pressed = false;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        mse = new Point(e.getX(), e.getY());
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mse = new Point(e.getX(), e.getY());
    }
}

