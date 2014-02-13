package signup;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Transaction;
import com.google.appengine.api.datastore.Query.CompositeFilterOperator;
import com.google.appengine.api.datastore.Query.Filter;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.FilterPredicate;
import com.google.gson.Gson;

public class RetrieveOneElderServlet extends HttpServlet  {

	 public void doGet(HttpServletRequest req, HttpServletResponse resp) 
	            throws IOException { 
		 
		 
	        DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
			List<String> results = new ArrayList<String>();
			String userName = "123";
			String elderName = "f";
			String retrievedNRIC = null;
			String retrievedPass="";
			String retrievedName = "";
			String retrievedEmail = null;
			String retrievedPhone = null;
			String retrievedCGEmail = null;
			String retrievedCGName = null;
			String retrievedCGPhone = null;
			String retrievedCoordinates = null;
			String retrievedLatitude = null;
					String retrievedLongitude = null;
					String retrievedLatitude2 = null;
					String retrievedLongitude2= null;
					String retrievedLatitude3= null;
					String retrievedLongitude3= null;
					String retrievedLatitude4= null;
					String retrievedLongitude4= null;
					String retrievedLatitude5= null;
					String retrievedLongitude5= null;
					String retrievedEmergencyName = null;
					String retrievedEmergencyPhone=null;
					String retrievedEmergencyAddress=null;
			String noSuchAccount = "No such Account";
			String xPin = "qwe";
			int i = 0;
			String[] arrayNames = null ;
			Filter Nric = new FilterPredicate("cgNRIC",FilterOperator.EQUAL,userName);
			Filter elderName1 = new FilterPredicate("Name",FilterOperator.EQUAL,elderName);
			Filter success =CompositeFilterOperator.and(elderName1, Nric);
			Query q = new Query("Elder").setFilter(success);
			PreparedQuery pq = datastore.prepare(q);
			String retrievedDate = null;
			for (Entity result : pq.asIterable()) {
					
				//arrayNames[i] = (String) result.getProperty("Name").toString();
				 // retrievedNRIC = (String) result.getProperty("Nric").toString();
				 // retrievedPhone=(String)result.getProperty("Phone").toString();
				  //response.getOutputStream().println(retrievedNRIC + retrievedName);
				retrievedName =  result.getProperty("Name").toString();
				retrievedNRIC= result.getProperty("Nric").toString();
				retrievedPass = result.getProperty("Pin").toString();
				retrievedPhone= result.getProperty("Phone").toString();
				retrievedEmail=result.getProperty("Email").toString();
				retrievedCGEmail= result.getProperty("cgEmail").toString();
				retrievedCGName= result.getProperty("cgName").toString();
				retrievedCGPhone= result.getProperty("cgPhone").toString();
				retrievedCoordinates = result.getProperty("coordinates").toString();
				retrievedLatitude = result.getProperty("latitude").toString();
				retrievedLongitude = result.getProperty("longitude").toString();
				retrievedDate = result.getProperty("lastlogin").toString();
				 retrievedLatitude2 =result.getProperty("latitude2").toString();;
				 retrievedLongitude2= result.getProperty("longitude2").toString();;
				 retrievedLatitude3= result.getProperty("latitude3").toString();;
				 retrievedLongitude3= result.getProperty("longitude3").toString();;
				 retrievedLatitude4 =result.getProperty("latitude4").toString();;
				 retrievedLongitude4= result.getProperty("longitude4").toString();;
				 retrievedLatitude5 =result.getProperty("latitude5").toString();;
				 retrievedLongitude5= result.getProperty("longitude5").toString();;
				 retrievedEmergencyName= result.getProperty("Ename").toString();;
					retrievedEmergencyPhone = result.getProperty("EPhone").toString();
					retrievedEmergencyAddress = result.getProperty("EAddress").toString();	
				 
				  }


						results.add(retrievedName);
						results.add(retrievedNRIC);
						results.add(retrievedEmail);
						results.add(retrievedPhone);
						results.add(retrievedCGEmail);
						results.add(retrievedCGName);
						results.add(retrievedCGPhone);
						results.add(retrievedCoordinates);
						results.add(retrievedLatitude);
						results.add(retrievedLongitude);
						results.add(retrievedDate);
						results.add(retrievedLatitude2);
						results.add(retrievedLongitude2);
						results.add(retrievedLatitude3);
						results.add(retrievedLongitude3);
						results.add(retrievedLatitude4);
						results.add(retrievedLongitude4);
						results.add(retrievedLatitude5);
						results.add(retrievedLongitude5);
						results.add(retrievedEmergencyName);
						results.add(retrievedEmergencyPhone);
						results.add(retrievedEmergencyAddress);
				String json = new Gson().toJson(results);
				resp.setContentType("application/json");
				//resp.setCharacterEncoding("UTF-8");
				resp.getWriter().println(json);
		 
		 
		 
	 }
	
		 
		 
	 
	
	
	 public void doPost(HttpServletRequest req, HttpServletResponse resp) 
	            throws IOException { 
		 
	        DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
			List<String> results = new ArrayList<String>();
			String userName = req.getParameter("NRIC");
			String elderName = req.getParameter("elderName");
			String retrievedNRIC = null;
			String retrievedPass="";
			String retrievedName = "";
			String retrievedEmail = null;
			String retrievedPhone = null;
			String retrievedCGEmail = null;
			String retrievedCGName = null;
			String retrievedCGPhone = null;
			String retrievedCoordinates = null;
			String retrievedLatitude = null;
					String retrievedLongitude = null;
					String retrievedLatitude2 = null;
					String retrievedLongitude2= null;
					String retrievedLatitude3= null;
					String retrievedLongitude3= null;
					String retrievedLatitude4= null;
					String retrievedLongitude4= null;
					String retrievedLatitude5= null;
					String retrievedLongitude5= null;
					String retrievedEmergencyName = null;
					String retrievedEmergencyPhone=null;
					String retrievedEmergencyAddress=null;
			String noSuchAccount = "No such Account";
			String xPin = "qwe";
			int i = 0;
			String[] arrayNames = null ;
			Filter Nric = new FilterPredicate("cgNRIC",FilterOperator.EQUAL,userName);
			Filter elderName1 = new FilterPredicate("Name",FilterOperator.EQUAL,elderName);
			Filter success =CompositeFilterOperator.and(elderName1, Nric);
			Query q = new Query("Elder").setFilter(success);
			PreparedQuery pq = datastore.prepare(q);
			String retrievedDate = null;
			for (Entity result : pq.asIterable()) {
					
				//arrayNames[i] = (String) result.getProperty("Name").toString();
				 // retrievedNRIC = (String) result.getProperty("Nric").toString();
				 // retrievedPhone=(String)result.getProperty("Phone").toString();
				  //response.getOutputStream().println(retrievedNRIC + retrievedName);
				retrievedName =  result.getProperty("Name").toString();
				retrievedNRIC= result.getProperty("Nric").toString();
				retrievedPass = result.getProperty("Pin").toString();
				retrievedPhone= result.getProperty("Phone").toString();
				retrievedEmail=result.getProperty("Email").toString();
				retrievedCGEmail= result.getProperty("cgEmail").toString();
				retrievedCGName= result.getProperty("cgName").toString();
				retrievedCGPhone= result.getProperty("cgPhone").toString();
				retrievedCoordinates = result.getProperty("coordinates").toString();
				retrievedLatitude = result.getProperty("latitude").toString();
				retrievedLongitude = result.getProperty("longitude").toString();
				retrievedDate = result.getProperty("lastlogin").toString();
				 retrievedLatitude2 =result.getProperty("latitude2").toString();;
				 retrievedLongitude2= result.getProperty("longitude2").toString();;
				 retrievedLatitude3= result.getProperty("latitude3").toString();;
				 retrievedLongitude3= result.getProperty("longitude3").toString();;
				 retrievedLatitude4 =result.getProperty("latitude4").toString();;
				 retrievedLongitude4= result.getProperty("longitude4").toString();;
				 retrievedLatitude5 =result.getProperty("latitude5").toString();;
				 retrievedLongitude5= result.getProperty("longitude5").toString();;
				 retrievedEmergencyName= result.getProperty("Ename").toString();;
					retrievedEmergencyPhone = result.getProperty("EPhone").toString();
					retrievedEmergencyAddress = result.getProperty("EAddress").toString();	
				  i++;
				  }


						results.add(retrievedName);
						results.add(retrievedNRIC);
						results.add(retrievedEmail);
						results.add(retrievedPhone);
						results.add(retrievedCGEmail);
						results.add(retrievedCGName);
						results.add(retrievedCGPhone);
						results.add(retrievedCoordinates);
						results.add(retrievedLatitude);
						results.add(retrievedLongitude);
						results.add(retrievedDate);
						results.add(retrievedLatitude2);
						results.add(retrievedLongitude2);
						results.add(retrievedLatitude3);
						results.add(retrievedLongitude3);
						results.add(retrievedLatitude4);
						results.add(retrievedLongitude4);
						results.add(retrievedLatitude5);
						results.add(retrievedLongitude5);
						results.add(retrievedEmergencyName);
						results.add(retrievedEmergencyPhone);
						results.add(retrievedEmergencyAddress);
				String json = new Gson().toJson(results);
				resp.setContentType("application/json");
				//resp.setCharacterEncoding("UTF-8");
				resp.getWriter().println(json);
		 
		 
		 
	 }
	
	 }
