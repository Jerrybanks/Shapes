import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;

public class myrect extends Shaper{
	myrect(double a,double b,double c,double d,String e)
	{
		super(a,b,c,d,e);
	}
	
	public void drawme(Graphics g) {
		Graphics2D king=(Graphics2D)g;
		
		Shape s=new Rectangle2D.Double(x1, y1, x2, y2);
		
		king.draw(s);
	}

	public boolean isIn(double a,double b)
	{
		if ( a>x1 && (x1+x2)>a && b>y1 && (y1+y2)>b )
			return true;
		else
			return false;
	}
}
