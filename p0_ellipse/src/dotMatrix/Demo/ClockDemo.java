package dotMatrix.Demo;

import java.util.ArrayList;

import dotMatrix.DotFont;
import dotMatrix.DotFont0706;
import dotMatrix.DotMatrix;
import dotMatrix.DotMatrixDisplay;
import dotMatrix.DotMatrixSerial;
import processing.core.PApplet;

public class ClockDemo extends PApplet
{
	private static final long serialVersionUID = -663453412567788630L;
	
	private DotMatrix dm;
	private DotMatrixDisplay dmd;	
	private DotMatrixSerial sp;
	
	private int dotWidth = 20;
	private int margin = 10;
	private ArrayList<DotFont0706> alFonts;		
	
	private ArrayList<DotFont> alColons;
	private int[] font={0x14};
		
	public void setup()
	{
		dm = new DotMatrix(48, 7);
		
		dmd = new DotMatrixDisplay(this, dm, dotWidth, margin);
		dmd.setColor(0xffff0000, 0xffffffff);
		
		sp = new DotMatrixSerial(this, "COM3", dm);
		
		alFonts = new ArrayList<DotFont0706>();
		alColons = new ArrayList<DotFont>();
		
		alColons.add(new DotFont(dm, font, 16, 0));
		alColons.add(new DotFont(dm, font, 30, 0));
	}
	
	public void draw()
	{
		alFonts.clear();
		
		alFonts.add(new DotFont0706(dm, hour() / 10, 4, 0));
		alFonts.add(new DotFont0706(dm, hour() % 10, 10, 0));
		
		alFonts.add(new DotFont0706(dm, minute() / 10, 18, 0));
		alFonts.add(new DotFont0706(dm, minute() % 10, 24, 0));
		
		alFonts.add(new DotFont0706(dm, second() / 10, 32, 0));
		alFonts.add(new DotFont0706(dm, second() % 10, 38, 0));		
		
		dm.clear(false);
		
		for (DotFont0706 font : alFonts)
		{
			font.show();
		}
		
		
		
		for (DotFont font : alColons)
		{
			font.show();
		}
		
		dmd.display();
		sp.send();
	}
}
