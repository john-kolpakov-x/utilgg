package kz.greetgo.utilgg.law;

public class SceneGenerator {
  public double dx, dy;
  public F Vy1, Vy2;
  public int Nx1, Nx2, Nx3;
  public int Ny1, Ny2, Ny3;

  public Scene scene;

  public Cell C1[][], C2[][], C3[][];

  public void generate() throws Exception {
    scene = new Scene();

    prepareCell("C1", 0, 0, Nx1, Ny1);
    prepareCell("C2", Nx1 * dx, 0, Nx2, Ny1 + Ny2);
    prepareCell("C3", (Nx1 + Nx2) * dx, (Ny1 + Ny2 - Ny3) * dy, Nx3, Ny3);

    prepareInnerBorder(C1, Nx1, Ny1, 0, 0);
    prepareInnerBorder(C2, Nx2, Ny1 + Ny2, Nx1 * dx, 0);
    prepareInnerBorder(C3, Nx3, Ny3, (Nx1 + Nx2) * dx, (Ny1 + Ny2 - Ny3) * dy);

    prepareBorder_OA();
    prepareBorder_AB();
    prepareBorder_BC();
    prepareBorder_CD();

    prepareBorder_OG();
    prepareBorder_GF();
    prepareBorder_FE();
    prepareBorder_ED();

    prepareBorder_BB1();
    prepareBorder_FF1();
  }


  private void prepareCell(String fieldName, double x0, double y0, int Nx, int Ny) throws Exception {
    Cell C[][] = new Cell[Nx][];
    for (int i = 0; i < Nx; i++) {
      C[i] = new Cell[Ny];
      for (int j = 0; j < Ny; j++) {
        Cell c = new Cell();
        C[i][j] = c;
        scene.cellList.add(c);

        c.x = x0 + dx * i;
        c.y = y0 + dy * j;
        c.width = dx;
        c.height = dy;
      }
    }

    getClass().getField(fieldName).set(this, C);
  }

  private void prepareBorder_OA() {
    for (int j = 0; j < Ny1; j++) {
      Border b = new Border(BorderType.OUTER);
      scene.borderList.add(b);

      b.x1 = 0;
      b.y1 = j * dy;
      b.x2 = 0;
      b.y2 = b.y1 + dy;
      b.nx = 1;
      b.ny = 0;
      b.c2 = C1[0][j];
    }
  }

  private void prepareBorder_AB() {
    for (int i = 0; i < Nx1; i++) {
      Border b = new Border(BorderType.WALL);
      scene.borderList.add(b);

      b.x1 = i * dx;
      b.y1 = Ny1 * dy;
      b.x2 = b.x1 + dx;
      b.y2 = b.y1;
      b.nx = 0;
      b.ny = -1;
      b.c2 = C1[i][Ny1 - 1];
    }
  }

  private void prepareBorder_BC() {
    for (int j = 0; j < Ny2; j++) {
      Border b = new Border(BorderType.WALL);
      scene.borderList.add(b);

      b.x1 = Nx1 * dx;
      b.y1 = (Ny1 + j) * dy;
      b.x2 = b.x1;
      b.y2 = b.y1 + dy;
      b.nx = 1;
      b.ny = 0;
      b.c2 = C2[0][Ny1 + j];
    }
  }

  private void prepareBorder_CD() {
    for (int i = 0; i < Nx2; i++) {
      Border b = new Border(BorderType.WALL);
      scene.borderList.add(b);

      b.x1 = (Nx1 + i) * dx;
      b.y1 = (Ny1 + Ny2) * dy;
      b.x2 = b.x1 + dx;
      b.y2 = b.y1;
      b.nx = 0;
      b.ny = -1;
      b.c2 = C2[i][Ny1 + Ny2 - 1];
    }

    for (int i = 0; i < Nx3; i++) {
      Border b = new Border(BorderType.WALL);
      scene.borderList.add(b);

      b.x1 = (Nx1 + Nx2 + i) * dx;
      b.y1 = (Ny1 + Ny2) * dy;
      b.x2 = b.x1 + dx;
      b.y2 = b.y1;
      b.nx = 0;
      b.ny = -1;
      b.c2 = C3[i][Ny3 - 1];
    }
  }

