package stud.mi.dachatserver.dto;

import java.util.Date;

import com.google.gson.Gson;

public class Message {

	public String _id = "message_id";
	private String _rev = null;
	private String datePosted;
	private String content = "";

	public Message(final String message, final Date date) {
		this.content = message;
		this.datePosted = date.toString();
		this._id = Integer.toString(message.hashCode());
	}

	public String getDatePosted() {
		return this.datePosted;
	}

	public String getMessage() {
		return this.content;
	}

	public String getRev() {
		return this._rev;
	}

	@Override
	public String toString() {
		return new Gson().toJson(this);
	}

}
