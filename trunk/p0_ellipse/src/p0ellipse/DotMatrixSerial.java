package p0ellipse;

import processing.core.PApplet;
import processing.serial.Serial;

public class DotMatrixSerial
{
	private Serial _sp;

	
	public DotMatrixSerial(PApplet p, String PortName)
	{

		_sp = new Serial(p, PortName, 9600);
	}
	
	public void send(byte[] pByte)
	{
		_sp.write(0xf3);
		_sp.write(pByte);		
	}
}
