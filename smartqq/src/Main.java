
import com.jsxnh.smartqq.tcpserver.TCPServer;

import java.io.IOException;

public class Main {

	public static void main(String[] args) {
		try {
			System.out.println(System.getProperty("file.encoding"));
			System.out.println("h..............................");
			System.getProperties().list(System.out);
			new TCPServer();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
