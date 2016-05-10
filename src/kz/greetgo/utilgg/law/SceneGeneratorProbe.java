package kz.greetgo.utilgg.law;

import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;

public class SceneGeneratorProbe {
  public static void main(String[] args) throws Exception {
    SceneGenerator sg = new SceneGenerator();

    sg.dx = 5;
    sg.dy = 5;

    sg.Nx1 = 4;
    sg.Nx2 = 4;
    sg.Nx3 = 7;

    sg.Ny1 = 5;
    sg.Ny2 = 8;
    sg.Ny3 = 4;

    sg.generate();

    BufferedImage im = new BufferedImage(800, 600, BufferedImage.TYPE_INT_ARGB);
    {
      Graphics2D g = im.createGraphics();
      g.setColor(new Color(240, 240, 240));
      g.fillRect(0, 0, im.getWidth(), im.getHeight());

      Painter painter = new Painter();
      painter.deltaX = 10;
      painter.deltaY = 10;
      painter.scale = 5;

      for (Cell cell : sg.scene.cellList) {
        painter.paintCell(g, cell, im.getWidth(), im.getHeight());
      }

      for (Border b : sg.scene.borderList) {
        painter.paintBorder(g, b, im.getWidth(), im.getHeight());
      }

      g.dispose();
    }

    ImageIO.write(im, "png", new File("/home/pompei/tmp/asd.png"));

    System.out.println("OK");
  }
}
