package signup;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.Blob;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.CompositeFilterOperator;
import com.google.appengine.api.datastore.Query.Filter;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.FilterPredicate;
import com.google.gson.Gson;
public class SuperTestRetrieveServlet  extends HttpServlet { 
	 
	 protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 
	        response.getOutputStream().println("faggot servlet");
	        
	        DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
			List<String> results = new ArrayList<String>();
			String userName = "s9438651i";
			String pin = "12345";
			String retrievedNRIC;
			String retrievedPass="";
			String retrievedName = "";
			String retrievedPhone;
			String noSuchAccount = "No such Account";
			String xPin = "qwe";
			Blob pic ;
			
			Filter password =
					  new FilterPredicate("Pin", FilterOperator.EQUAL, pin);
			Filter Nric = new FilterPredicate("Nric",FilterOperator.EQUAL,userName);
			Filter success =
					  CompositeFilterOperator.and(password, Nric);
			Query q = new Query("User").setFilter(success);
			PreparedQuery pq = datastore.prepare(q);
			
			for (Entity result : pq.asIterable()) {
				retrievedPass = (String) result.getProperty("Pin").toString();	
				retrievedName = (String) result.getProperty("Name").toString();
				  retrievedNRIC = (String) result.getProperty("Nric").toString();
				  retrievedPhone=(String)result.getProperty("Phone").toString();
				  pic = (Blob)result.getProperty("Image");
			}
				
			response.getOutputStream().println(retrievedPass + retrievedName);
	 
	    }


	
	public void doPost(HttpServletRequest req, HttpServletResponse resp) 
            throws IOException { 
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		List<String> results = new ArrayList<String>();
		String userName = req.getParameter("NRIC");
		String pin = req.getParameter("Pin");
		String retrievedNRIC="";
		String retrievedPass="";
		String retrievedName="";
		String retrievedPhone="";
		String retrievedEmail="";
		String noSuchAccount = "No such Account";
		String xPin = "qwe";
		Blob pic = null;
		
		
		Filter password = new FilterPredicate("Pin", FilterOperator.EQUAL, pin);
		Filter Nric = new FilterPredicate("Nric",FilterOperator.EQUAL,userName);
		Filter success =CompositeFilterOperator.and (Nric,password);
		Query q = new Query("User").setFilter(success);
		PreparedQuery pq = datastore.prepare(q);
		String testing = null ;
		for (Entity result : pq.asIterable()) {
			retrievedPass = (String) result.getProperty("Pin").toString();	
			retrievedName = (String) result.getProperty("Name").toString();
			  retrievedNRIC = (String) result.getProperty("Nric").toString();
			  retrievedPhone=(String)result.getProperty("Phone").toString();
			  retrievedEmail = (String)result.getProperty("Email").toString();
			  pic = (Blob)result.getProperty("Image");
		
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
			if (pic != null) {
	            // Set the appropriate Content-Type header and write the raw bytes
	            // to the response's output stream
	            //resp.setContentType(myImage.getImageType());
	           byte[] haha = pic.getBytes();
	           testing= new String(haha, "UTF-8");}
	            
	           
	            
			results.add(retrievedName);
			results.add(retrievedPass);
			results.add(retrievedPhone);
			results.add(retrievedNRIC);
			results.add(retrievedEmail);
			results.add("success");	
			String json = new Gson().toJson(results);
			resp.setContentType("application/json");
			//resp.setCharacterEncoding("UTF-8");
			resp.getWriter().println(json);
			
			
		}
			
			
			
			
		}
		
			
		
		
		
		
		
	}
				                      
		
		
		
			
		
		
	
	
