package com.app.weatherImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class WeatherlogicImpl {
	
	
	 
	 
	 final static String SQLUrl = "jdbc:sqlserver://localhost:1433;databaseName=Weather;username=admin;password=admin;";
	    static Connection connection;

	    public boolean  init() throws ClassNotFoundException
	    {
	         try {
	        	 
	        	 Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	            connection = DriverManager.getConnection(SQLUrl);
	            
	            return true;
	            
	        } catch (SQLException e) {
	            // TODO Auto-generated catch block
	        	return false;
	        	
	        }
	         
	         
	    }

	    public ArrayList<String> getCountry() throws ClassNotFoundException  {
	        // TODO Auto-generated method stub
	        boolean a = init();

	        ArrayList<String> table_data = new ArrayList<String>();
     
	        if ( a ==false)
	        {
	        	table_data.add("SQL error on initialization connection with database.");
				return table_data;
	        }
	        
	        Statement statement;
			try {
				
				String SQLQuery = "Select * from Country" ;
				statement = connection.createStatement();
				 ResultSet resultset = statement.executeQuery(SQLQuery);
				 
				 if(resultset.next()==false)
		        	{
		        		table_data.add("No Country found");
						return table_data;
		        	}
				 else {
					 do {
						  String CountryCode = resultset.getString("CountryCode");
			              String CountryName = resultset.getString("CountryName");
				          

				          table_data.add(CountryName+";"+CountryCode);
				          
				         
					 } while(resultset.next());
					 connection.close();
				 }

			      			      			       
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				table_data.add("SQL error when retrieving Country table data : " + e.getMessage());
				return table_data;
			}
	       
	        return table_data;
	    }
	    
	    
	    public ArrayList<String> getCity(String CountryCode) throws ClassNotFoundException  {
	        // TODO Auto-generated method stub

	    	boolean a = init();

	        ArrayList<String> table_data = new ArrayList<String>();
     
	        if ( a ==false)
	        {
	        	table_data.add("SQL error on initialization connection with database.");
				return table_data;
	        }
	        
	        
	       
			try { 
				
				String SQLQuery = "Select * from City where CountryCode = \'"+ CountryCode + "\'" ;

	            Statement statement;
				statement = connection.createStatement();
				 ResultSet resultset = statement.executeQuery(SQLQuery);
				 
				 if(resultset.next()==false)
		        	{
		        		table_data.add("No Cities found with CountryCode : " +CountryCode);
						return table_data;
		        	}
				 else {
					 do {
						 String CityCode = resultset.getString("CityCode");
				          String CityName = resultset.getString("CityName");
				          table_data.add(CityName+";"+CityCode);
					 }while(resultset.next());
					 connection.close();
				 }
				 
			
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				table_data.add("SQL error when retrieving City table data : " +e.getLocalizedMessage());
				return table_data;
			}
	       
	        return table_data;
	    }

		public ArrayList<String> getWeatherDetails(String CityCode) throws Exception {
			// TODO Auto-generated method stub
			
			boolean a = init();

	        ArrayList<String> table_data = new ArrayList<String>();
     
	        if ( a ==false)
	        {
	        	table_data.add("SQL error on initialization connection with database.");
				return table_data;
	        }
				
	       try {
	    	   
	         String SQLQuery = "Select * from WeatherDetails where CityCode = \'"+ CityCode + "\'" ;
			
			Statement statement = connection.createStatement();
			ResultSet resultset = statement.executeQuery(SQLQuery);
			 if(resultset.next()==false)
	        	{
	        		table_data.add("Currently No Weather details found for the City code : " +CityCode);
					return table_data;
	        	}
			 else {
				 do {
					  String citycode = resultset.getString("CityCode");
					  String CountryCode = resultset.getString("CountryCode");
					  String Date = resultset.getString("TimeStamp");
					  String Temperature = resultset.getString("Temperature");
					  
					  table_data.add(Date+";"+Temperature+";"+citycode+";"+CountryCode);
				 }while(resultset.next());

				 connection.close();
			
	         }
			 }
	       
	       catch(Exception e)
	       {
	    	   table_data.add("SQL error when retrieving Weather Details table data : " +e.getLocalizedMessage());
				return table_data;
	       }
	       
			return table_data;
		}
		
		public ArrayList<String> insertWeatherDetails(String CountryCode,String CityCode,String Timestamp,double Temp) throws Exception {
			// TODO Auto-generated method stub		
			boolean a = init();
	        ArrayList<String> table_data = new ArrayList<String>();
	        if ( a ==false)
	        {
	        	table_data.add("SQL error on initialization connection with database.");
				return table_data;
	        }
	        System.out.println(CountryCode +" " + CityCode +" " +Timestamp +" " + Temp);
	        			
			try {
				
				String SQLQuery = "Select COUNT(*) AS recordCount from WeatherDetails where CountryCode = '" + CountryCode + "' and CityCode = '" + CityCode + "' and Timestamp = '" + Timestamp + "' and Temperature = '" + Temp + "'";
		        Statement statement = connection.createStatement();
		        ResultSet resultset = statement.executeQuery(SQLQuery);
		        resultset.next();
		        
		        int count1 = resultset.getInt("recordCount");
				
								
				SQLQuery = "insert into WeatherDetails values('" + CountryCode + "','" + CityCode + "','" + Timestamp + "','" + Temp + "')";
		        statement = connection.createStatement();
				statement.executeUpdate(SQLQuery);
				
				SQLQuery = "Select COUNT(*) AS recordCount from WeatherDetails where CountryCode = '" + CountryCode + "' and CityCode = '" + CityCode + "' and Timestamp = '" + Timestamp + "' and Temperature = '" + Temp + "'";
		        statement = connection.createStatement();
		        resultset = statement.executeQuery(SQLQuery);
		        resultset.next();
		        
		        int count2 = resultset.getInt("recordCount");
				if(count1+1 == count2) 
				{
					table_data.add("success");
				}
				else
				{
					table_data.add("failed");
				}
				
				
		        }
			
			catch (Exception e) 
			{
			
				 return table_data;
		    }	
					
			connection.close();
		
			return table_data;
		}
	    

	    


}
