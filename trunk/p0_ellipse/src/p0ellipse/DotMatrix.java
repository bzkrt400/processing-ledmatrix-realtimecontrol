package p0ellipse;


public class DotMatrix
{
	private int _ColCount;
	private int _RowCount;
	private int _DotCount;
	
	private boolean _dots[];
	
	public int ColCount()
	{
		return _ColCount;		
	}
	
	public int RowCount()
	{
		return _RowCount;		
	}
	
	
	public DotMatrix(int ColCount, int RowCount)
	{
		_ColCount = ColCount;
		_RowCount = RowCount;
		_DotCount = _ColCount * _RowCount;
		_dots = new boolean[_DotCount];
	}
	
	public void Clear(boolean b)
	{		
		for (int i=0; i<_dots.length;i++)
		{
			_dots[i] = b;
		}		
	}
	
	public byte[] Output()
	{
		byte[] pScreen = new byte[_DotCount / 8];
		
		for(int i=0; i<_dots.length; i++)
		{
			if (_dots[i])
				pScreen[i/8] |= 0x01 << (i%8);			
		}
		
		return pScreen;
	}
	
	
}
