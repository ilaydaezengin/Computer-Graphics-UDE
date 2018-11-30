package submission.IlaydaZengin;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import submission.IlaydaZengin.Point;

/**
 * Simple class that encapsulates the drawing of Bézier curves of 

arbitrary order using
 * de Casteljau's algorithm.
 */
public class Bezier {

  /**
   * Constructs an instance of a Bézier curve with given {@code 

controlPoints}.
   * 
   * @param controlPoints to construct the Bézier curve, the first 

and last points are interpolated
   *          and all points in between are approximated
   */
	List<Point> controlPoints;
	
  public Bezier(List<Point> controlPoints) {
   
  this.controlPoints = controlPoints;
  }

  /**
   * Draw the control polygon and the curve with a fixed number of 

line {@code segments}.
   * 
   * @param context used for drawing
   * @param segments of the curve
   */
  public void draw(Graphics2D context, int segments) {
  
	  fillContext(context);
      drawCurve(context,segments);
  }

  /**
   * Draw the control polygon and the curve with an optimal amount 

of line segments.
   * 
   * @param context to use for drawing
   */
  public void draw(Graphics2D context) {
    
	  fillContext(context);
      drawCurve(context);
     
  }
  
  public void fillContext(Graphics2D context){
	  
//control polygon with solid magenta colored lines
	  
	  context.setColor(Color.magenta);
	  for(int i=0; i<controlPoints.size()-1 ;i++){
		  Point a3 =controlPoints.get(i);
		  Point a4 = controlPoints.get(i+1);		  
		  context.drawLine(Math.round(a3.getX()),Math.round(a3.getY()),
				  			Math.round(a4.getX()),Math.round(a4.getY()));
	  }
	  
	// first and last point are yellow according to assignment
	  
	Point a = controlPoints.get(0);
	Point b = controlPoints.get(controlPoints.size()-1);
	  context.setColor(Color.yellow);
	  context.fillOval(Math.round(a.getX()-5), Math.round(a.getY()-5), 10, 10);
	  context.fillOval(Math.round(b.getX()-5), Math.round(b.getY()-5) , 10, 10);
	  
	  // the approximated points as green circles
	  context.setColor(Color.green);
	  for(int i=1; i< controlPoints.size()-1;i++ ){
		  Point a2 = controlPoints.get(i);
		  context.fillOval(Math.round(a2.getX()-5), Math.round(a2.getY()-5), 10, 10);
	    }
	  	  // Bézier curve as a white solid line
	  context.setColor(Color.white);
	  
  }
  
  private void drawCurve(Graphics2D context,int segments) {
	  List<Point> list = new ArrayList<Point>();
	     
      list.add(controlPoints.get(0));
      for(int i = 1; i < segments; i++) {
          float t = (float)i/(float)(segments);
          list.add(segment_point(controlPoints,t));
      }
      
      list.add(controlPoints.get(controlPoints.size()-1));
      for(int i = 0; i < list.size()-1;i++) {
          Point a = list.get(i);
          Point b = list.get(i+1);
          context.drawLine( (int)a.getX(), (int)a.getY(),
                  (int)b.getX(),(int) b.getY());
}
}

  private void drawCurve(Graphics2D context) {
	  Point start = controlPoints.get(0);
      Point end = controlPoints.get(controlPoints.size()-1);
      split(context,start,0.0f,end,1.0f);
}
  
  public Point segment_point(List<Point> controlPoints, float t){
	 List<Point> input = controlPoints;
	 
     while(input.size() > 1) {
    	input = subList(input, t);
     }
	return input.get(0);
}



public List<Point> subList(List<Point> input, float t){
    List<Point> subList = new ArrayList<Point>();
   
    for(int i = 0; i < input.size()-1;i++) {
        Point a = input.get(i);
        Point b = input.get(i+1);
        Point c = a.linear_inter(b, t);
        subList.add(c);
    }
    return subList;
}

public void split(Graphics2D context, Point a, float ta,Point c, float tc){
	  float tb = 0.5f * ta + 0.5f * tc;
	  Point b = segment_point(controlPoints, tb);

	  	if(a.dist(c) > 2 || a.dist(b) + b.dist(c) > 2) {
	  		split(context, a, ta, b, tb);
	  		split(context, b, tb, c, tc);
	  	}else {
	  		context.setColor(Color.WHITE);
	  		context.drawLine(Math.round(a.getX()),Math.round(a.getY()),
	  				Math.round(b.getX()),Math.round(b.getY()));
}
}
  
}