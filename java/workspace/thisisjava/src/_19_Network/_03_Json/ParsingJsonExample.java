package _19_Network._03_Json;

import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.charset.Charset;

import org.json.JSONObject;

public class ParsingJsonExample {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new FileReader("C:/Temp/member.json", Charset.forName("UTF-8")));
		String json = br.readLine();
		br.close();
		
		JSONObject root = new JSONObject(json);
		
		System.out.println("id: " + root.getString("id"));
		System.out.println("name: " + root.getString("name"));
		System.out.println("age: " + root.getInt("age"));
		System.out.println("student: " + root.getBoolean("student"));

	}

}
