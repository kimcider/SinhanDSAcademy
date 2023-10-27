package _19_Network._01;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server{
	public static ServerSocket serverSocket = null;
	public static void main(String[]args) {
		System.out.println("------------------------------");
		System.out.println("종료하ㅕ면Q");
		System.out.println("------------------------------");
		
		startServer();
		Scanner scanner = new Scanner(System.in);
		while(true) {
			String key = scanner.nextLine();
			if(key.toLowerCase().equals("q")) {
				break;
			}
		}
		scanner.close();
		stopServer();
	}
	
	static void startServer() {
		Thread thread = new Thread() {
			@Override
			public void run() {
				try {
					serverSocket = new ServerSocket(50001);
					System.out.println("서버시작");
					
					while(true) {
						System.out.println("서버연결요청대기");
						
						Socket socket = serverSocket.accept();
						
						InetSocketAddress isa = (InetSocketAddress) socket.getRemoteSocketAddress();
						System.out.println("[서버]"+ isa.getHostName() + "의 연결요청 수락");
						
						socket.close();
						System.out.println("[서버]"+isa.getHostName()+"의 연결을 끊음");
						
					}
				}catch(Exception e) {
					System.out.println("[서버]" + e.getMessage());
				}
			}
		};
		thread.start();
	}
	
	static void stopServer() {
		try {
			serverSocket.close();
			System.out.println("서버 종료됨");
		}catch(Exception e) {
			
		}
	}
}