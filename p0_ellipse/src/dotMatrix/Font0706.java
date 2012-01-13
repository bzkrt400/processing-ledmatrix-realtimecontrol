package dotMatrix;

public class Font0706 extends SparkArray
{
	private int[] _font;
	
	private final static int PATTERN_FONT[][] = 
	{
		{0x7f, 0x7f, 0x41, 0x7f, 0x7f},		//0
		{0x00, 0x00, 0x7f, 0x7f, 0x00},		//1
		{0x79, 0x79, 0x49, 0x4f, 0x4f},		//2		
		{0x49, 0x49, 0x49, 0x7f, 0x7f},		//3
		{0x3f, 0x3f, 0x20, 0x7f, 0x7f},		//4
		{0x4f, 0x4f, 0x49, 0x79, 0x79},		//5
		{0x7f, 0x7f, 0x49, 0x79, 0x79},		//6
		{0x01, 0x01, 0x01, 0x7f, 0x7f},		//7
		{0x7f, 0x7f, 0x49, 0x7f, 0x7f},		//8
		{0x4f, 0x4f, 0x49, 0x7f, 0x7f},		//9
	};
	
	public Font0706(DotMatrix dm, int num, int col, int row)
	{
		super(dm);
		_sparks.clear();
		_font = PATTERN_FONT[num];
		
		this.setSparks(col, row);
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

