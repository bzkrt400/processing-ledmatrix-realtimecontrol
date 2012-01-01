package p0ellipse;

import processing.core.PApplet;

public class DotMatrixDisplay extends PApplet
{
	private int _DotWidth = 20;
	private int _DotSpan = 2;
	private int _DotDistance;
	
	private int _Margin = 10;
	
	private int _ColorOn = 0xffff0000;
	private int _ColorOff = 0xffffffff;
	
	private int _Width, _Height;
	
	public int Width()
	{
		return _Width;		
	}
	
	public int Height()
	{
		return _Height;
	}
	
	public DotMatrixDisplay(DotMatrix dm)
	{
		_DotDistance  = _DotWidth + _DotSpan;
		_Width = _DotDistance * dm.ColCount() + _Margin * 2;
		_Height = _DotDistance * dm.RowCount() + _Margin * 2;
	}
	
	public void Update(DotMatrix dm)
	{
		byte[] pScreen = dm.Output();
		
		for(int i=0; i< pScreen.length; i++)
		{
			int r=i/_BytePerRow;
			int c=i%_BytePerRow;
			
			for(int j=0;j<8;j++)
			{			
				int j1 = 7-j;
				if((_pScreen[i] & (0x01<<j1)) == (0x01<<j1))
				{
					fill(_ColorOn);
				}
				else
				{
					fill(_ColorOff);
				}
				rect(_Margin + 8*c*_DotDistance + j * _DotDistance, _Margin+r*_DotDistance, _DotWidth, _DotWidth);
			}
		}
	}
	
	
}
