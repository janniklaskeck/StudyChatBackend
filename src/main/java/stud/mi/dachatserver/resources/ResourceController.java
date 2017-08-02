package stud.mi.dachatserver.resources;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.JsonObject;

import stud.mi.dachatserver.dal.MessageDatabase;
import stud.mi.dachatserver.dto.MessageList;

@RestController
@RequestMapping("/history")
public class ResourceController
{

    @PostMapping("/channel/{channelName}")
    @ResponseStatus(HttpStatus.OK)
    public String addMessage(@RequestBody final String input, @PathVariable final String channelName)
    {
        final JsonObject jo = MessageDatabase.addChannelMessageToDB(channelName, input);
        return jo.toString();
    }

    @GetMapping("/channel/{channelName}")
    @ResponseStatus(HttpStatus.OK)
    public String getChannelMessages(@PathVariable final String channelName)
    {
        return MessageDatabase.getMessageFromDB(channelName, new MessageList().getId());
    }

    @PostMapping("/removeall/{channelName}")
    @ResponseStatus(HttpStatus.OK)
    public void removeDB(@PathVariable final String channelName)
    {
        MessageDatabase.removeDB(channelName);
    }

}
