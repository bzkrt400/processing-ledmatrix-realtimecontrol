package Tetris;

import java.util.ArrayList;

import dotMatrix.DotMatrix;
import dotMatrix.Spark;

public class TetrisStack
{
	private ArrayList<Spark> _sparks;
	private DotMatrix _dm;
	
	public TetrisStack(DotMatrix dm)
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
	
	public ArrayList<Spark> getSparks()
	{
		return _sparks;
	}
	
	public void merge(TetrisBlock tb)
	{		
		_sparks.addAll(tb.getSparks());				
	}
}
