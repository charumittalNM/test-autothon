#Author: Hackathon@gmail.com
Feature: weatherReporting

  Scenario: Call current weather data By city name
    Given pass weather data
    |city123|
    |Gurugram|
	|London|
    |Delhi|
    |Pune|
    |Simla|
    
    #When Hit WeatherData api url
    #Then Verify the weatherData response
    #|StatusCode|temp  |temp_min|temp_max|
    #|200       |280.32|279.15  |281.15  |
  
     
   # Scenario: Call current weather data By city ZipCode
#    Given pass "94040" for "zip" weather data
#    When Hit WeatherData api url
#    Then Verify the weatherData response
#    |StatusCode|temp  |temp_min|temp_max|
#    |200       |280.32|279.15  |281.15  |
#      
#    Scenario: Call current weather data By city ID
#    Given pass "2172797" for "id" weather data
#    When Hit WeatherData api url
#    Then Verify the weatherData response
#    |StatusCode|temp  |temp_min|temp_max|
#    |200       |300.15|300.15  |300.15  |