package stud.mi.dachatserver.dto;

import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

public class MessageList
{

    private static final JsonParser PARSER = new JsonParser();

    private String _id = "message_list";
    private String _rev = null;

    private final JsonArray messages = new JsonArray();

    public void addMessage(final Message msg)
    {
        msg.setID(Integer.toString(this.messages.size()));
        this.messages.add(PARSER.parse(msg.toJson()));
    }

    public String getId()
    {
        return this._id;
    }

    public String getRev()
    {
        return this._rev;
    }

    public void removeRev()
    {
        this._rev = null;
    }
}
