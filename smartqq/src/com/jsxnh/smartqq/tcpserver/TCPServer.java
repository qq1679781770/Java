package com.jsxnh.smartqq.tcpserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TCPServer {

	private static final int SERVER_PORT=9001;
	private static final int MAX_THREAD=20;
	private ServerSocket serverSocket;
	private Socket socket;
	public static Map<Integer, ServerThread> getServerThreadMap() {
		return serverThreadMap;
	}

	private static Map<Integer,ServerThread> serverThreadMap = new HashMap<>();
	
	public TCPServer() throws IOException{
		serverSocket=new ServerSocket(SERVER_PORT);
		ExecutorService exec=Executors.newFixedThreadPool(MAX_THREAD);
		while(true){
			System.out.println("waiting ....");
			socket=serverSocket.accept();
			ServerThread serverThread=new ServerThread(socket);
			exec.execute(serverThread);
		}
	}

}
