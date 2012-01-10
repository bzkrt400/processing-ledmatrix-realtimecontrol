package Tetris;

import java.util.ArrayList;

import processing.core.PVector;

import dotMatrix.DotMatrix;
import dotMatrix.Spark;



public class TetrisBlock
{
	static int _pattern[][][] =
	{
		{{0,1},{1,1},{2,1},{3,1}},
		{{1,0},{1,1},{1,2},{1,3}},
	};
	
	private DotMatrix _dm;	
	private int _index = 0;
	private PVector _location;
	private ArrayList<Spark> _sparks;
	
	public TetrisBlock(DotMatrix dm)
	{
		_dm = dm;
		_sparks = new ArrayList<Spark>();
		_location = new PVector(0,0);
		setSparks();
	}
	
	public int countIndex()
	{
		return _pattern.length;
	}
	
	public void setIndex(int index)
	{
		_index = index;		
		setSparks();
	}
	
	private void setSparks()
	{
		_sparks.clear();
		
		for(int i=0; i<_pattern[_index].length; i++)
		{
			Spark spark = new Spark(_dm);
			spark.moveTo(locationCol() + _pattern[_index][i][0], locationRow() + _pattern[_index][i][1]);
			_sparks.add(spark);
		}		
	}
	
	public void show()
	{
		for (Spark spark : _sparks)
		{
			spark.show();
		}
	}
	
	private void tryChange(TetrisDirection t)
	{
		switch (t)
		{
			case UP:
				_location.y--;
				break;
			case DOWN:
				_location.y++;
				break;
			case LEFT:
				_location.x--;
				break;
			case RIGHT:
				_location.x++;
				break;
			case CLOCKWISE:
				_index ++;
				if (_index == _pattern.length) _index = 0;
				break;
			case ANTI_CLOCKWISE:
				_index --;
				if (_index == -1) _index = _pattern.length - 1;
				break;
			default:
				break;
		}				
		
		setSparks();
	}

	public void change(TetrisDirection direction)
	{				
		tryChange(direction);
		
		if (isOutOfBorder())
			tryChange(TetrisDirection.reverse(direction));		
	}
	
	private boolean isOutOfBorder()
	{
		boolean b = false;
		
		for (Spark spark : _sparks)
		{
			if (spark.getCol() <0 || spark.getCol() >= _dm.getColCount() ||
					spark.getRow() <0 || spark.getRow() >= _dm.getRowCount()) 
			{
				b = true;
				break;
			}
		}
		
		return b;
	}
	
	private int locationCol()
	{
		return (int)_location.x;
	}
	
	private int locationRow()
	{
		return (int)_location.y;
	}
}
