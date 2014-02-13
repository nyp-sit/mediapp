package signup;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

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

public class ElderLoginServlet extends HttpServlet  {

	 public void doPost(HttpServletRequest req, HttpServletResponse resp) 
	            throws IOException { 
		 
	        DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
			List<String> results = new ArrayList<String>();
			String userName = req.getParameter("NRIC");
			String pin = req.getParameter("Pin");
			String longitude=req.getParameter("longitude");
			String latitude=req.getParameter("latitude");
			String coordinates = req.getParameter("coordinates");
			String retrievedNRIC = null;
			String retrievedPass="";
			String retrievedName = "";
			String retrievedEmail = null;
			String retrievedPhone = null;
			String retrievedCGEmail = null;
			String retrievedCGName = null;
			String retrievedCGPhone = null;
			String retrievedLatitude= null;
			String retrievedLongitude= null;
			String retrievedLatitude2 = null;
			String retrievedLongitude2= null;
			String retrievedLatitude3= null;
			String retrievedLongitude3= null;
			String retrievedLatitude4= null;
			String retrievedLongitude4= null;
			String eName = null,ePhone = null,eAdd = null;
			String noSuchAccount = "No such Account";
			String xPin = "qwe";
			int i = 0;
			String[] arrayNames = null ;
			Filter Nric = new FilterPredicate("Nric",FilterOperator.EQUAL,userName);
			Filter password = new FilterPredicate("Pin",FilterOperator.EQUAL,pin);
			Filter success =CompositeFilterOperator.and(password, Nric);
			Query q = new Query("Elder").setFilter(success);
			PreparedQuery pq = datastore.prepare(q);
			//date
			Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT+8"));
			 
			Date currentLocalTime = cal.getTime();
			 DateFormat date = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss z");   
			 date.setTimeZone(TimeZone.getTimeZone("GMT+8")); 
			 String localTime = date.format(currentLocalTime); 

			 
			 
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
				 retrievedLatitude = result.getProperty("latitude").toString();
				 retrievedLongitude = result.getProperty("longitude").toString();
				 retrievedLatitude2 =result.getProperty("latitude2").toString();;
				 retrievedLongitude2= result.getProperty("longitude2").toString();;
				 retrievedLatitude3= result.getProperty("latitude3").toString();;
				 retrievedLongitude3= result.getProperty("longitude3").toString();;
				 retrievedLatitude4 =result.getProperty("latitude4").toString();;
				 retrievedLongitude4= result.getProperty("longitude4").toString();;
				ePhone = result.getProperty("EPhone").toString();;
				eName=  result.getProperty("Ename").toString();;
				eAdd =  result.getProperty("EAddress").toString();;
				  i++;
				  }
			if(retrievedPass == "" || retrievedPass == null){
				String fail ="Invalid Login Credentials";
				results.add(fail);
				results.add(fail);
				results.add(fail);
				results.add(fail);
				String json = new Gson().toJson(results);
				resp.setContentType("application/json");
				//resp.setCharacterEncoding("UTF-8");
				resp.getWriter().println(json);
			}
			
			else{
				
					Transaction txn = datastore.beginTransaction();
					
						Key nric = KeyFactory.createKey("Elder", userName);
						Entity User;
						try {
							User = datastore.get(nric);
							User.setProperty("coordinates", coordinates);
							User.setProperty("latitude", latitude);
							User.setProperty("longitude", longitude);
							User.setProperty("lastlogin", localTime);
							User.setProperty("latitude2",retrievedLatitude);
							User.setProperty("longitude2",retrievedLongitude);
							User.setProperty("latitude3",retrievedLatitude2);
							User.setProperty("longitude3",retrievedLongitude2);
							User.setProperty("latitude4",retrievedLatitude3);
							User.setProperty("longitude4",retrievedLongitude3);
							User.setProperty("latitude5",retrievedLatitude4);
							User.setProperty("longitude5",retrievedLongitude4);
							datastore.put(User);
							 txn.commit();	
						} catch (EntityNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						results.add(retrievedName);
						results.add(retrievedNRIC);
						results.add(retrievedEmail);
						results.add(retrievedPhone);
						results.add(retrievedCGEmail);
						results.add(retrievedCGName);
						results.add(retrievedCGPhone);
						results.add(eName);
						results.add(ePhone);
						results.add(eAdd);
						
				String json = new Gson().toJson(results);
				resp.setContentType("application/json");
				//resp.setCharacterEncoding("UTF-8");
				resp.getWriter().println(json);
		 
		 
		 
	 }
	
	 }}
