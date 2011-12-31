package p0ellipse;

//import com.sun.xml.internal.ws.util.xml.CDATA;


import processing.core.PApplet;
import processing.serial.Serial;

public class P0Ellipse extends PApplet
{	
	private static final long serialVersionUID = -1596293397312990840L;
	
	byte _pScreen[] = new byte[42];
	
	int _DotWidth = 20;
	int _DotSpan = 2;
	int _DotDistance = _DotWidth + _DotSpan;
	
	int _CountRow = 7;
	
	int _BytePerRow = _pScreen.length / _CountRow;
	int _CountCol = _BytePerRow * 8;
	
	int _Margin = 10;
	
	int _ColorOn = 0xffff0000;
	int _ColorOff = 0xffffffff;
	
	Serial sp;
	
	
	
	public void setup() 
	{
		int iWidth = _DotDistance * _CountCol + _Margin * 2;
		int iHeight = _DotDistance * _CountRow + _Margin * 2;
		
		size(iWidth, iHeight);
		
		sp = new Serial(this, "COM3", 115200);  
	  
		_pScreen[0] = 0x01;
		_pScreen[20] = 0x20;
		
		DrawMatrix();	
		
	}
	
	private void DrawMatrix()
	{
		for(int i=0; i<_pScreen.length; i++)
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

	public void draw()
	{	
	
		
	}
	
	public void mousePressed()
	{
		int c = (mouseX - _Margin) / _DotDistance;
		int r = (mouseY - _Margin) / _DotDistance;
		
		if (c<0 || c>=_CountCol) return;
		if (r<0 || r>=_CountRow) return;
		
		int i = _BytePerRow*r + c/8;
		int j=c%8;
		int j1 = 7-j;
		
		if((_pScreen[i] & (0x01<<j1)) == (0x01<<j1))
		{
			_pScreen[i] &= ~(0x01<<j1);
		}
		else
		{
			_pScreen[i] |= (0x01<<j1);
		}
		
		DrawMatrix();
		Send();
	}
	
	private void Send()
	{
		sp.write(0xf3);
		sp.write(_pScreen);
		
	}

}
