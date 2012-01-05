package dotMatrix;

import java.util.ArrayList;

import processing.core.PApplet;

public class SparkDemo extends PApplet
{
	private static final long serialVersionUID = -3966570181465468437L;

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
		
		Spark spark;
		for(int i=0; i<dm.getColCount(); i++)
		{
			spark = new Spark(dm);
			spark.moveTo(i,dm.getRowCount()-1);
			spark.Show();
			alSparks.add(spark);
		}
		
		dmd.display();
		sp.send();
		
		
	}
	
	public void draw()
	{
		dm.clear(false);
		
		for (Spark spark : alSparks)
		{
			if (spark.getRow() < 1+(int)random(dm.getRowCount()-1))
			{
				spark.moveTo(spark.getCol(), spark.getRow()+1);					
			}
			else
			{
				spark.moveTo(spark.getCol(), spark.getRow()-1);
			}
			
			for (int i=spark.getRow(); i< dm.getRowCount(); i++)
				dm.setDot(i, spark.getCol(), true);
			
			spark.Show();					
		}

		dmd.display();
		sp.send();
		
	}
}
