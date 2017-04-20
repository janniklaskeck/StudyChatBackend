package stud.mi.dachatserver.dal;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cloudant.client.api.ClientBuilder;
import com.cloudant.client.api.CloudantClient;
import com.cloudant.client.api.Database;
import com.cloudant.client.org.lightcouch.NoDocumentException;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import stud.mi.dachatserver.dto.Message;
import stud.mi.dachatserver.dto.MessageList;

public class MessageDatabase {

	private static final Logger LOGGER = LoggerFactory.getLogger(MessageDatabase.class);
	private static final Gson GSON = new Gson();

	private MessageDatabase() {
	}

	public static JsonObject addMessageToDB(final String message) {
		final Database db = getDB();
		MessageList msgList = null;
		try {
			msgList = db.find(MessageList.class, new MessageList().getId());
		} catch (NoDocumentException e) {
			LOGGER.error("Could not find Document in DB", e);
		}
		if (msgList == null) {
			msgList = new MessageList();
		} else {
			db.remove(msgList.getId(), msgList.getRev());
			msgList.removeRev();
		}

		msgList.addMessage(new Message(message, new Date()));
		final JsonObject jo = GSON.toJsonTree(msgList).getAsJsonObject();
		db.save(jo);
		return jo;
	}

	public static String getMessageFromDB(final String key) {
		final Database db = getDB();
		final MessageList msgList = db.find(MessageList.class, key);
		return msgList.toString();
	}

	public static void removeDB() {
		final Database db = getDB();
		MessageList msgList = null;
		try {
			msgList = db.find(MessageList.class, new MessageList().getId());
		} catch (NoDocumentException e) {
			LOGGER.error("Could not find Document in DB", e);
		}
		if (msgList != null) {
			db.remove(msgList.getId(), msgList.getRev());
		}
	}

	public static Database getDB() {
		final String user = "f00c8b1b-c0ad-4bc6-a302-c18eeef61249-bluemix";
		final String pass = "70c7e3c7c66570fb0f8afcc76b88b5816e9feb671612e59d6e9d4b55767510fa";
		final String account = "f00c8b1b-c0ad-4bc6-a302-c18eeef61249-bluemix";
		// final String user = EnvUtils.getDbUser();
		// final String pass = EnvUtils.getDbPass();
		// final String account = EnvUtils.getDbAccount();
		final CloudantClient client = ClientBuilder.account(account).username(user).password(pass).build();
		final String dbName = "message_test";
		return client.database(dbName, true);
	}
}
