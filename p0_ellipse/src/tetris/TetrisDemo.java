package tetris;

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
	private TetrisBlock tbNext;
	private TetrisStack ts;	
	
	private TetrisScore tscore;
	private int score;
	
	public void setup()
	{
		dm = new DotMatrix(48,7);		
		dmd = new DotMatrixDisplay(this, dm);
		
		sp = new DotMatrixSerial(this, "COM3", dm);
		
		score =0;
		tscore = new TetrisScore(dm, 2, 0, 0, score);
		
		tbNext = new TetrisBlock(dm, (int)random(TetrisBlock.getPatternCount()), (int)random(4));
		
		initBlocks();	
		
		ts = new TetrisStack(dm);			
			
		dmd.display();
		
		
		//noLoop();
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
						//t = TetrisDirection.DOWN;
						t = TetrisDirection.RIGHT;
						break;					
					case UP:
						//t = TetrisDirection.UP;
						t = TetrisDirection.CLOCKWISE;
						break;
					case LEFT:
						//t = TetrisDirection.LEFT;
						t = TetrisDirection.DOWN;
						break;
					case RIGHT:
						//t = TetrisDirection.RIGHT;
						t = TetrisDirection.UP;
						break;
					default:
						break;
				}
				break;
			case ' ':
				t = TetrisDirection.ANTI_CLOCKWISE;
				break;
			default:
				break;
		}
		
		if (t != null)
			tb.change(t,ts);
	}
	
	public void draw()
	{			
		boolean b = tb.change(TetrisDirection.RIGHT, ts);	
		
		if (b)
		{				
			score += calcScore(ts.merge(tb));
			tscore = new TetrisScore(dm, 2, 0, 0, score);
			initBlocks();
		}			
		
		this.display();		
	}
	
	private void initBlocks()
	{
		tb = tbNext;
		tb.moveTo(10, 2);
		
		tbNext = new TetrisBlock(dm, (int)random(TetrisBlock.getPatternCount()), (int)random(4));
		tbNext.moveTo(6, 2);		
	}
	
	private int calcScore(int rowsSweeped)
	{
		int points=0;
		
		switch(rowsSweeped)
		{
			case 1:
				points = 1;
				break;
			case 2:
				points = 3;
				break;
			case 3:
				points = 6;
				break;
			case 4:
				points = 10;
				break;
			default:
				points = 0;
				break;
		}
		
		return points;
		
	}
	
	private void display()
	{
		dm.clear(false);
		
		tbNext.show();
		tb.show();
		
		ts.show();		
		tscore.show();		
		
		sp.send();
		
		dmd.display();
				
		delay(100);		
	}
}
