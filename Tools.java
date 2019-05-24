import java.util.Random;

public class Tools {

	public static Point getNextPoint(double x, double y, int rotation) {
		double radians = Math.PI*(rotation/180);
		
		double x1 = (x*Math.cos(radians)) + (y*Math.sin(y));
		double y1 = (y*Math.cos(radians)) - (x*Math.sin(y));
		
		return new Point(x1, y1);
	}
	
	
}
