package com.app.weather;


import java.util.ArrayList;

import javax.jws.WebService;

import com.app.weatherImpl.WeatherlogicImpl;


@WebService(endpointInterface = "com.app.weather.WeatherInterface")
public class Weather implements WeatherInterface {
	
	
	public ArrayList<String> getCountryWSDL() throws Exception
	{
		return new WeatherlogicImpl().getCountry();
	}
	
	
	public ArrayList<String> getCityWSDL(String CountryCode) throws Exception 	{
		return new WeatherlogicImpl().getCity(CountryCode);
		
	}
	public ArrayList<String> getWeatherDetails(String CityCode) throws Exception {
		// TODO Auto-generated method stub
		return new WeatherlogicImpl().getWeatherDetails(CityCode);
	}

	public ArrayList<String> insertWeatherDetails(String CountryCode, String CityCode, String Timestamp, double temp)
			throws Exception {
		// TODO Auto-generated method stub
		
		return new WeatherlogicImpl().insertWeatherDetails(CountryCode, CityCode, Timestamp, temp);
		
	}
	
	
}
