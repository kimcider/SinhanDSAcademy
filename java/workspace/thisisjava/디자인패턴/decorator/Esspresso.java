package decorator;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Esspresso implements Basic{
	private Basic basic;
	private int upShot;
	private int upPrice;

	@Override
	public int getShot() {
		// TODO Auto-generated method stub
		
		
		return basic.getShot() + upShot;
	}

	@Override
	public int getPrice() {
		// TODO Auto-generated method stub
		return basic.getPrice() + upPrice;
	}

}
