package strategy;

import java.net.URLEncoder;

public class Utf8Strategy implements EncodingStrategy{

	@Override
	public String encode(String text) {
		String result = "";
		try {
			result = URLEncoder.encode(text, "UTF-8");
		}catch(Exception e) {}
		
		return result;
	}

}
