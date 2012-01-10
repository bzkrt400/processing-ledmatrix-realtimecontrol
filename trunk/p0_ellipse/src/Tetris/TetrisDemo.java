package Tetris;

import dotMatrix.DotMatrix;
import dotMatrix.DotMatrixDisplay;
import processing.core.PApplet;

public class TetrisDemo extends PApplet
{
	private static final long serialVersionUID = 4522547351037250574L;
	
	private DotMatrix dm;
	private TetrisBlock tb;
	private DotMatrixDisplay dmd;
	
	public void setup()
	{
		dm = new DotMatrix(48,7);
		tb = new TetrisBlock(dm);
		dmd = new DotMatrixDisplay(this, dm);	
		
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
			tb.change(t);
		
		dm.clear(false);
		//tb.turn();
		tb.show();
		dmd.display();
	}
	
	public void draw()
	{
		
	}
}
