package kz.greetgo.utilgg.law;

public class Border {
  public final BorderType type;
  public double x1, y1, x2, y2;

  public double nx, ny;

  public Cell c1, c2;

  public Border(BorderType type) {
    this.type = type;
  }
}
