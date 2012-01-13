package tetris;

import java.util.ArrayList;

import dotMatrix.DotMatrix;
import dotMatrix.Spark;

public class TetrisStack
{
	private ArrayList<Spark> _sparks;
	private DotMatrix _dm;
	private ArrayList<ArrayList<Spark>> _sparksPerCol;
	
	public TetrisStack(DotMatrix dm)
	{		
		_dm = dm;
		_sparks = new ArrayList<Spark>();		
		_sparksPerCol = new ArrayList<ArrayList<Spark>>();
		
		for(int i=0; i<_dm.getColCount(); i++)
			_sparksPerCol.add(i, new ArrayList<Spark>());
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
	
	public int merge(TetrisBlock tb)
	{		
		_sparks.addAll(tb.getSparks());		
		
		return sweep();
	}
	
	private void countSparkPerCol()
	{
		for (ArrayList<Spark> sparksPerCol : _sparksPerCol)
		{
			sparksPerCol.clear();
		}			
		
		for (Spark spark : _sparks)
		{
			_sparksPerCol.get(spark.getCol()).add(spark);
		}
	}
	
	private int sweep()
	{
		int score = 0;
		
		countSparkPerCol();
		
		for(int i=0; i<_dm.getColCount(); i++)
		{
			if(_sparksPerCol.get(i).size() == _dm.getRowCount())
			{
				score ++;
				_sparks.removeAll(_sparksPerCol.get(i));
				
				for(int j=0; j<i; j++)
				{
					for (Spark spark : _sparksPerCol.get(j))
					{
						spark.moveTo(spark.getCol()+1, spark.getRow());
					}
				}
			}			
		}
		
		return score;
	}
}