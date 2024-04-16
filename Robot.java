public class Robot {
      private String name;
  private int x;
  private int y;

  public Robot(String name, int x, int y) {
    this.name = name;
    this.x = x;
    this.y = y;
  }

  public void move(int dx, int dy) {
    x += dx;
    y += dy;
  }

  public String getName() {
    return name;
  }

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }

  public String toString() {
    return name + " is at (" + x + "," + y + ")";
  }
}
