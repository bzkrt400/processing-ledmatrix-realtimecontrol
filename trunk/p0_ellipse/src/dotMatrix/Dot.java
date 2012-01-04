package dotMatrix;

public class Dot
{
	boolean _on;
	int _rowID;
	int _colID;
	
	public Dot(int row, int col)
	{
		_rowID = row;
		_colID = col;
	}
	
	public void turnOn()
	{
		_on = true;
	}
	
	public void turnOff()
	{
		_on = false;
	}
	
	public void set(boolean on)
	{
		_on = on;
	}
	
	public void reverse()
	{
		_on = !_on;
	}
	
}
