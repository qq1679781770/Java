package com.jsxnh.smartqq.tcpserver;

import java.io.IOException;

public class Main {

	public static void main(String[] args) {
		try {
			new TCPServer();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
