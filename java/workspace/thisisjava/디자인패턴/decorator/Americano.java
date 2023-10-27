package decorator;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class Americano implements Basic{
	private int shot;
	private int price;
	
	@Override
	public int getShot() {
		
		return shot;
	}

	@Override
	public int getPrice() {
		// TODO Auto-generated method stub
		return price;
	}

}
