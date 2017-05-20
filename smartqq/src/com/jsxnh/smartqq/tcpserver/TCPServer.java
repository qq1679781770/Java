package com.jsxnh.smartqq.tcpserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TCPServer {

	private static final int SERVER_PORT=9001;
	private static final int MAX_THREAD=10;
	private ServerSocket serverSocket;
	private Socket socket;
	private static List<ServerThread> serverThreads=new LinkedList<ServerThread>();
	
	public TCPServer() throws IOException{
		serverSocket=new ServerSocket(SERVER_PORT);
		ExecutorService exec=Executors.newFixedThreadPool(MAX_THREAD);
		while(true){
			System.out.println("waiting ....");
			socket=serverSocket.accept();
			
			ServerThread serverThread=new ServerThread(socket);
			serverThreads.add(serverThread);
			exec.execute(serverThread);
		}
	}
	
	public static List<ServerThread> getserverThreads(){
		return serverThreads;
	}
}
