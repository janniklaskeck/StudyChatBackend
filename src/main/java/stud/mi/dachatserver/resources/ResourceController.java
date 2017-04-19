package stud.mi.dachatserver.resources;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class ResourceController {

	private static String tempMessages = "";

	@PostMapping("/message")
	@ResponseStatus(HttpStatus.OK)
	public String addMessage(@RequestBody String input) {
		addMessageToDB(input);
		return input;
	}

	private static void addMessageToDB(final String message) {
		tempMessages += message + System.lineSeparator();
	}

	@GetMapping("/messages")
	@ResponseStatus(HttpStatus.OK)
	public String getMessages() {
		return tempMessages;
	}
}
