package dotMatrix;

public class DotFontV extends SparkArray
{
	protected int[] _font;
	
	public DotFontV(DotMatrix dm, int[] font, int col, int row)
	{
		super(dm);
		_font = font;
		setSparks(col, row);
	}
	
	protected void setSparks(int col, int row)
	{
		_sparks.clear();	
		
		for (int c=0; c<_font.length; c++)
		{			
			for (int r=0; r<_dm.getRowCount(); r++)
			{
				if ((_font[c] & (0x01<<r)) == (0x01<<r))
				{
					Spark spark = new Spark(_dm);
					spark.moveTo(col + r, row + c);
					_sparks.add(spark);
				}
			}
		}		
	}
}
