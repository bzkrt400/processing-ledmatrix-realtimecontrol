package Tetris;

import java.util.ArrayList;

import dotMatrix.DotMatrix;
import dotMatrix.Spark;

public class TetrisDigital
{
	private final static int _patternAll[][][] =
	{
		//0
		{
			{0,0}, {1,0}, {2,0}, {3,0}, {4,0}, 
			{0,1}, {4,1},
			{0,2}, {1,2}, {2,2}, {3,2}, {4,2},
		},
		
		//1
		{
			{0,1}, {1,1}, {2,1}, {3,1}, {4,1},			
		}, 
		
		//2
		{
			{0,0}, {1,0}, {2,0}, {4,0}, 
			{0,1}, {2,1}, {4,1},
			{0,2}, {2,2}, {3,2}, {4,2},
		},
		
		//3
		{
			{0,0}, {1,0}, {2,0}, {3,0}, {4,0}, 
			{0,1}, {2,1}, {4,1},
			{0,2}, {2,2}, {4,2},
		},		 
		
		//4
		{
			{0,0}, {1,0}, {2,0}, {3,0}, {4,0}, 
			{3,1},
			{0,2}, {1,2}, {2,2}, {3,2},
		},
		
		//5
		{
			{0,0}, {2,0}, {3,0}, {4,0}, 
			{0,1}, {2,1}, {4,1},
			{0,2}, {1,2}, {2,2}, {4,2},
		},  
		
		//6
		{
			{0,0}, {2,0}, {3,0}, {4,0}, 
			{0,1}, {2,1}, {4,1},
			{0,2}, {1,2}, {2,2}, {3,2}, {4,2},
		},  	
		//7
		{
			{0,0}, {1,0}, {2,0}, {3,0}, {4,0}, 
			{0,1}, 
			{0,2}, 
		},  		
		//8
		{
			{0,0}, {1,0}, {2,0}, {3,0}, {4,0}, 
			{0,1}, {2,1}, {4,1},
			{0,2}, {1,2}, {2,2}, {3,2}, {4,2},
		},  
		//9
		{
			{0,0}, {1,0}, {2,0}, {3,0}, {4,0}, 
			{0,1}, {2,1}, {4,1},
			{0,2}, {1,2}, {2,2}, {4,2},
		},  		
	};
	
	private DotMatrix _dm;
	private int _col, _row;
	private ArrayList<Spark> _sparks;
	private int _num;
	private int[][] _pattern;
	
	public TetrisDigital(DotMatrix dm, int num, int col, int row)
	{
		_dm = dm;
		_sparks = new ArrayList<Spark>();
		_col = col;
		_row = row;
		_num = num % _patternAll.length;
		_pattern = _patternAll[_num];
		setSparks();
	}
	
	private void setSparks()
	{
		_sparks.clear();
		
		for(int i=0; i<_pattern.length; i++)
		{
			Spark spark = new Spark(_dm);
			spark.moveTo(_col + _pattern[i][0], _row + _pattern[i][1]);
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
	
}
