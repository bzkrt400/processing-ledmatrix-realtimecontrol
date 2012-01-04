package dotMatrix;

//import com.sun.xml.internal.ws.util.xml.CDATA;


import processing.core.PApplet;

public class DotMatrixDemo extends PApplet
{	
	private static final long serialVersionUID = -1596293397312990840L;
	
	private DotMatrixDisplay dmd;	
	//private DotMatrixSerial sp;

	private int dotWidth = 20;
	private int margin = 10;
	
	public void setup() 
	{
	
		dmd = new DotMatrixDisplay(this, 48, 8, dotWidth, margin);
		dmd.setColor(0xffff0000, 0xffffffff);
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
