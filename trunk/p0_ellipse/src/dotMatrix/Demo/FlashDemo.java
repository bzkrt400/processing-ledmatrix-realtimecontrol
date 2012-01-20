package dotMatrix.Demo;

import java.util.ArrayList;

import dotMatrix.DotMatrix;
import dotMatrix.DotMatrixDemo;
import dotMatrix.DotMatrixDisplay;
import dotMatrix.Spark;
import processing.core.PApplet;

public class FlashDemo extends PApplet
{	
	private static final long serialVersionUID = -1596293397312990840L;
	
	private DotMatrix _dm;
	private DotMatrixDemo dmDemo;
	private DotMatrixDisplay _dmd;
	
	private ArrayList<Spark> alSparks;
	
	private int dotDistance = 18;
	private int dotWidth = 16;
	private int margin = 10;
	
	
	public void setup() 
	{
		dmDemo = new DotMatrixDemo(this, 72, 7, "COM3");
		dmDemo.SetDisplayStyle(dotWidth, margin);
		_dm = dmDemo.getDM();
		_dmd = dmDemo.getDotMatrixDisplay();
				
		alSparks = new ArrayList<Spark>();
		for(int i=0; i<6; i++)
			alSparks.add(new Spark(_dm));		  
		
		dmDemo.display();
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
			int index =_dmd.getDotIndex(mouseX, mouseY); 
			if (index != -1)
			{				
				_dm.reverseDot(index);
			}
		}
		
		dmDemo.display();
	}	

}
