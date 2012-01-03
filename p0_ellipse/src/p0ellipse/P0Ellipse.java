package p0ellipse;

//import com.sun.xml.internal.ws.util.xml.CDATA;


import processing.core.PApplet;

public class P0Ellipse extends PApplet
{	
	private static final long serialVersionUID = -1596293397312990840L;
	
	private DotMatrixDisplay dmd;	
	private DotMatrixSerial sp;

	private int dotWidth = 20;
	private int margin = 10;
	
	public void setup() 
	{
	
		dmd = new DotMatrixDisplay(this, 48, 7, dotWidth, margin);
		dmd.setColor(0xff0000ff, 0xffffffff);
		//sp = new DotMatrixSerial(this, "COM1");
		
		size(dmd.getWidth(),dmd.getHeight());	  
		
		dmd.dm.clear(false);
		dmd.display();
		
		//sp.send(dmd.dm.output());	
		
	}

	public void draw()
	{	
			
	}
	
	public void mousePressed()
	{
		
		int c = (mouseX - margin) / dmd.getDotDistance();
		int r = (mouseY - margin) / dmd.getDotDistance();		
		
		if (r < 0 || c <0 || r >= dmd.dm.getRowCount() || c >= dmd.dm.getColCount()) return;
			
		
		dmd.dm.reverseDot(r, c);
		
		dmd.display();
	
		//sp.send(dmd.dm.output());
	}
	
	

}
