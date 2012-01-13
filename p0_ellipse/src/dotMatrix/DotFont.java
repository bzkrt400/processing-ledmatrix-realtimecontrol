package dotMatrix;

public class DotFont extends SparkArray
{
	protected int[] _font;
	
	public DotFont(DotMatrix dm)
	{
		super(dm);
	}
	
	public DotFont(DotMatrix dm, int[] font, int col, int row)
	{
		this(dm);
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
					spark.moveTo(col + c, row + r);
					_sparks.add(spark);
				}
			}
		}		
	}
}
