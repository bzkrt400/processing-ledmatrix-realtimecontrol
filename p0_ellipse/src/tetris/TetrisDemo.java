package tetris;

import dotMatrix.DotMatrix;
import dotMatrix.DotMatrixDemo;
import processing.core.PApplet;

public class TetrisDemo extends PApplet
{
	private static final long serialVersionUID = 87901132768588420L;

	private static final int[] SCORE = {0, 1, 3, 6, 10};
	
	private DotMatrixDemo dmDemo;
	private DotMatrix _dm;
	
	private TetrisBlock tb;
	private TetrisBlock tbNext;
	private TetrisStack ts;	
	
	private TetrisScore tscore;
	private int score = 0;
		
	public void setup()
	{
		dmDemo = new DotMatrixDemo(this, 48, 7, "COM3");
		_dm = dmDemo.getDM();
		
		tscore = new TetrisScore(_dm, 2, 0, 0, score);
		
		tbNext = new TetrisBlock(_dm, (int)random(TetrisBlock.getPatternCount()), (int)random(4));		
		initBlocks();	
		
		ts = new TetrisStack(_dm);			
		
		this.display();
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
			tscore = new TetrisScore(_dm, 2, 0, 0, score);
			initBlocks();
		}			
		
		this.display();		
	}
	
	private void initBlocks()
	{
		tb = tbNext;
		tb.moveTo(10, 2);
		
		tbNext = new TetrisBlock(_dm, (int)random(TetrisBlock.getPatternCount()), (int)random(4));
		tbNext.moveTo(6, 2);		
	}
	
	private int calcScore(int rowsSweeped)
	{
		if (rowsSweeped < SCORE.length && rowsSweeped >= 0)
			return SCORE[rowsSweeped];
		else
			return 0;
	}
	
	protected void display()
	{
		_dm.clear(false);
		
		tbNext.show();
		tb.show();
		
		ts.show();		
		tscore.show();			
		
		dmDemo.display();
				
		delay(100);		
	}
}
