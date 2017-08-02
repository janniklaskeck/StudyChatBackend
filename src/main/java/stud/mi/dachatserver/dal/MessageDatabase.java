package stud.mi.dachatserver.dal;

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
import stud.mi.dachatserver.utils.EnvUtils;

public class MessageDatabase
{

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageDatabase.class);
    private static final Gson GSON = new Gson();

    private MessageDatabase()
    {
    }

    public static JsonObject addChannelMessageToDB(final String channelName, final String content)
    {
        LOGGER.info("Save Message from channel {}", channelName);
        final Database db = getDB(channelName);
        MessageList msgList = null;
        try
        {
            msgList = db.find(MessageList.class, new MessageList().getId());
        }
        catch (final NoDocumentException e)
        {
            LOGGER.error("Could not find Document in DB.", e);
        }
        if (msgList == null)
        {
            msgList = new MessageList();
        }
        else
        {
            db.remove(msgList);
        }
        msgList.removeRev();
        msgList.addMessage(new Message(content));
        final JsonObject jo = GSON.toJsonTree(msgList).getAsJsonObject();
        db.save(jo);
        return jo;
    }

    public static String getMessageFromDB(final String dbName, final String key)
    {
        LOGGER.info("Return all Message for channel {}", dbName);
        final Database db = getDB(dbName);
        try
        {
            final MessageList msgList = db.find(MessageList.class, key);
            return GSON.toJson(msgList);
        }
        catch (final NoDocumentException e)
        {
            LOGGER.error("Could not find Document in DB", e);
            return "{}";
        }
    }

    public static void removeDB(final String dbName)
    {
        final Database db = getDB(dbName);
        MessageList msgList = null;
        try
        {
            msgList = db.find(MessageList.class, new MessageList().getId());
        }
        catch (final NoDocumentException e)
        {
            LOGGER.error("Could not find Document in DB", e);
        }
        if (msgList != null)
        {
            db.remove(msgList.getId(), msgList.getRev());
        }
        LOGGER.debug("Removed all messages from Channel {}", dbName);
    }

    public static Database getDB(final String dbName)
    {
        final String user = EnvUtils.getDbUser();
        final String pass = EnvUtils.getDbPass();
        final String account = EnvUtils.getDbAccount();
        final CloudantClient client = ClientBuilder.account(account).username(user).password(pass).build();
        return client.database(dbName, true);
    }
}
