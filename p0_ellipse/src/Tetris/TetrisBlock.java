package Tetris;

import java.util.ArrayList;

import processing.core.PVector;
import sun.java2d.pipe.SpanClipRenderer;

import dotMatrix.DotMatrix;
import dotMatrix.Spark;



public class TetrisBlock
{
	static int _pattern[][][] =
	{
		{{0,1},{1,1},{2,1}},
		{{1,0},{1,1},{1,2}},
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
			spark.moveTo(_pattern[_index][i][0], _pattern[_index][i][1]);
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
	
	
	public int turn()
	{
		_index ++;
		if (_index == _pattern.length) _index = 0;
		
		setSparks();
		
		return _index;
	}
	
}
