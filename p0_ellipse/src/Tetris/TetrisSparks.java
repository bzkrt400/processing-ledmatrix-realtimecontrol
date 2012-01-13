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
	
	
}
