package kz.greetgo.utilgg.law;

import java.awt.Color;
import java.awt.Graphics2D;

public class Painter {
  public double scale;

  public double deltaX, deltaY;

  public void paintBorder(Graphics2D g, Border b, int width, int height) {

    int X1 = (int) Math.round(b.x1 * scale + deltaX);
    int Y1 = (int) Math.round(b.y1 * scale + deltaY);

    int X2 = (int) Math.round(b.x2 * scale + deltaX);
    int Y2 = (int) Math.round(b.y2 * scale + deltaY);

    g.setColor(colorFor(b.type));
    g.drawLine(X1, height - Y1, X2, height - Y2);
  }

  public void paintCell(Graphics2D g, Cell c, int width, int height) {

    int X = (int) Math.round(c.x * scale + deltaX);
    int Y = (int) Math.round(c.y * scale + deltaY);

    int W = (int) Math.round(c.width * scale);
    int H = (int) Math.round(c.height * scale);

    g.setColor(Color.white);
    g.fillRect(X, height - Y - H, W, H);

  }

  private static Color colorFor(BorderType type) {
    switch (type) {
      case INNER:
        return Color.gray;
      case OUTER:
        return Color.green.darker();
      case WALL:
        return Color.black;
    }
    throw new IllegalArgumentException("Unknown type = " + type);
  }
}
