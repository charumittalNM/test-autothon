package common;

import org.json.JSONObject;

public class JsonConversionUtil {
	
	public JSONObject getJsonObject(String response)
	{
		JSONObject responseJsonObject = new JSONObject(response);
		return responseJsonObject;
	}

}
