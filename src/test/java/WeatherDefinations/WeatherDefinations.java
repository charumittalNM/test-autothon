package WeatherDefinations;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;
import org.json.JSONObject;
import org.junit.Assert;
import common.JsonConversionUtil;
import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class WeatherDefinations {
	private static String urlData;
	private static String url;
	private static Response response;
	private static int expectedStatusCode;
	private static double expectedTemp,expectedtemp_min,expectedtemp_max;
	private JsonConversionUtil jsonConversionUtil;
	private JSONObject responseJsonObject;
	private Properties prop = new Properties();

	@Given("^pass \"([^\"]*)\" for \"([^\"]*)\" weather data$")
	public void verifyWeatherDatapassCityName(String arg1,String arg2) throws Throwable {
		InputStream input = new FileInputStream("src/test/resources/config/config.properties");
		prop.load(input);

		if(arg2.equalsIgnoreCase("city"))
		{
			url = prop.getProperty("cityNameUrl")+arg1+prop.getProperty("appid");
			System.out.println("URL: "+url);
			urlData = arg1;
		}
		else if(arg2.equalsIgnoreCase("id"))
		{
			url = prop.getProperty("cityIdUrl")+arg1+prop.getProperty("appid");
			System.out.println("URL: "+url);
			urlData = arg1;
		}
		else if(arg2.equalsIgnoreCase("zip"))
		{
			url = prop.getProperty("cityZipCodeUrl")+arg1+prop.getProperty("appid");
			System.out.println("URL: "+url);
			urlData = arg1;
		}
	}

	@Given("^Hit WeatherData api url$")
	public void hit_WeatherData_Api() throws Throwable {
		RequestSpecification httpRequest = RestAssured.given();
		response = httpRequest.get(url);
	}

	@Given("^Verify the weatherData response$")
	public void verify_WeatherData_Reponse(DataTable expectedReponseData) throws Throwable {
		jsonConversionUtil = new JsonConversionUtil();
		String responseBody = response.getBody().asString();
		System.out.println("Response Body is =>  " + responseBody);

		for (Map<String, String> data : expectedReponseData.asMaps(String.class, String.class)) {
			expectedStatusCode = Integer.parseInt(data.get("StatusCode"));
			if(!data.get("temp").isEmpty()) {
				expectedTemp = Double.valueOf(data.get("temp"));
			}
			else {
				expectedTemp = 0.0;
			}
			if(!data.get("temp_min").isEmpty()) {
				expectedtemp_min = Double.valueOf(data.get("temp_min"));
			}
			else {
				expectedtemp_min = 0.0;
			}
			if(!data.get("temp_max").isEmpty()) {
				expectedtemp_max = Double.valueOf(data.get("temp_max"));
			}
			else {
				expectedtemp_max = 0.0;
			}
		}

		responseJsonObject = jsonConversionUtil.getJsonObject(responseBody);
		Assert.assertEquals(expectedStatusCode, responseJsonObject.getInt("cod"));
		Assert.assertEquals(expectedTemp, responseJsonObject.getJSONObject("main").get("temp"));
		Assert.assertEquals(expectedtemp_min, responseJsonObject.getJSONObject("main").get("temp_min"));
		Assert.assertEquals(expectedtemp_max, responseJsonObject.getJSONObject("main").get("temp_max"));
	}


}
