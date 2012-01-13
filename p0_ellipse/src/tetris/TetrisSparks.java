package tetris;

import java.util.ArrayList;

import dotMatrix.DotMatrix;
import dotMatrix.Spark;

public class TetrisSparks
{
	protected DotMatrix _dm;
	protected ArrayList<Spark> _sparks;
	protected int[][] _pattern;
	
	public TetrisSparks(DotMatrix dm)
	{
		_dm = dm;
		_sparks = new ArrayList<Spark>();
	}
	
	public void show()
	{
		for (Spark spark : _sparks)
		{
			spark.show();
		}
	}
	
	protected void setSparks(int col, int row)
	{
		_sparks.clear();
		
		for(int i=0; i<_pattern.length; i++)
		{
			Spark spark = new Spark(_dm);
			spark.moveTo(col + _pattern[i][0], row + _pattern[i][1]);
			_sparks.add(spark);
		}		
	}	
	
	
}
