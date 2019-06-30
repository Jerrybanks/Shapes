import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Line2D;

public class myline extends Shaper{
	myline(double a,double b,double c,double d,String e)
	{
		super(a,b,c,d,e);
	}
	
	public void drawme(Graphics g) {
		Graphics2D king=(Graphics2D)g;
		
		Shape s=new Line2D.Double(x1, y1, x2, y2);
		
		king.draw(s);
	}
	
	public boolean isIn(double a,double b)
	{
		if ( a>=x1 && a<=(x1+x2) && ( b==y1||b==(y1+1)||b==(y1-1)) )
			return true;
		else
			return false;
	}
}
