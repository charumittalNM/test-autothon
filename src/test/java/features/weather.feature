#Author: your.email@your.domain.com
Feature: weatherReporting

  Scenario: Call current weather data By city name
    Given pass city name
    |city|
    |Gurugram|
    When Hit WeatherData api url
    Then Verify the weatherData response