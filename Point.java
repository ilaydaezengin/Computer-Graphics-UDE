package assignment;

/**
 * Simple two dimensional point class that encapsulates its x and y components.
 */
public class Point {
	private float x;
	private float y;
  /**
   * Constructs an instance of a point with the given {@code x} and {@code y} components.
   * 
   * @param x component of a point
   * @param y component of a point
   */
  public Point(float x, float y) {
	  this.x = x;
	  this.y = y;
    // TODO: implement this constructor and the rest of this class necessary for the assignment
  }

  public float getX(){
	 return x;
	 }
  public float getY(){
	 return y;
	 }
  }