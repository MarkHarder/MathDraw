package markharder.mathdraw;

import javax.swing.JFrame;
import javax.swing.JComponent;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;

import java.util.ArrayList;

import java.lang.Math;

/**
 * A series of mathematical based drawings
 *
 * @author Mark Harder
 * @version 1.0
 */
public class MathDraw extends JComponent implements Runnable {
    public static MathDraw canvas;
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

    private Slider rectangleAngle;

    private ArrayList<Point> points = new ArrayList<Point>();

    public String mode;

    public MathDraw() {
        mode = "Heart";

        Point center = new Point(CORNER_X + WIDTH / 2, CORNER_Y + HEIGHT / 2);
        int r = 250;

        for (int i = -9; i < 27; i++) {
            double x = center.x + r * Math.cos(i*Math.PI*10/180);
            double y = center.y + r * Math.sin(i*Math.PI*10/180);
            points.add(new Point((int) x, (int) y));
        }
    }

    public void click() {
        if (mode == "Rectangle") {
            if (rectangleAngle.contains(Mouse.mse)) {
                rectangleAngle.active = true;
            }
        }
    }

    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        g2.setColor(new Color(0, 0, 0));
        g2.fillRect(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
        g2.setColor(new Color(255, 255, 255));
        g2.fillRect(CORNER_X, CORNER_Y, WIDTH, HEIGHT);

        Point center = new Point(CORNER_X + WIDTH / 2, CORNER_Y + HEIGHT / 2);

        if (mode.equals("Rectangle")) {
            int adjust = 91 - (int) (rectangleAngle.getValue() * 90 / 100);

            for (double theta = 0.0; theta < 90.0; theta += adjust) {

                double radians = theta * Math.PI / 180;
                int width = 200;

                Point p1 = new Point((int) (width * Math.sin(radians) + center.getX()), (int) (width * Math.cos(radians) + center.getY()));
                Point p2 = new Point((int) (width * Math.sin(radians + Math.PI / 2) + center.getX()), (int) (width * Math.cos(radians + Math.PI / 2) + center.getY()));
                Point p3 = new Point((int) (width * Math.sin(radians + Math.PI) + center.getX()), (int) (width * Math.cos(radians + Math.PI) + center.getY()));
                Point p4 = new Point((int) (width * Math.sin(radians + 3 * Math.PI / 2) + center.getX()), (int) (width * Math.cos(radians + 3 * Math.PI / 2) + center.getY()));

                g2.setColor(Color.BLUE);
                g2.setStroke(new BasicStroke(4F));
                g2.drawLine((int) p1.getX(), (int) p1.getY(), (int) p2.getX(), (int) p2.getY());
                g2.drawLine((int) p2.getX(), (int) p2.getY(), (int) p3.getX(), (int) p3.getY());
                g2.drawLine((int) p3.getX(), (int) p3.getY(), (int) p4.getX(), (int) p4.getY());
                g2.drawLine((int) p4.getX(), (int) p4.getY(), (int) p1.getX(), (int) p1.getY());
            }

            rectangleAngle.paint(g);
        } else if (mode.equals("Circle")) {
            for (double theta = 0.0; theta == 0.0 || theta % 360.0 != 0; theta += 30) {
                double radians = theta * Math.PI / 180;
                int center_diameter = 150;
                int diameter = 150;

                Point p1 = new Point((int) (center_diameter * Math.sin(radians) + center.getX()), (int) (center_diameter * Math.cos(radians) + center.getY()));

                g2.setColor(Color.GREEN);
                g2.setStroke(new BasicStroke(4F));
                g2.drawOval((int) (p1.getX() - diameter / 2), (int) (p1.getY() - diameter / 2), diameter, diameter);
            }
        } else if (mode.equals("Heart")) {
            int r = 250;

            g2.setColor(Color.RED);
            g2.setStroke(new BasicStroke(2F));

            for (int i = 0; i <= points.size() / 4; i++) {
                Point p1 = points.get(i);
                Point p2 = points.get(i + 9);
                g.drawLine(p1.x, p1.y, p2.x, p2.y);
            }
            for (int i = points.size() / 2; i <= points.size() * 3 / 4; i++) {
                Point p1 = points.get(i);
                Point p2 = points.get((i + 9) % points.size());
                g.drawLine(p1.x, p1.y, p2.x, p2.y);
            }
            for (int i = 0; i <= points.size() / 4; i++) {
                Point p1 = points.get(i);
                Point p2 = points.get((18 + i * 2) % points.size());
                g.drawLine(p1.x, p1.y, p2.x, p2.y);
            }
            for (int i = points.size(); i >= points.size() * 3 / 4; i--) {
                Point p1 = points.get(i % points.size());
                Point p2 = points.get((18 - (points.size() - i) * 2) % points.size());
                g.drawLine(p1.x, p1.y, p2.x, p2.y);
            }
        }
    }

    public void start() {
        running = true;

        new Thread(this).start();
    }

    public void run() {
        while(running) {
            repaint();

            try {
                Thread.sleep(9);
            } catch (Exception e) {
                e.printStackTrace();
            }
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
        canvas.rectangleAngle = new Slider("Angle of Rotation", CORNER_X + WIDTH + 20, CORNER_Y, 25, 600, 50);

        frame.add(canvas);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        frame.addKeyListener(new Listener());
        frame.addMouseListener(new Mouse());
        frame.addMouseMotionListener(new Mouse());


        canvas.start();
    }
}

