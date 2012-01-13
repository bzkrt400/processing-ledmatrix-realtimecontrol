package tetris;

import java.util.ArrayList;
import dotMatrix.DotMatrix;

public class TetrisScore
{
	ArrayList<TetrisDigital> _digitals;
	DotMatrix _dm;
	
	public TetrisScore(DotMatrix dm, int digiCount, int col, int row, int value)
	{
		_dm = dm;
		_digitals = new ArrayList<TetrisDigital>();
		
		for(int i=0; i<digiCount; i++)
		{
			int ten = 1;
			
			for (int j=0; j<i; j++)
				ten *= 10;			
			
			_digitals.add(new TetrisDigital(_dm, (value / ten) % 10 , col, row + 4*i));
		}		
	}
	
	public void show()
	{
		for (TetrisDigital digi : _digitals)
		{
			digi.show();
		}
	}
	
}
