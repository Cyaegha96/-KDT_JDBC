package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class ServerMain {
	
	public static void main(String[] args){
		ServerSocket server = null;
		try {
			server = new ServerSocket(19329);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("클라이언트 연결을 기다리는 중입니다..");
		
		
		while (true) {
			Socket clientSocket = null;
			try {
				clientSocket = server.accept();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(clientSocket.getInetAddress() + "가 접속");
			new ServerThread(clientSocket).start();
		}
	}
}