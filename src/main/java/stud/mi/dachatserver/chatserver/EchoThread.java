package stud.mi.dachatserver.chatserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class EchoThread {

	public EchoThread(final Socket clientSocket) {
		if (clientSocket != null) {
			new Thread(() -> {
				try {
					PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
					BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
					String inputLine;
					while ((inputLine = in.readLine()) != null) {
						out.println(inputLine);
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}).start();
		} else {
			System.err.println("No Socket");
		}
	}

}