  private void prepareBorder_OG() {
    for (int i = 0; i < Nx1; i++) {
      Border b = new Border(BorderType.WALL);
      scene.borderList.add(b);

      b.x1 = i * dx;
      b.y1 = 0;
      b.x2 = b.x1 + dx;
      b.y2 = 0;
      b.nx = 0;
      b.ny = 1;
      b.c2 = C1[i][0];
    }

    for (int i = 0; i < Nx2; i++) {
      Border b = new Border(BorderType.WALL);
      scene.borderList.add(b);

      b.x1 = (Nx1 + i) * dx;
      b.y1 = 0;
      b.x2 = b.x1 + dx;
      b.y2 = 0;
      b.nx = 0;
      b.ny = 1;
      b.c2 = C2[i][0];
    }
  }

  private void prepareBorder_GF() {
    for (int j = 0; j < Ny1 + Ny2 - Ny3; j++) {
      Border b = new Border(BorderType.WALL);
      scene.borderList.add(b);

      b.x1 = (Nx1 + Nx2) * dx;
      b.y1 = j * dy;
      b.x2 = b.x1;
      b.y2 = b.y1 + dy;
      b.nx = -1;
      b.ny = 0;
      b.c2 = C2[Nx2 - 1][j];
    }
  }

  private void prepareBorder_FE() {
    for (int i = 0; i < Nx3; i++) {
      Border b = new Border(BorderType.WALL);
      scene.borderList.add(b);

      b.x1 = (Nx1 + Nx2 + i) * dx;
      b.y1 = (Ny1 + Ny2 - Ny3) * dy;
      b.x2 = b.x1 + dx;
      b.y2 = b.y1;
      b.nx = 0;
      b.ny = 1;
      b.c2 = C3[i][0];
    }
  }

  private void prepareBorder_ED() {
    for (int j = 0; j < Ny3; j++) {
      Border b = new Border(BorderType.OUTER);
      scene.borderList.add(b);

      b.x1 = (Nx1 + Nx2 + Nx3) * dx;
      b.y1 = (Ny1 + Ny2 - Ny3 + j) * dy;
      b.x2 = b.x1;
      b.y2 = b.y1 + dy;
      b.nx = 1;
      b.ny = 0;
      b.c1 = C3[Nx3 - 1][j];
    }
  }

  private void prepareInnerBorder(Cell[][] C, int Nx, int Ny, double x0, double y0) {
    for (int j = 1; j < Ny; j++) {
      for (int i = 0; i < Nx; i++) {

        Border b = new Border(BorderType.INNER);
        scene.borderList.add(b);

        b.x1 = x0 + dx * i;
        b.y1 = y0 + dy * j;
        b.x2 = b.x1 + dx;
        b.y2 = b.y1;
        b.nx = 0;
        b.ny = 1;

        b.c2 = C[i][j];
        b.c1 = C[i][j - 1];

      }
    }

    for (int i = 1; i < Nx; i++) {
      for (int j = 0; j < Ny; j++) {

        Border b = new Border(BorderType.INNER);
        scene.borderList.add(b);

        b.x1 = x0 + dx * i;
        b.y1 = y0 + dy * j;
        b.x2 = b.x1;
        b.y2 = b.y1 + dy;
        b.nx = 1;
        b.ny = 0;

        b.c1 = C[i - 1][j];
        b.c2 = C[i][j];

      }
    }

  }

  private void prepareBorder_BB1() {
    for (int j = 0; j < Ny1; j++) {

      Border b = new Border(BorderType.INNER);
      scene.borderList.add(b);

      b.x1 = Nx1 * dx;
      b.y1 = dy * j;
      b.x2 = b.x1;
      b.y2 = b.y1 + dy;
      b.nx = 1;
      b.ny = 0;

      b.c1 = C1[Nx1 - 1][j];
      b.c2 = C2[0][j];

      b.c1.title = "-" + j;
      b.c2.title = "+" + j;
    }
  }

  private void prepareBorder_FF1() {
    for (int j = 0; j < Ny3; j++) {
      Border b = new Border(BorderType.INNER);
      scene.borderList.add(b);

      b.x1 = (Nx1 + Nx2) * dx;
      b.y1 = (Ny1 + Ny2 - Ny3 + j) * dy;
      b.x2 = b.x1;
      b.y2 = b.y1 + dy;
      b.nx = 1;
      b.ny = 0;

      b.c1 = C2[Nx2 - 1][Ny1 + Ny2 - Ny3 + j];
      b.c2 = C3[0][j];

      b.c1.title = "-" + j;
      b.c2.title = "+" + j;
    }
  }
}
