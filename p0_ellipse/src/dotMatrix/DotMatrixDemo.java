package dotMatrix;


import processing.core.PApplet;

public class DotMatrixDemo extends PApplet
{	
	private static final long serialVersionUID = -1596293397312990840L;
	
	private DotMatrix dm;
	private DotMatrixDisplay dmd;	
	private DotMatrixSerial sp;
	private Spark spark;

	private int dotWidth = 20;
	private int margin = 10;
	
	public void setup() 
	{
		this.background(0x33);
		
		dm = new DotMatrix(48, 7);
		
		dmd = new DotMatrixDisplay(this, dm, dotWidth, margin);
		dmd.setColor(0xffff0000, 0xffffffff);
		
		sp = new DotMatrixSerial(this, "COM1", dm);
		spark = new Spark(dm);
		
		dm.clear(false);
		spark.Show();
		
		
		dmd.display();
		
		sp.send();		
	}

	public void draw()
	{	
		if (frameCount % 10 != 0) return;
		
		dm.clear(false);
		spark.moveTo((int)random(dm.getColCount()), (int)random(dm.getRowCount()));
		spark.Show();
		
		dmd.display();
	}
	
	public void mousePressed()
	{	
		if(mouseButton == LEFT)
		{
			int c = (mouseX - margin) / dmd.getDotDistance();
			int r = (mouseY - margin) / dmd.getDotDistance();		
			
			if (r < 0 || c <0 || r >= dm.getRowCount() || c >= dm.getColCount()) return;			
			
			dm.reverseDot(r, c);		
		}
		
		dmd.display();		
		sp.send();
	}	

}
