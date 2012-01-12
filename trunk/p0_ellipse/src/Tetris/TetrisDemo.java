	package Tetris;

import dotMatrix.DotMatrix;
import dotMatrix.DotMatrixDisplay;
import dotMatrix.DotMatrixSerial;
import processing.core.PApplet;

public class TetrisDemo extends PApplet
{
	private static final long serialVersionUID = 4522547351037250574L;
	
	private DotMatrix dm;	
	private DotMatrixDisplay dmd;
	
	private DotMatrixSerial sp;
	
	private TetrisBlock tb;
	private TetrisStack ts;
	
	public void setup()
	{
		dm = new DotMatrix(48,7);		
		dmd = new DotMatrixDisplay(this, dm);
		
		sp = new DotMatrixSerial(this, "COM1", dm);
		
		tb = new TetrisBlock(dm, (int)random(TetrisBlock.getPatternCount()));
		ts = new TetrisStack(dm);
				
		tb.setIndex(1);
		tb.show();
		dmd.display();
	}
	
	public void keyPressed()
	{		
		TetrisDirection t = null;
		
		switch(key) 
		{	
			case CODED:					
				switch(keyCode)
				{
					case DOWN:
						t = TetrisDirection.DOWN;
						break;					
					case UP:
						t = TetrisDirection.UP;
						break;
					case LEFT:
						t = TetrisDirection.LEFT;
						break;
					case RIGHT:
						t = TetrisDirection.RIGHT;
						break;
					default:
						break;
				}
				break;
			case ' ':
				t = TetrisDirection.CLOCKWISE;
				break;
			default:
				break;
		}
		
		if (t != null)
			tb.change(t,ts);
		
		this.display();
	}
	
	public void draw()
	{			
		boolean b = tb.change(TetrisDirection.RIGHT, ts);
		delay(200);
		
		if (b)
		{			
			ts.merge(tb);
			tb = new TetrisBlock(dm, (int)random(TetrisBlock.getPatternCount()));
		}			
		
		this.display();		
	}
	
	private void display()
	{
		dm.clear(false);
		tb.show();
		ts.show();
		sp.send();
		dmd.display();
	}
}
