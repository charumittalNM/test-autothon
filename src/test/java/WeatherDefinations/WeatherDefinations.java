package WeatherDefinations;

import java.util.Map;

import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class WeatherDefinations {

	private static String cityName;
	private static Response response;

	@Given("^pass city name$")
	public void verifyWeatherDatapassCityName(DataTable cityNames) throws Throwable {
		for (Map<String, String> data : cityNames.asMaps(String.class, String.class)) {			 
			cityName = data.get("city");
		}
	}
	
	@Given("^Hit WeatherData api url$")
	public void hit_WeatherData_Api() throws Throwable {		
		 RequestSpecification httpRequest = RestAssured.given();
		 response = httpRequest.get("https://samples.openweathermap.org/data/2.5/weather?q="+cityName+"&appid=b6907d289e10d714a6e88b30761fae22");
	}

	@Given("^Verify the weatherData response$")
	public void verify_WeatherData_Reponse() throws Throwable {		
		 String responseBody = response.getBody().asString();
		 System.out.println("Response Body is =>  " + responseBody);
	}


}
