package submission.IlaydaZengin;

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

  public void setX(float x) {
	this.x = x;
}

public void setY(float y) {
	this.y = y;
}

public float getX(){
	 return x;
	 }
  public float getY(){
	 return y;
	 }
  
  //linear interpolation with given point and t value
  public Point linear_inter(Point a, float t){
	  Point c = new Point(0, 0);
	  c.setX(((1 - t) * this.getX()) + (t * a.getX()));
	  c.setY(((1 - t) * this.getY()) + (t * a.getY()));
	  return c;
	  
  }

public double dist(Point b) {
	double a1 = Math.pow(this.getX(), 2) + Math.pow(b.getX(), 2);
	double a2 = Math.pow(this.getY(),2) + Math.pow(b.getY(), 2);
	return Math.sqrt(a1 + a2);
}
  }

