package drawline;

import java.applet.Applet;

import java.awt.HeadlessException;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Vector;
import java.awt.Font;
import java.awt.Graphics;

public class Drawline extends Applet {

Vector<Line> v = new Vector<>();
class Line{
public int xbegin,xend;
public int ybegin,yend;}
Line line;
public Drawline() throws HeadlessException {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void init() {
		setSize(400, 400);
		
		this.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
line =new Line();
				line .xbegin=e.getX();
line.ybegin=e.getY();
line.xend=e.getX();
line.yend=e.getY();



			}
			
			@Override
			public void mouseReleased(MouseEvent e) {
				v.add(line);
				repaint();

			}
		});
		
	
		this.addMouseMotionListener(new MouseMotionListener() {
			
			@Override
			public void mouseMoved(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseDragged(MouseEvent e) {
				line.xend = e.getX();//get x coordinates
				line.yend =e.getY();//get y coordinates
				
				
				repaint();
			}
		});
	}

	
	public void paint(Graphics g) {
		for (int i = 0; i < v.size(); i++) {
			g.drawLine(v.get(i).xbegin,v.get(i).ybegin,v.get(i).xend,v.get(i).yend);
		}
		
	};
	
	
}
