package markharder.mathdraw;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Color;
import java.awt.Point;

/**
 * A simple graphical slider
 *
 * @author Mark Harder
 * @version 1.0
 */
public class Slider {
    private Rectangle slide;
    private Rectangle slot;
    public boolean active = false;

    public Slider(int x, int y, int width , int height, int startingValue) {
        slot = new Rectangle(x + (width - 5) / 2, y, 5, height);
        
        int yValue = y - width / 2 + (int) (startingValue * (height + width / 2) / 100.0);

        slide = new Rectangle(x, yValue, width, width);
    }

    public void paint(Graphics g) {
        g.setColor(new Color(255, 255, 255, 120));
        g.fillRect(slot.x, slot.y, slot.width, slot.height);

        if (active && Mouse.pressed) {
            if (Mouse.mse.y < slot.y - slide.width / 2) {
                slide.setLocation(slide.x, slot.y - slide.width / 2);
            } else if (Mouse.mse.y > slot.y + slot.height) {
                slide.setLocation(slide.x, slot.y + slot.height);
            } else {
                slide.setLocation(slide.x, Mouse.mse.y);
            }
        }

        g.setColor(Color.WHITE);
        g.fillRect(slide.x, slide.y, slide.width, slide.height);
    }

    public double getValue() {
        return (slide.y - slot.y) * 100.0 / (slot.height + slide.width / 2);
    }

    public boolean contains(Point p) {
        return p.y > slot.y && p.y < slot.y + slot.height && p.x > slide.x && p.x < slide.x + slide.width;
    }
}

