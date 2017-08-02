package stud.mi.dachatserver.utils;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class EnvUtils
{

    private static final JsonParser JSON_PARSER = new JsonParser();
    private static final String NO_SQL_NAME = "cloudantNoSQLDB";

    private static final String CREDENTIALS = "credentials";

    public static String getDbUser()
    {
        if (isRunningLocally())
        {
            return "f6f5b7a3-00d3-4e19-9f24-a1771f81aae0-bluemix";
        }
        final JsonObject envVars = getVCapEnvVars();
        final JsonArray array = envVars.get(NO_SQL_NAME).getAsJsonArray();
        final JsonObject dbEnvVars = array.get(0).getAsJsonObject();
        final JsonObject dbCreds = dbEnvVars.get(CREDENTIALS).getAsJsonObject();
        return dbCreds.get("username").getAsString();
    }

    public static String getDbPass()
    {
        if (isRunningLocally())
        {
            return "951e1822adb4077d968edc0429d87493a8ce028487ed296c4da8ad50a57680f7";
        }
        final JsonObject envVars = getVCapEnvVars();
        final JsonArray array = envVars.get(NO_SQL_NAME).getAsJsonArray();
        final JsonObject dbEnvVars = array.get(0).getAsJsonObject();
        final JsonObject dbCreds = dbEnvVars.get(CREDENTIALS).getAsJsonObject();
        return dbCreds.get("password").getAsString();
    }

    public static String getDbAccount()
    {
        if (isRunningLocally())
        {
            return "f6f5b7a3-00d3-4e19-9f24-a1771f81aae0-bluemix";
        }
        final JsonObject envVars = getVCapEnvVars();
        final JsonArray array = envVars.get(NO_SQL_NAME).getAsJsonArray();
        final JsonObject dbEnvVars = array.get(0).getAsJsonObject();
        final JsonObject dbCreds = dbEnvVars.get(CREDENTIALS).getAsJsonObject();
        return dbCreds.get("host").getAsString().replace(".cloudant.com", "");
    }

    public static JsonObject getVCapEnvVars()
    {
        final String vCapEnvVars = System.getenv("VCAP_SERVICES");
        return JSON_PARSER.parse(vCapEnvVars).getAsJsonObject();
    }

    public static boolean isRunningLocally()
    {
        return System.getenv("PORT") == null;
    }

}
