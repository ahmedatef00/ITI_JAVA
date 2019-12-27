package arrows;

import java.applet.Applet;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.HeadlessException;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Arrows extends Applet {

	public int x = 100, y = 100;
	
	public Arrows() throws HeadlessException {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void init() {
		setSize(400, 400);
		this.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				
			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			        x -= 7;
			    }
			    if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			        x += 7;
			    }
			    if (e.getKeyCode() == KeyEvent.VK_UP) {
			        y -= 7;
			    }
			    if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			         y += 7;
			    }
			    
			    if(e.getKeyCode()==KeyEvent.VK_RIGHT && x>=400) {
			    	x=7;
//System.out.println("get width");
			    }
			    if(e.getKeyCode()==KeyEvent.VK_LEFT && x<7) {
			    	x=400;
//System.out.println("get width");
			    }
			    if(e.getKeyCode()==KeyEvent.VK_UP && y<7) {
			    	y=400;
System.out.println(getHeight());
			    }
			    if(e.getKeyCode()==KeyEvent.VK_DOWN && y>=400) {
			    	y=7;
//System.out.println("get width");
			    }
			    
			    
			    
			    
			    repaint();
				
			}
			
		});
	}

	
	@Override
	public void paint(Graphics g) {
		g.setFont(new Font(getName(), Font.BOLD, 20));
		g.drawString("java", x, y);
	}

}
