package Tetris;

import java.util.ArrayList;

import processing.core.PVector;

import dotMatrix.DotMatrix;
import dotMatrix.Spark;
public class TetrisBlock
{
	private final static int _patternAll[][][][] =
	{
		// l
		{
			{{0,1},{1,1},{2,1},{3,1}},
			{{1,0},{1,1},{1,2},{1,3}},
		},
		
		// O
		{
			{{0,0}, {1,0}, {1,1}, {0,1}},
			//{{0,0}, {1,0}, {1,1}, {0,1}},
		},
		
		// T
		{
			{{0,1}, {1,1}, {2,1}, {1,0}},
			{{1,0}, {1,1}, {1,2}, {2,1}},
			{{0,1}, {1,1}, {2,1}, {1,2}},
			{{1,0}, {1,1}, {1,2}, {0,1}},
		},
		
		// J
		{
			{{0,1}, {1,1}, {2,1}, {2,2}},
			{{1,0}, {1,1}, {1,2}, {0,2}},
			{{0,1}, {1,1}, {2,1}, {0,0}},
			{{1,0}, {1,1}, {1,2}, {2,0}},
		},
		
		// L
		{
			{{0,1}, {1,1}, {2,1}, {2,0}},
			{{1,0}, {1,1}, {1,2}, {2,2}},
			{{0,1}, {1,1}, {2,1}, {0,2}},
			{{1,0}, {1,1}, {1,2}, {0,0}},
		},
		
		// Z
		{
			{{0,2}, {0,1}, {1,1}, {1,0}},
			{{0,1}, {1,1}, {1,2}, {2,2}},
		},
		
		// S
		{
			{{0,1}, {1,1}, {1,0}, {2,0}},
			{{0,0}, {0,1}, {1,1}, {1,2}},
		},
		
	};
	
	private DotMatrix _dm;	
	private int _index;
	private PVector _location;
	private ArrayList<Spark> _sparks;
	
	private int[][][] _pattern;
	
	public TetrisBlock(DotMatrix dm, int pattern, int index)
	{
		_dm = dm;
		_sparks = new ArrayList<Spark>();
		_location = new PVector(2,2);
		_pattern = _patternAll[pattern];
		_index = index % _pattern.length;		
		setSparks();
	}
	
	public void moveTo(int col, int row)
	{
		_location = new PVector(col, row);
		setSparks();
	}
	
	public ArrayList<Spark> getSparks()
	{
		return _sparks;
	}
	
	public int countIndex()
	{
		return _pattern.length;
	}
	
	
	
	public void show()
	{
		for (Spark spark : _sparks)
		{
			spark.show();
		}
	}
	
	public static int getPatternCount()
	{
		return _patternAll.length;
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

	public boolean change(TetrisDirection direction, TetrisStack ts)
	{	
		tryChange(direction);
		
		boolean b = isOutOfBorder() || isContacted(ts);
		if (b)
		{
			tryChange(TetrisDirection.reverse(direction));			
		}
		
		return b;
	}

	public boolean isContacted(TetrisStack ts)
	{	
		boolean b = false;
		
		for (Spark sparkStack : ts.getSparks())
		{
			for (Spark spark : _sparks)
			{
				if (sparkStack.getID() == spark.getID())
				{
					b = true;
					break;
				}	
			}			
		}
		return b;
	}

	public boolean isOutOfBorder()
	{
		boolean b = false;
		
		for (Spark spark : _sparks)
		{
			if (	spark.getCol() <0 || spark.getCol() >= _dm.getColCount() ||
					spark.getRow() <0 || spark.getRow() >= _dm.getRowCount()) 
			{
				b = true;
				break;
			}
		}
		
		return b;
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

	private int locationCol()
	{
		return (int)_location.x;
	}
	
	private int locationRow()
	{
		return (int)_location.y;
	}
	
	
}
