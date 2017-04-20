package stud.mi.dachatserver.dal;

import java.time.ZonedDateTime;

import com.cloudant.client.api.ClientBuilder;
import com.cloudant.client.api.CloudantClient;
import com.cloudant.client.api.Database;

import stud.mi.dachatserver.dto.Message;
import stud.mi.dachatserver.dto.MessageList;
import stud.mi.dachatserver.utils.EnvUtils;

public class MessageDatabase {

	public static void addMessageToDB(final String message) {
		final Database db = getDB();
		MessageList msgList = db.find(MessageList.class, "message_list");
		if (msgList == null) {
			msgList = new MessageList();
		}
		msgList.messages.add(new Message(message, ZonedDateTime.now()));
		db.save(msgList);
	}

	public static String getMessageFromDB(final String key) {
		final Database db = getDB();
		final Message msg = db.find(Message.class, key);
		System.out.println(msg);
		return msg.getMessage();
	}

	public static Database getDB() {
		// final String user = "f00c8b1b-c0ad-4bc6-a302-c18eeef61249-bluemix";
		// final String pass =
		// "70c7e3c7c66570fb0f8afcc76b88b5816e9feb671612e59d6e9d4b55767510fa";
		// final String account =
		// "f00c8b1b-c0ad-4bc6-a302-c18eeef61249-bluemix";
		final String user = EnvUtils.getDbUser();
		final String pass = EnvUtils.getDbPass();
		final String account = EnvUtils.getDbAccount();
		final CloudantClient client = ClientBuilder.account(account).username(user).password(pass).build();
		final String dbName = "message_test";
		return client.database(dbName, true);
	}
}
