package assignment;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

/**
 * Simple class that encapsulates the drawing of Bézier curves of arbitrary order using
 * de Casteljau's algorithm.
 */
public class Bezier {
	private ArrayList<Point> control_Points;
  /**
   * Constructs an instance of a Bézier curve with given {@code control_Points}.
   * 
   * @param control_Points to construct the Bézier curve, the first and last points are interpolated
   *          and all points in between are approximated
   * 
   */
   public Bezier(List<Point> controlPoints) {
	   
    // TODO: implement this constructor and the rest of this class necessary for the assignment
	   
	   // Since list is an interface, to use the elements of the list iteratively; I changed the type of control
	   // points as ArrayList 
		   control_Points  = new ArrayList<Point>(controlPoints);
	   
  }

  /**
   * Draw the control polygon and the curve with a fixed number of line {@code segments}.
   * 
   * @param context used for drawing
   * @param segments of the curve
   */
  public void draw(Graphics2D context, int segments) {
	  fillContext(context, control_Points);
	  Point p1, p2;
	  p1 = control_Points.get(0);
	  for (float t=0; t <= 1; t += ((float) 1 / segments)) {
	    p2 = DeCasteljau(control_Points.size()-1, 0, t);
	    context.drawLine(Math.round(p1.getX()),Math.round(p1.getY()), Math.round(p2.getX()), Math.round(p2.getY())); //round x and y 
	    p1=p2;
	  }

  
  }
  //implementation of the DeCasteljau algorithm to calculate b^n_i as a general function
  public Point DeCasteljau(int n, int i, float t) {
	  if (n == 0){
		  return control_Points.get(i);
	  }
		  Point p1 = DeCasteljau(n-1,i,t);
		  Point p2 = DeCasteljau(n-1,i+1,t);
		  return new Point((1-t)*p1.getX() + t*p2.getX(), (1-t)*p1.getY() + t*p2.getY()); 
	  
	}
  // this methods implements visualization parts of the assignment
  
  private void fillContext(Graphics2D context,List<Point> control_Points){
	  // interpolated points of the control polygon as yellow circles
	  context.setColor(Color.yellow);
	  context.fillOval(Math.round(control_Points.get(0).getX()-5), Math.round(control_Points.get(0).getY()-5), 10, 10);
	  context.fillOval(Math.round(control_Points.get(control_Points.size()-1).getX()-5),Math.round(control_Points.get(control_Points.size()-1).getY()-5) , 10, 10);
	  // the approximated points as green circles
	  context.setColor(Color.green);
	  for(int i=1; i< control_Points.size()-1;i++ ){
		  context.fillOval(Math.round(control_Points.get(i).getX()-5), Math.round(control_Points.get(i).getY()-5), 10, 10);
	    }
	  //control polygon with solid magenta colored lines
	  context.setColor(Color.magenta);
	  for(int i=0; i<control_Points.size()-1 ;i++){
		  context.drawLine(Math.round(control_Points.get(i).getX()),Math.round(control_Points.get(i).getY()),Math.round(control_Points.get(i+1).getX()),Math.round(control_Points.get(i+1).getY()));
	  }
	  // B�zier curve as a white solid line
	  context.setColor(Color.white);
	  
  }
  
  /**
   * Draw the control polygon and the curve with an optimal amount of line segments.
   * 
   * @param context to use for drawing
   */
  public void draw(Graphics2D context) {
    // TODO: implement this method and the rest of this class necessary for the assignment
	  fillContext(context, control_Points);
	  Point p1 = control_Points.get(0);
	  Point p2;
	  //distance parameter between two points
	  int segments = 0; // 
	  float d = (float) 0.1; //initial distance value for while loop
	  float a = 0; // min distance
	  float b = 50000; // max distance
	while( d > 0.00001 && segments < 250){
		float t = (float) 1/ segments;
		p2 = DeCasteljau(control_Points.size()-1,0,t);
		//Euclodian distance between two points
		a = (float)Math.sqrt(Math.pow((p1.getX()-p2.getX()),2) + Math.pow((p1.getY()-p2.getY()),2));
		d = b-a;
		p1 = p2;
		segments ++;
	}
	draw(context,segments);
	  }
	  
	  
  }


