package exam;

public class Bmi {
	public static void main(String[] args) {
		double height = 180;
		double weight = 80;
		
		System.out.println(bmi(height, weight));
	}
	
	static String bmi(double height, double weight) {
		double bmi = weight / ((height / 100) * (height / 100)); 
		
		if(bmi > 25) return "비만";
		else if( bmi > 18.5) return "표준";
		else return "저체중";
	}
}
