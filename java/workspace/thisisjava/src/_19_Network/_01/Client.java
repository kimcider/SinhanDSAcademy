package _19_Network._01;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
	public static void main(String[]args) {
		try {
			Socket socket = new Socket("192.168.0.122", 50001);
			System.out.println("클라연결성공");
			
			socket.close();
			System.out.println("연결끊음");
		}catch(UnknownHostException e) {
			System.out.println(e);
		}catch(IOException e) {
			System.out.println(e);
		}
	}
}
