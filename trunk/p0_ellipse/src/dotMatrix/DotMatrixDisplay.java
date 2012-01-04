package dotMatrix;

import processing.core.PApplet;

public class DotMatrixDisplay
{
	private PApplet _parent;	
	
	private int _dotWidth = 20;
	private int _dotSpan = 2;
	private int _dotDistance;
	
	private int _margin = 10;
	
	private int _colorOn = 0xffff0000;
	private int _colorOff = 0xffffffff;
	
	private int _width, _height;
	
	public DotMatrix dm;
	
	public int getWidth()
	{
		return _width;		
	}
	
	public int getHeight()
	{
		return _height;
	}
	
	public int getDotDistance()
	{
		return _dotDistance;
	}
	
	private void setStyle()
	{
		_dotDistance  = _dotWidth + _dotSpan;
		_width = _dotDistance * dm.getColCount() + _margin * 2;
		_height = _dotDistance * dm.getRowCount() + _margin * 2;		
	}
	
	public DotMatrixDisplay(PApplet p,int colCount, int rowCount)
	{
		dm = new DotMatrix(colCount, rowCount);
		_parent = p;
		setStyle();
	}
	
	public DotMatrixDisplay(PApplet p,int colCount, int rowCount, int dotWidth, int margin)
	{
		this(p, colCount, rowCount);
		_dotWidth = dotWidth;
		_margin = margin;
		setStyle();
	}
	
	public void setColor(int colorOn, int colorOff)
	{
		_colorOn = colorOn;
		_colorOff = colorOff;
	}	
	
	public void display()
	{
		_parent.stroke(0);
		for(int r=0; r< dm.getRowCount(); r++)
		{	
			for(int c=0; c<dm.getColCount();c++)
			{			
				
				if(dm.getDot(r,c))
				{
					_parent.fill(_colorOn);
				}
				else
				{
					_parent.fill(_colorOff);
				}
				
				_parent.pushMatrix();
				_parent.translate(_margin + c * _dotDistance, _margin + r * _dotDistance);
				_parent.rect(0, 0 , _dotWidth, _dotWidth);
				_parent.popMatrix();
			}
		}
	}
	
	
}
