package stud.mi.dachatserver.utils;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class EnvUtils {

	private static final JsonParser JSON_PARSER = new JsonParser();
	private static final String NO_SQL_NAME = "cloudantNoSQLDB";

	public static String getDbUser() {
		final JsonObject envVars = getVCapEnvVars();
		final JsonArray array = envVars.get(NO_SQL_NAME).getAsJsonArray();
		final JsonObject dbEnvVars = array.get(0).getAsJsonObject();
		final JsonObject dbCreds = dbEnvVars.get("credentials").getAsJsonObject();
		return dbCreds.get("username").getAsString();
	}

	public static String getDbPass() {
		final JsonObject envVars = getVCapEnvVars();
		final JsonArray array = envVars.get(NO_SQL_NAME).getAsJsonArray();
		final JsonObject dbEnvVars = array.get(0).getAsJsonObject();
		final JsonObject dbCreds = dbEnvVars.get("credentials").getAsJsonObject();
		return dbCreds.get("password").getAsString();
	}

	public static String getDbAccount() {
		final JsonObject envVars = getVCapEnvVars();
		final JsonArray array = envVars.get(NO_SQL_NAME).getAsJsonArray();
		final JsonObject dbEnvVars = array.get(0).getAsJsonObject();
		final JsonObject dbCreds = dbEnvVars.get("credentials").getAsJsonObject();
		return dbCreds.get("host").getAsString().replace(".cloudant.com", "");
	}

	public static JsonObject getVCapEnvVars() {
		final String vCapEnvVars = System.getenv("VCAP_SERVICES");
		return JSON_PARSER.parse(vCapEnvVars).getAsJsonObject();
	}

}
