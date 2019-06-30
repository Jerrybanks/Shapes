import java.util.*;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;


@SuppressWarnings("serial")
public class green
{
	
	JFrame frame;
	JButton inc,dec,winc,wdec,last,current,undo;
	JTextField t1,t2;
	JLabel l,l1,l2,l3;
	JMenuBar bmenu;
	JMenu smenu;
	JMenuItem rect,circle,line;
	
	green()
	{
		frame=new JFrame("Shaping World");
		frame.setLayout(null);
		frame.setBounds(250, 150, 800, 480);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setBackground(Color.GREEN);
		
//************************************************************************************
		
		class drawing extends JPanel implements KeyListener
		{

			Shaper cur;
			ArrayList <Shaper> mypack=new ArrayList<>();
			LinkedList <Shaper> gone=new LinkedList<>();
			
			drawing()
			{
				this.setBorder(BorderFactory.createLineBorder(Color.BLUE));
				this.setBounds(10, 44, 764, 306);
				this.setBackground(Color.CYAN);
				addMouseListener(new listen());
				addMouseMotionListener(new listen());
				addKeyListener(this);
				setFocusable(true);
				setFocusTraversalKeysEnabled(false);
			}
		
			protected void paintComponent(Graphics g)
			{
				super.paintComponent(g);
				if (cur!=null)
					cur.drawme(g);
				for(Shaper k:mypack) {
					k.drawme(g);
				}
			}
			
			class listen implements MouseListener,MouseMotionListener
			{
			
				public void mouseDragged(MouseEvent e) {
					double a,b;
					if (cur==null) {
						t1.setText("Current unknown");
					}
					else {
						t1.setText( cur.gettype()+" is moving" );
						if ( !cur.gettype().equals("Line") )
						{
							a=cur.getX2()/2;
							b=cur.getY2()/2;
							cur.setX1(e.getX()-a);
							cur.setY1(e.getY()-b);
						}
						else
						{
							a=Math.abs(cur.getX2()-cur.getX1())/2;
							cur.setX1(e.getX()-a);
							cur.setX2(e.getX()+a);
							cur.setY1(e.getY());
							cur.setY2(e.getY());
						}
						repaint();
					}
				}
		
				public void mouseMoved(MouseEvent e) {
					
				}
		
				public void mouseClicked(MouseEvent e) {
					if (cur!=null)
					mypack.add(cur);
					cur=null;
	
					for(int i=mypack.size()-1;i>=0;i--) {
						Shaper k=mypack.get(i);
						if ( k.isIn(e.getX(),e.getY()) )
						{
							setcur(k);
							t1.setText( cur.gettype()+" clicked" );
							break;
						}
					}
					if (cur==null)
						t1.setText("Current unknown");
					else
						repaint();
				}
		
				public void mouseEntered(MouseEvent e) {
					
				}
		
				public void mouseExited(MouseEvent e) {
					
				}
		
				public void mousePressed(MouseEvent e) {
					if (cur!=null)
						mypack.add(cur);
						cur=null;
		
						for(int i=mypack.size()-1;i>=0;i--) {
							Shaper k=mypack.get(i);
							if ( k.isIn(e.getX(),e.getY()) )
							{
								setcur(k);
								t1.setText( cur.gettype()+" clicked" );
								break;
							}
						}
						if (cur==null)
							t1.setText("Current unknown");
						else
							repaint();
				}
		
				public void mouseReleased(MouseEvent e) {
					if (cur!=null)
					{
						t1.setText( cur.gettype()+" stopped" );
						repaint();
					}
				}
				
			}
			
			public void addcur(Shaper c) {
				if (cur!=null)
					mypack.add(cur);
				this.cur=c;
				repaint();
			}
			
			public void setcur(Shaper c) {
				if (cur!=null)
					mypack.add(cur);
				this.cur=c;
				mypack.remove(c);
				repaint();
			}
			
			public void delc() {
				if (cur!=null) {
					int x=Integer.parseInt(t2.getText());
					x--;
					t2.setText( String.valueOf(x) );
					t1.setText( cur.gettype()+" removed" );
					gone.addLast(cur);
					this.cur=null;
					repaint();
				}
			}
			
			public void dell() {
				if (!mypack.isEmpty()) {
					int x=Integer.parseInt(t2.getText());
					x--;
					t2.setText( String.valueOf(x) );
					t1.setText( mypack.get( mypack.size()-1 ).gettype()+" removed" );
					gone.addLast( mypack.remove(mypack.size()-1) );
					repaint();
				}
			}
			
			public void addprev() {
				int x=Integer.parseInt(t2.getText());
				if (gone.size()!=0)
				{
					x++;
					t2.setText( String.valueOf(x) );
					if (cur!=null)
					mypack.add(cur);
					cur=gone.removeLast();
					repaint();
				}
			}
			
			public void incsize(int a)
			{
				if (cur!=null)
				{
					if ( cur.gettype().equals("Line") )
					{
						if (a==1) {
							cur.setX1(cur.getX1()-1);
							cur.setX2(cur.getX2()+2);
						}
						if (a==2&& (cur.getX2()>=2) ){
							cur.setX1(cur.getX1()+1);
							cur.setX2(cur.getX2()-2);
						}
						if (a==3)
							cur.setX2(cur.getX2()+1);
						if (a==4&& (cur.getX2()>=1) )
							cur.setX2(cur.getX2()-1);
					}
					if ( cur.gettype().equals("Rectangle") )
					{
						if (a==1) {
							cur.setY2(cur.getY2()+1);
						}
						if (a==2&& (cur.getY2()>=1) )
							cur.setY2(cur.getY2()-1);
						if (a==3)
							cur.setX2(cur.getX2()+1);
						if (a==4&& (cur.getX2()>=1) )
							cur.setX2(cur.getX2()-1);
					}
					if ( cur.gettype().equals("Circle") )
					{
						if (a==1) {
							cur.setY2(cur.getY2()+1);
							cur.setX2(cur.getX2()+1);
						}
						if (a==2&& (cur.getX2()>=1&&cur.getY2()>=1) ){
							cur.setY2(cur.getY2()-1);
							cur.setX2(cur.getX2()-1);
						}
					}
					repaint();
				}
			}

			
			public void keyPressed(KeyEvent e) {
				System.out.println("hello");
					
			}

			public void keyReleased(KeyEvent arg0) {
			}

			public void keyTyped(KeyEvent arg0) {				
			}
			
		}
		
//************************************************************************************************
		
		drawing p1=new drawing();
		frame.getContentPane().add(p1);
		
		l1 = new JLabel("Status -");
		l1.setFont(new Font("Tahoma", Font.BOLD, 14));
		l1.setBounds(10, 12, 72, 21);
		l2 = new JLabel("Shapes Count -");
		l2.setFont(new Font("Tahoma", Font.BOLD, 14));
		l2.setBounds(10, 376, 130, 21);
		l = new JLabel("Welcome");
		l.setForeground(Color.RED);
		l.setFont(new Font("Times New Roman", Font.BOLD, 23));
		l.setBounds(312, 12, 126, 21);
		l3= new JLabel("Delete -");
		l3.setFont(new Font("Tahoma", Font.BOLD, 14));
		l3.setBounds(609, 353, 72, 21);
		frame.getContentPane().add(l3);
		frame.getContentPane().add(l1);
		frame.getContentPane().add(l2);
		frame.getContentPane().add(l);
		
		//***************************************
		
		bmenu=new JMenuBar();
		frame.setJMenuBar(bmenu);
		
		smenu = new JMenu("Shapes");
		bmenu.add(smenu);
		
		rect = new JMenuItem("Rectangle");
		smenu.add(rect);
		
		line = new JMenuItem("Line");
		smenu.add(line);
		
		circle = new JMenuItem("Circle");
		smenu.add(circle);
		
		//****************************************
		
		t1 = new JTextField();
		t1.setBounds(84, 11, 137, 26);
		frame.getContentPane().add(t1);
		t1.setText("Start");
		t2 = new JTextField();
		t2.setColumns(10);
		t2.setBounds(130, 374, 80, 26);
		t2.setHorizontalAlignment(JTextField.CENTER);
		frame.getContentPane().add(t2);
		t2.setText("0");
		t1.setEditable(false);
		t2.setEditable(false);
		
		inc = new JButton("Inc");
		inc.setBounds(347, 353, 56, 26);
		frame.getContentPane().add(inc);
		
		dec = new JButton("Dec");
		dec.setBounds(347, 385, 56, 26);
		frame.getContentPane().add(dec);
		
		winc = new JButton("W-Inc");
		winc.setBounds(410, 361, 70, 39);
		frame.getContentPane().add(winc);
		
		wdec = new JButton("W-Dec");
		wdec.setBounds(268, 361, 72, 39);
		frame.getContentPane().add(wdec);
		
		last = new JButton("Last");
		last.setBounds(558, 380, 72, 23);
		frame.getContentPane().add(last);
		
		current = new JButton("Current");
		current.setBounds(659, 380, 80, 23);
		frame.getContentPane().add(current);
		
		undo = new JButton("Undo");
		undo.setBounds(659, 11, 80, 23);
		frame.getContentPane().add(undo);

		frame.setVisible(true);
	
		
			class click implements ActionListener 
			{
				Shaper x;
			
				public void actionPerformed(ActionEvent e) 
				{
					if (e.getSource()==inc)
					{
						p1.incsize(1);
					}
					if (e.getSource()==winc)
					{
						p1.incsize(3);
					}
					if (e.getSource()==dec)
					{
						p1.incsize(2);
					}
					if (e.getSource()==wdec)
					{
						p1.incsize(4);
					}
					if (e.getSource()==current)
					{
						p1.delc();
					}
					if (e.getSource()==last)
					{
						p1.dell();
					}
					if (e.getSource()==rect)
					{
						x=new myrect(10,10,20,20,"Rectangle");
						p1.addcur(x);
						t1.setText("Rectangle added");
					}
					if (e.getSource()==circle)
					{
						x=new mycircle(50,10,20,20,"Circle");
						p1.addcur(x);
						t1.setText("Circle added");
					}
					if (e.getSource()==line)
					{
						x=new myline(10,50,30,50,"Line");
						p1.addcur(x);
						t1.setText("Line added");
					}
					if ( e.getSource()==rect || e.getSource()==line ||  e.getSource()==circle )
					{
						int y=Integer.parseInt(t2.getText());
						y++;
						t2.setText( String.valueOf(y) );
					}
					if ( e.getSource()==undo )
					{
						p1.addprev();
					}
				}
			}
			
			click btn=new click();
			inc.addActionListener(btn);
			winc.addActionListener(btn);
			dec.addActionListener(btn);
			wdec.addActionListener(btn);
			rect.addActionListener(btn);
			circle.addActionListener(btn);
			line.addActionListener(btn);
			last.addActionListener(btn);
			current.addActionListener(btn);
			undo.addActionListener(btn);
			
	}

}




