package tetris;

import dotMatrix.DotMatrix;
import dotMatrix.SparkArray;

public class TetrisDigital extends SparkArray
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
	
	private int _col, _row;	
	private int _num;	
	
	public TetrisDigital(DotMatrix dm, int num, int col, int row)
	{
		super(dm);
		_num = num;
		super._pattern = _patternAll[_num];
		
		_col = col;
		_row = row;
		_num = num % _patternAll.length;
		
		super.setSparks(_col, _row);
	}	
	
	
		
}
