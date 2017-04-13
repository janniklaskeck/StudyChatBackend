package stud.mi.dachatserver.resources;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import stud.mi.dachatserver.chatserver.ServerManager;
import stud.mi.dachatserver.dto.Session;

@RestController
@RequestMapping("/")
public class ResourceController {

	@GetMapping("/session")
	public Session createSession() {
		return ServerManager.getInstance().createServer(1234);
	}

	@PostMapping("/session")
	public Session freeSession() {
		return ServerManager.getInstance().stopServer(1234);
	}

	@GetMapping("/")
	public String documentation() {
		return "<html><a href='/session'>Click to get Session</a>";
	}
}
