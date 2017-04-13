package stud.mi.dachatserver.chatserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import stud.mi.dachatserver.dto.Session;

public class Server {

	private static final Logger LOGGER = LoggerFactory.getLogger(Server.class);

	private int port;
	private ServerSocket serverSocket;
	private List<Socket> clientSockets = new ArrayList<>();
	private boolean running = true;

	public Server(final int port) {
		this.port = port;
	}

	public void start() {
		try {
			serverSocket = new ServerSocket(port);
			LOGGER.info("Server Started On Port {}", port);
		} catch (IOException e) {
			LOGGER.error("Could not start Server!", e);
		}
		while (running) {
			try {
				Socket clientSocket = serverSocket.accept();
				new EchoThread(clientSocket);
				LOGGER.info("New Client Connected from Port {}", clientSocket.getPort());
			} catch (IOException e) {
				LOGGER.error("Could not accept connection!", e);
			}

		}
	}

	public boolean stop() {
		try {
			serverSocket.close();
		} catch (IOException e) {
			return false;
		}
		return true;
	}

	public Session getSession() {

		return new Session(0L);
	}

}
