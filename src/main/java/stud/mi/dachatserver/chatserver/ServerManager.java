package stud.mi.dachatserver.chatserver;

import java.util.HashMap;
import java.util.Map;

import stud.mi.dachatserver.dto.Session;

public class ServerManager {

	private static Map<Integer, Server> servers = new HashMap<>();
	private static ServerManager instance;

	private ServerManager() {
	}

	public static synchronized ServerManager getInstance() {
		if (instance == null) {
			instance = new ServerManager();
		}
		return instance;
	}

	public Session createServer(final int port) {
		final Server server = new Server(port);
		servers.put(port, server);
		return server.getSession();
	}

	public Session stopServer(final long sessionNumber) {
		return null;
	}
}
