package dotMatrix.Demo;

import java.util.ArrayList;

import dotMatrix.DotMatrix;
import dotMatrix.DotMatrixDisplay;
import dotMatrix.DotMatrixSerial;
import dotMatrix.Spark;
import processing.core.PApplet;

public class FlashDemo extends PApplet
{	
	private static final long serialVersionUID = -1596293397312990840L;
	
	private DotMatrix dm;
	private DotMatrixDisplay dmd;	
	private DotMatrixSerial sp;
	
	private int dotWidth = 20;
	private int margin = 10;
	private ArrayList<Spark> alSparks;
	
	public void setup() 
	{
		dm = new DotMatrix(48, 7);
		
		dmd = new DotMatrixDisplay(this, dm, dotWidth, margin);
		dmd.setColor(0xffff0000, 0xffffffff);
		
		sp = new DotMatrixSerial(this, "COM3", dm);
		
		alSparks = new ArrayList<Spark>();
		for(int i=0; i<6; i++)
			alSparks.add(new Spark(dm));		  
		
		dmd.display();
		sp.send();
	}

	public void draw()
	{	
		/*
		//if (frameCount % 10 != 0) return;
		
		dm.clear(false);
		
		for (Spark spark : alSparks)
		{
			spark.moveTo((int)random(dm.getColCount()), (int)random(dm.getRowCount()));
			spark.show();					
		}

		dmd.display();
		sp.send();
		*/
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
