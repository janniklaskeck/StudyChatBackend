package stud.mi.dachatserver.dto;

import java.util.Date;

public class Message {

	private String _id = "message_id";
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
		final StringBuilder builder = new StringBuilder();
		builder.append("{ id: ");
		builder.append(_id);
		builder.append(",\nrev: ");
		builder.append(_rev);
		builder.append(",\ndatePosted: ");
		builder.append(datePosted);
		builder.append(",\nmessage: ");
		builder.append(content);
		builder.append("\n}");
		return builder.toString();
	}

}
