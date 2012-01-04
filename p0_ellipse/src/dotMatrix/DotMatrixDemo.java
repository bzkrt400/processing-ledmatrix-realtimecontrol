package dotMatrix;

import processing.core.PApplet;

public class DotMatrixDemo extends PApplet
{	
	private static final long serialVersionUID = -1596293397312990840L;
	
	private DotMatrixDisplay dmd;	
	private DotMatrixSerial sp;

	private int dotWidth = 20;
	private int margin = 10;
	
	public void setup() 
	{
		this.background(0x33);
		dmd = new DotMatrixDisplay(this, 48, 7, dotWidth, margin);
		dmd.setColor(0xffff0000, 0xffffffff);
		sp = new DotMatrixSerial(this, "COM3");
		
		size(dmd.getWidth(),dmd.getHeight());	  
		
		dmd.dm.clear(false);
		dmd.display();
		
		sp.send(dmd.dm.output());		
	}

	public void draw()
	{	
		
		if (frameCount % 8 == 0)
		{
			dmd.dm.moveOnCol(true, false);
			
			int seed = (int)random(dmd.dm.getRowCount()*2);
			if (seed < dmd.dm.getRowCount())
				dmd.dm.reverseDot(seed, dmd.dm.getColCount()-1);		
			dmd.display();
		
			sp.send(dmd.dm.output());			
		}
	}
	
	public void mousePressed()
	{	
		if(mouseButton == LEFT)
		{
		
			int c = (mouseX - margin) / dmd.getDotDistance();
			int r = (mouseY - margin) / dmd.getDotDistance();		
			
			if (r < 0 || c <0 || r >= dmd.dm.getRowCount() || c >= dmd.dm.getColCount()) return;			
			
			dmd.dm.reverseDot(r, c);
		
		}
		else if(mouseButton == RIGHT)
		{
			dmd.dm.moveOnCol(true, true);
		}
		dmd.display();
		
		sp.send(dmd.dm.output());
	}	

}
