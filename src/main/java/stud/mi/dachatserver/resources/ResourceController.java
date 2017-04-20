package stud.mi.dachatserver.resources;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import stud.mi.dachatserver.dal.MessageDatabase;

@RestController
@RequestMapping("/")
public class ResourceController {

	@PostMapping("/message")
	@ResponseStatus(HttpStatus.OK)
	public String addMessage(@RequestBody String input) {
		MessageDatabase.addMessageToDB(input);
		return input;
	}

	@GetMapping("/message")
	@ResponseStatus(HttpStatus.OK)
	public String getMessages() {
		return MessageDatabase.getMessageFromDB("message_id");
	}

}
