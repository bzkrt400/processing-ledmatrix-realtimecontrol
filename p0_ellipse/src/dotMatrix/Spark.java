package dotMatrix;

import processing.core.PVector;

public class Spark extends Dot
{
	private PVector _pv;
	private DotMatrix _dm;
	
	public Spark(DotMatrix dm)
	{
		this.turnOn();
		_dm = dm; 
		_pv = new PVector();
	}
	
	public void moveTo(int col, int row)
	{
		_pv.x = (float)col;
		_pv.y = (float)row;	
	}
	
	public void Show()
	{
		int col = (int) _pv.x;
		int row = (int) _pv.y;
		
		if (row <0 || row >= _dm.getRowCount())return;
		if (col <0 || col >= _dm.getColCount())return;
		
		_dm.setDot(row, col, this.isOn());		
	}	
	
	
	
}
