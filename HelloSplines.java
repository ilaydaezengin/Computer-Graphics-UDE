package submission.IlaydaZengin;

import java.util.List;
import java.util.ArrayList;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class HelloSplines {

  public static void main(String[] args) {
    helloJava2D();
    helloBezier();
  }

  public static void helloJava2D() {
    // create a new 500x500 pixel sized image
    Dimension dim = new Dimension(500, 500);
    BufferedImage img = new BufferedImage(dim.width, dim.height, BufferedImage.TYPE_INT_RGB);

    // create simple 2D drawing tools and fill canvas with background color
    Graphics2D g2d = img.createGraphics();
    g2d.setColor(Color.WHITE);
    g2d.fillRect(0, 0, dim.width, dim.height);

    // draw some random lines and circles
    g2d.setColor(Color.BLACK);
    g2d.drawLine(15, 100, 15, 400);
    g2d.drawLine(155, 100, 155, 400);
    g2d.drawLine(15, 240, 155, 240);
    g2d.setColor(Color.BLUE);
    g2d.fillOval(10, 235, 10, 10);
    g2d.fillOval(150, 235, 10, 10);
    g2d.setColor(Color.CYAN);
    g2d.drawLine(175, 100, 175, 400);
    g2d.drawLine(175, 100, 315, 100);
    g2d.drawLine(175, 240, 315, 240);
    g2d.drawLine(315, 100, 315, 240);
    g2d.setColor(Color.MAGENTA);
    g2d.fillOval(175, 100, 140, 140);
    g2d.setColor(Color.GREEN);
    g2d.drawLine(335, 100, 335, 400);
    g2d.drawLine(335, 100, 485, 100);
    g2d.drawLine(335, 400, 485, 400);

    assignment.ImageHelper.saveAndDisplay(img, "LinesAndCircles.png");
  }

  public static void helloBezier() {
    // create a new 500x500 pixel sized image
    Dimension dim = new Dimension(500, 500);
    BufferedImage img2 = new BufferedImage(dim.width, dim.height, BufferedImage.TYPE_INT_RGB);

    // create simple 2D drawing tools and fill canvas with background color
    Graphics2D g2 = img2.createGraphics();
    g2.setColor(Color.BLACK);
    g2.fillRect(0, 0, dim.width, dim.height);

    // duplicate the empty canvas to draw more than one image
    BufferedImage img3 = assignment.ImageHelper.copy(img2);
    BufferedImage img5 = assignment.ImageHelper.copy(img2);
    Graphics2D g3 = img3.createGraphics();
    Graphics2D g5 = img5.createGraphics();

    // define some points for the control polygon
    List<Point> points = new ArrayList<>();
    points.add(new Point(10, 10));
    points.add(new Point(300, 10));
    points.add(new Point(400, 200));

    // instantiate a Bézier curve and draw this quadratic curve with just 3 line segments
    Bezier curve = new Bezier(points);
    curve.draw(g2, 3);

    // add another point to the control polygon and draw the cubic curve with 4 line segments
    points.add(new Point(50, 400));
    curve.draw(g3, 4);

    // add two more points and draw the resulting quintic curve with the optimal number of line
    // segments
    points.add(new Point(490, 490));
    points.add(new Point(490, 300));
    curve.draw(g5);

    // ATTENTION: Do not change the following code lines for your submission!
    // You are required to use ImageHelper.saveAndDisplay(...) from the package assignment
    // to display and save your solution images.
    assignment.ImageHelper.saveAndDisplay(img2, "Quadratic.png");
    assignment.ImageHelper.saveAndDisplay(img3, "Cubic.png");
    assignment.ImageHelper.saveAndDisplay(img5, "Quintic.png");
  }

}