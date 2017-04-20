package stud.mi.dachatserver.dto;

import com.google.gson.Gson;
import com.google.gson.JsonArray;

public class MessageList {

	private String _id = "message_list";
	private String _rev = null;

	public JsonArray messages = new JsonArray();

	public void addMessage(final Message msg) {
		msg._id = Integer.toString(messages.size());
		messages.add(new Gson().toJsonTree(msg));
	}

	public String getId() {
		return this._id;
	}

	public String getRev() {
		return this._rev;
	}

	public void removeRev() {
		this._rev = null;
	}
}
