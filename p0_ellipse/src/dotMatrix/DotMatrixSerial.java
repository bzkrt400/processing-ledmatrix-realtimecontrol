package dotMatrix;

import processing.core.PApplet;
import processing.serial.Serial;

public class DotMatrixSerial
{
	private Serial _sp;
	private DotMatrix _dm;
	
	public DotMatrixSerial(PApplet p, String PortName, DotMatrix dm)
	{
		_sp = new Serial(p, PortName, 9600);
		_dm = dm;
	}
	
	public void send()
	{
		_sp.write(0xf3);
		_sp.write(_dm.output());		
	}
}
