package stud.mi.dachatserver.resources;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.JsonObject;

import stud.mi.dachatserver.dal.MessageDatabase;
import stud.mi.dachatserver.dto.MessageList;

@RestController
@RequestMapping("/")
public class ResourceController {

	@PostMapping("/message")
	@ResponseStatus(HttpStatus.OK)
	public String addMessage(@RequestBody String input) {
		final JsonObject jo = MessageDatabase.addMessageToDB(input);
		return jo.toString();
	}

	@GetMapping("/message")
	@ResponseStatus(HttpStatus.OK)
	public String getMessages() {
		return MessageDatabase.getMessageFromDB(new MessageList().getId());
	}

	@PostMapping("/removeall")
	@ResponseStatus(HttpStatus.OK)
	public void removeDB() {
		MessageDatabase.removeDB();
	}

}
