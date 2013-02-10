package markharder.mathdraw;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Color;
import java.awt.Point;
import java.awt.Font;

/**
 * A simple graphical slider
 *
 * @author Mark Harder
 * @version 1.0
 */
public class Slider {
    private Rectangle slide;
    private Rectangle slot;
    private String name;
    public boolean active = false;

    public Slider(String name, int x, int y, int width , int height, int startingValue) {
        this.name = name;
        slot = new Rectangle(x + (width - 5) / 2, y, 5, height);
        
        int yValue = y - width / 2 + (int) (startingValue * (height + width / 2) / 100.0);

        slide = new Rectangle(x, yValue, width, width);
    }

    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        g2.setColor(new Color(255, 255, 255));
        g2.setFont(new Font("Courier New", Font.BOLD, 14));
        int strLength = (int) g2.getFontMetrics().getStringBounds(name, g2).getWidth();
        g2.drawString(name, slot.x - strLength / 2, slot.y - 20);

        g.setColor(new Color(255, 255, 255, 120));
        g.fillRect(slot.x, slot.y, slot.width, slot.height);

        if (active && Mouse.pressed) {
            if (Mouse.mse.y < slot.y - slide.width / 2) {
                slide.setLocation(slide.x, slot.y - slide.width / 2);
            } else if (Mouse.mse.y > slot.y + slot.height - slide.width / 2) {
                slide.setLocation(slide.x, slot.y + slot.height - slide.width / 2);
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
        return p.y > slot.y - slide.width / 2 && p.y < slot.y + slot.height + slide.width / 2 && p.x > slide.x && p.x < slide.x + slide.width;
    }
}

