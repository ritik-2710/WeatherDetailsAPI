package com.app.weather;

import java.util.ArrayList;

import javax.jws.WebMethod;
import javax.jws.WebService;


@WebService
public interface WeatherInterface {

	@WebMethod
	public ArrayList<String> getCountryWSDL() throws Exception ;
	
	@WebMethod
	public ArrayList<String> getCityWSDL(String CountryCode) throws Exception ;
	
	@WebMethod
	public ArrayList<String> getWeatherDetails(String CityCode) throws Exception ;
	
	@WebMethod
	public ArrayList<String> insertWeatherDetails(String CountryCode, String CityCode, String Timestamp, double Temperature)
			throws Exception;
}