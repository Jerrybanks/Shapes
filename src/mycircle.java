import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;

public class mycircle extends Shaper{
	mycircle(double a,double b,double c,double d,String e)
	{
		super(a,b,c,d,e);
	}
	
	public void drawme(Graphics g) {
		Graphics2D king=(Graphics2D)g;
		
		Shape s=new Ellipse2D.Double(x1, y1, x2, y2);
		
		king.draw(s);
	}
	
	public boolean isIn(double a,double b)
	{
		double x,y;
		x=x1+x2/2;
		y=y1+y2/2;
		
		x=Math.abs( Math.sqrt((x-a)*(x-a)+(y-b)*(y-b)) );
		y=x2/2;
		
		if ( y>=x )
			return true;
		else
			return false;
	}
}
