package stud.mi.dachatserver.dto;

import java.util.ArrayList;
import java.util.List;

public class MessageList {

	private String _id = "message_list";
	private String _rev;

	public List<Message> messages = new ArrayList<>();

	@Override
	public String toString() {
		final StringBuilder builder = new StringBuilder();
		builder.append("[\n");
		for (int i = 0; i < messages.size(); i++) {
			builder.append(messages.get(i));
			if (i < messages.size() - 1) {
				builder.append(",");
			}
		}
		builder.append("]");
		return "";
	}

}
